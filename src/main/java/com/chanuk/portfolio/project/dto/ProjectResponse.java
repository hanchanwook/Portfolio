package com.chanuk.portfolio.project.dto;
import com.chanuk.portfolio.project.entity.Project;
public record ProjectResponse(Long id, String title, String category, String description, String role, String period, boolean pending, int displayOrder) {
 public static ProjectResponse from(Project entity) {
  return new ProjectResponse(entity.getId(), entity.getTitle(), entity.getCategory(), entity.getDescription(), entity.getRole(), entity.getPeriod(), entity.getPending(), entity.getDisplayOrder());
 }
}
