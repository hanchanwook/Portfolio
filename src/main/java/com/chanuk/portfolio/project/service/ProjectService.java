package com.chanuk.portfolio.project.service;
import com.chanuk.portfolio.project.dto.*;
import com.chanuk.portfolio.project.entity.Project;
import com.chanuk.portfolio.project.repository.ProjectRepository;
import com.chanuk.portfolio.common.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
@Service
@Transactional(readOnly = true)
public class ProjectService {
 private final ProjectRepository repository;
 public ProjectService(ProjectRepository repository) { this.repository = repository; }
 public List<ProjectResponse> findAll() { return repository.findAllByOrderByDisplayOrderAscIdAsc().stream().map(ProjectResponse::from).toList(); }
 public ProjectResponse findById(Long id) { return ProjectResponse.from(findEntity(id)); }
 @Transactional
 public ProjectResponse create(ProjectRequest request) {
  return ProjectResponse.from(repository.save(new Project(request.title(), request.category(), request.description(), request.role(), request.period(), request.pending(), request.displayOrder())));
 }
 @Transactional
 public ProjectResponse update(Long id, ProjectRequest request) {
  Project entity = findEntity(id);
  entity.update(request.title(), request.category(), request.description(), request.role(), request.period(), request.pending(), request.displayOrder());
  return ProjectResponse.from(entity);
 }
 @Transactional
 public void delete(Long id) { repository.delete(findEntity(id)); }
 private Project findEntity(Long id) { return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Project", id)); }
}
