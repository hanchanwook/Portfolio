package com.chanuk.portfolio.skill.dto;
import jakarta.validation.constraints.*;
public record SkillRequest(
 @NotBlank @Size(max = 80) String name,
 @NotBlank @Pattern(regexp = "BACKEND|FRONTEND|DATABASE|TOOLS") String category,
 @Min(0) int displayOrder
) {}
