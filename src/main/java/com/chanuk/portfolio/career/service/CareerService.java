package com.chanuk.portfolio.career.service;
import com.chanuk.portfolio.career.dto.*;
import com.chanuk.portfolio.career.entity.Career;
import com.chanuk.portfolio.career.repository.CareerRepository;
import com.chanuk.portfolio.common.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
@Service
@Transactional(readOnly = true)
public class CareerService {
 private final CareerRepository repository;
 public CareerService(CareerRepository repository) { this.repository = repository; }
 public List<CareerResponse> findAll() { return repository.findAllByOrderByDisplayOrderAscIdAsc().stream().map(CareerResponse::from).toList(); }
 public CareerResponse findById(Long id) { return CareerResponse.from(findEntity(id)); }
 @Transactional
 public CareerResponse create(CareerRequest request) {
  return CareerResponse.from(repository.save(new Career(request.company(), request.role(), request.period(), request.description(), request.current(), request.displayOrder())));
 }
 @Transactional
 public CareerResponse update(Long id, CareerRequest request) {
  Career entity = findEntity(id);
  entity.update(request.company(), request.role(), request.period(), request.description(), request.current(), request.displayOrder());
  return CareerResponse.from(entity);
 }
 @Transactional
 public void delete(Long id) { repository.delete(findEntity(id)); }
 private Career findEntity(Long id) { return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Career", id)); }
}
