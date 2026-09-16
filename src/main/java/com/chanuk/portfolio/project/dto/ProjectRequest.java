package com.chanuk.portfolio.project.dto;
import jakarta.validation.constraints.*;
public record ProjectRequest(
 @NotBlank @Size(max = 120) String title,
 @NotBlank @Pattern(regexp = "COMPANY|FREELANCE|TEAM") String category,
 @NotBlank @Size(max = 5000) String description,
 @NotBlank @Size(max = 200) String role,
 @Size(max = 80) String period,
  boolean pending,
 @Min(0) int displayOrder
) {}
