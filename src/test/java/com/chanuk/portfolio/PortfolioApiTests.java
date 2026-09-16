package com.chanuk.portfolio;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import java.net.URI;
import java.net.http.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PortfolioApiTests {
    private static final String KEY = "test-only-key-0123456789-abcdefghijklmnopqrstuvwxyz";
    private final HttpClient client = HttpClient.newHttpClient();
    @Autowired Environment environment;

    private HttpResponse<String> request(String method, String path, String body, String key) throws Exception {
        var builder = HttpRequest.newBuilder(URI.create("http://localhost:" + environment.getProperty("local.server.port") + path));
        if (key != null) builder.header("X-Admin-Key", key);
        if (body != null) builder.header("Content-Type", "application/json");
        return client.send(builder.method(method, body == null ? HttpRequest.BodyPublishers.noBody() : HttpRequest.BodyPublishers.ofString(body)).build(), HttpResponse.BodyHandlers.ofString());
    }
    private String body(String resource) {
        return switch (resource) {
            case "projects" -> """
                {"title":"Test project","category":"TEAM","description":"Test description","role":"Developer","period":"2026","pending":true,"displayOrder":99}
                """;
            case "careers" -> """
                {"company":"Test company","role":"Developer","period":"2026","description":"Test description","current":false,"displayOrder":99}
                """;
            default -> """
                {"name":"Test skill","category":"BACKEND","displayOrder":99}
                """;
        };
    }
    @ParameterizedTest
    @CsvSource({"projects", "careers", "skills"})
    void crudPersistsAndReturnsDto(String resource) throws Exception {
        String path = "/api/" + resource;
        var created = request("POST", path, body(resource), KEY);
        assertEquals(201, created.statusCode(), created.body());
        String location = created.headers().firstValue("Location").orElseThrow();
        try {
            var found = request("GET", location, null, null);
            assertEquals(200, found.statusCode());
            assertTrue(found.body().contains("Test"));
            assertTrue(found.body().contains("\"id\":"));
            assertEquals(200, request("PUT", location, body(resource).replace("Test", "Updated"), KEY).statusCode());
            assertTrue(request("GET", location, null, null).body().contains("Updated"));
        } finally {
            assertEquals(204, request("DELETE", location, null, KEY).statusCode());
        }
        assertEquals(404, request("GET", location, null, null).statusCode());
        assertEquals(404, request("PUT", location, body(resource), KEY).statusCode());
        assertEquals(404, request("DELETE", location, null, KEY).statusCode());
    }
    @ParameterizedTest
    @CsvSource({"projects", "careers", "skills"})
    void writesRequireAdminAndValidation(String resource) throws Exception {
        String path = "/api/" + resource;
        assertEquals(200, request("GET", path, null, null).statusCode());
        assertEquals(401, request("POST", path, body(resource), null).statusCode());
        assertEquals(401, request("POST", path, body(resource), "wrong-key").statusCode());
        assertEquals(401, request("PUT", path + "/1", body(resource), null).statusCode());
        assertEquals(401, request("DELETE", path + "/1", null, null).statusCode());
        assertEquals(400, request("POST", path, "{}", KEY).statusCode());
        String invalidBody = body(resource).replace("Test project", "").replace("Test company", "").replace("Test skill", "");
        var invalid = request("POST", path, invalidBody, KEY);
        assertEquals(400, invalid.statusCode());
        assertTrue(invalid.body().contains("errors"));
        assertEquals(400, request("POST", path, body(resource).replace("99", "-1"), KEY).statusCode());
        assertEquals(400, request("GET", path + "/invalid-id", null, null).statusCode());
    }
    @Test
    void migrationsSeedRealContentAndRejectBadCategories() throws Exception {
        assertTrue(request("GET", "/api/projects", null, null).body().contains("S-IN"));
        assertTrue(request("GET", "/api/careers", null, null).body().contains("핸드소프트"));
        assertTrue(request("GET", "/api/skills", null, null).body().contains("MySQL"));
        assertEquals(400, request("POST", "/api/projects", body("projects").replace("TEAM", "UNKNOWN"), KEY).statusCode());
        assertEquals(400, request("POST", "/api/skills", body("skills").replace("BACKEND", "UNKNOWN"), KEY).statusCode());
    }
    @Test
    void corsAllowsOnlyConfiguredFrontend() throws Exception {
        String url = "http://localhost:" + environment.getProperty("local.server.port") + "/api/projects";
        for (String origin : new String[]{"http://localhost:3000", "https://untrusted.example"}) {
            var preflight = HttpRequest.newBuilder(URI.create(url)).header("Origin", origin)
                    .header("Access-Control-Request-Method", "POST")
                    .header("Access-Control-Request-Headers", "content-type,x-admin-key")
                    .method("OPTIONS", HttpRequest.BodyPublishers.noBody()).build();
            var response = client.send(preflight, HttpResponse.BodyHandlers.ofString());
            if (origin.equals("http://localhost:3000")) {
                assertEquals(200, response.statusCode());
                assertEquals(origin, response.headers().firstValue("Access-Control-Allow-Origin").orElseThrow());
            } else assertEquals(403, response.statusCode());
        }
    }
}
