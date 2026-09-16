package com.chanuk.portfolio.career.dto;
import jakarta.validation.constraints.*;
public record CareerRequest(
 @NotBlank @Size(max = 120) String company,
 @NotBlank @Size(max = 200) String role,
 @NotBlank @Size(max = 80) String period,
 @NotBlank @Size(max = 5000) String description,
  boolean current,
 @Min(0) int displayOrder
) {}
