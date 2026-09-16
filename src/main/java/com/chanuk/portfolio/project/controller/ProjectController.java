package com.chanuk.portfolio.project.controller;
import com.chanuk.portfolio.project.dto.*;
import com.chanuk.portfolio.project.service.ProjectService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;
@RestController
@RequestMapping("/api/projects")
public class ProjectController {
 private final ProjectService service;
 public ProjectController(ProjectService service) { this.service = service; }
 @GetMapping public List<ProjectResponse> findAll() { return service.findAll(); }
 @GetMapping("/{id}") public ProjectResponse findById(@PathVariable Long id) { return service.findById(id); }
 @PostMapping public ResponseEntity<ProjectResponse> create(@Valid @RequestBody ProjectRequest request) {
  ProjectResponse response = service.create(request);
  return ResponseEntity.created(URI.create("/api/projects/" + response.id())).body(response);
 }
 @PutMapping("/{id}") public ProjectResponse update(@PathVariable Long id, @Valid @RequestBody ProjectRequest request) { return service.update(id, request); }
 @DeleteMapping("/{id}") public ResponseEntity<Void> delete(@PathVariable Long id) { service.delete(id); return ResponseEntity.noContent().build(); }
}
