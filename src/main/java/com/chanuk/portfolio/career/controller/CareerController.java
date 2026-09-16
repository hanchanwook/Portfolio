package com.chanuk.portfolio.career.controller;
import com.chanuk.portfolio.career.dto.*;
import com.chanuk.portfolio.career.service.CareerService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;
@RestController
@RequestMapping("/api/careers")
public class CareerController {
 private final CareerService service;
 public CareerController(CareerService service) { this.service = service; }
 @GetMapping public List<CareerResponse> findAll() { return service.findAll(); }
 @GetMapping("/{id}") public CareerResponse findById(@PathVariable Long id) { return service.findById(id); }
 @PostMapping public ResponseEntity<CareerResponse> create(@Valid @RequestBody CareerRequest request) {
  CareerResponse response = service.create(request);
  return ResponseEntity.created(URI.create("/api/careers/" + response.id())).body(response);
 }
 @PutMapping("/{id}") public CareerResponse update(@PathVariable Long id, @Valid @RequestBody CareerRequest request) { return service.update(id, request); }
 @DeleteMapping("/{id}") public ResponseEntity<Void> delete(@PathVariable Long id) { service.delete(id); return ResponseEntity.noContent().build(); }
}
