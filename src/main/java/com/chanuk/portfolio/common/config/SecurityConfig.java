package com.chanuk.portfolio.common.config;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.*;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.*;

@Configuration
public class SecurityConfig {
    @Bean
    org.springframework.security.core.userdetails.UserDetailsService userDetailsService() {
        return username -> { throw new org.springframework.security.core.userdetails.UsernameNotFoundException("Header authentication only"); };
    }
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http,
            @Value("${portfolio.admin-key}") String adminKey) throws Exception {
        if (!adminKey.isEmpty() && adminKey.length() < 32) {
            throw new IllegalArgumentException("ADMIN_API_KEY must contain at least 32 characters");
        }
        // Only an explicit header authenticates writes: no cookies, HTTP Basic, or login session.
        // With no browser-automatic credentials, the API does not need a CSRF token.
        http.csrf(csrf -> csrf.disable())
            .cors(cors -> {})
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .requestCache(cache -> cache.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(HttpMethod.GET, "/api/projects", "/api/projects/*", "/api/careers", "/api/careers/*", "/api/skills", "/api/skills/*").permitAll()
                .requestMatchers("/error").permitAll()
                .requestMatchers("/api/**").hasRole("ADMIN")
                .anyRequest().denyAll())
            .exceptionHandling(errors -> errors
                .authenticationEntryPoint((request, response, exception) -> error(response, 401, "Unauthorized"))
                .accessDeniedHandler((request, response, exception) -> error(response, 403, "Forbidden")))
            .addFilterBefore(new OncePerRequestFilter() {
                @Override
                protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
                        throws ServletException, IOException {
                    String suppliedKey = request.getHeader("X-Admin-Key");
                    if (!adminKey.isEmpty() && suppliedKey != null && MessageDigest.isEqual(
                            adminKey.getBytes(StandardCharsets.UTF_8), suppliedKey.getBytes(StandardCharsets.UTF_8))) {
                        var authentication = new UsernamePasswordAuthenticationToken("admin", null,
                                List.of(new SimpleGrantedAuthority("ROLE_ADMIN")));
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    }
                    chain.doFilter(request, response);
                }
            }, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
    private static void error(HttpServletResponse response, int status, String title) throws IOException {
        response.setStatus(status);
        response.setContentType("application/problem+json");
        response.getWriter().write("{\"type\":\"about:blank\",\"title\":\"" + title + "\",\"status\":" + status + "}");
    }
    @Bean
    CorsConfigurationSource corsConfigurationSource(@Value("${portfolio.cors-origins}") String origins) {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.stream(origins.split(",")).map(String::trim).filter(s -> !s.isEmpty()).toList());
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("Content-Type", "X-Admin-Key"));
        configuration.setExposedHeaders(List.of("Location"));
        configuration.setAllowCredentials(false);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/api/**", configuration);
        return source;
    }
}
