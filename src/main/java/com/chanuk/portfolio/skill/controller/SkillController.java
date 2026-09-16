package com.chanuk.portfolio.skill.controller;
import com.chanuk.portfolio.skill.dto.*;
import com.chanuk.portfolio.skill.service.SkillService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;
@RestController
@RequestMapping("/api/skills")
public class SkillController {
 private final SkillService service;
 public SkillController(SkillService service) { this.service = service; }
 @GetMapping public List<SkillResponse> findAll() { return service.findAll(); }
 @GetMapping("/{id}") public SkillResponse findById(@PathVariable Long id) { return service.findById(id); }
 @PostMapping public ResponseEntity<SkillResponse> create(@Valid @RequestBody SkillRequest request) {
  SkillResponse response = service.create(request);
  return ResponseEntity.created(URI.create("/api/skills/" + response.id())).body(response);
 }
 @PutMapping("/{id}") public SkillResponse update(@PathVariable Long id, @Valid @RequestBody SkillRequest request) { return service.update(id, request); }
 @DeleteMapping("/{id}") public ResponseEntity<Void> delete(@PathVariable Long id) { service.delete(id); return ResponseEntity.noContent().build(); }
}
