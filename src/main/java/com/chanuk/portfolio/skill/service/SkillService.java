package com.chanuk.portfolio.skill.service;
import com.chanuk.portfolio.skill.dto.*;
import com.chanuk.portfolio.skill.entity.Skill;
import com.chanuk.portfolio.skill.repository.SkillRepository;
import com.chanuk.portfolio.common.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
@Service
@Transactional(readOnly = true)
public class SkillService {
 private final SkillRepository repository;
 public SkillService(SkillRepository repository) { this.repository = repository; }
 public List<SkillResponse> findAll() { return repository.findAllByOrderByDisplayOrderAscIdAsc().stream().map(SkillResponse::from).toList(); }
 public SkillResponse findById(Long id) { return SkillResponse.from(findEntity(id)); }
 @Transactional
 public SkillResponse create(SkillRequest request) {
  return SkillResponse.from(repository.save(new Skill(request.name(), request.category(), request.displayOrder())));
 }
 @Transactional
 public SkillResponse update(Long id, SkillRequest request) {
  Skill entity = findEntity(id);
  entity.update(request.name(), request.category(), request.displayOrder());
  return SkillResponse.from(entity);
 }
 @Transactional
 public void delete(Long id) { repository.delete(findEntity(id)); }
 private Skill findEntity(Long id) { return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Skill", id)); }
}
