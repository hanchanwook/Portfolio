package com.chanuk.portfolio.skill.dto;
import com.chanuk.portfolio.skill.entity.Skill;
public record SkillResponse(Long id, String name, String category, int displayOrder) {
 public static SkillResponse from(Skill entity) {
  return new SkillResponse(entity.getId(), entity.getName(), entity.getCategory(), entity.getDisplayOrder());
 }
}
