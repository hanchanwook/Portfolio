package com.chanuk.portfolio.career.dto;
import com.chanuk.portfolio.career.entity.Career;
public record CareerResponse(Long id, String company, String role, String period, String description, boolean current, int displayOrder) {
 public static CareerResponse from(Career entity) {
  return new CareerResponse(entity.getId(), entity.getCompany(), entity.getRole(), entity.getPeriod(), entity.getDescription(), entity.getCurrent(), entity.getDisplayOrder());
 }
}
