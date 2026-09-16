package com.chanuk.portfolio.career.repository;
import com.chanuk.portfolio.career.entity.Career;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface CareerRepository extends JpaRepository<Career, Long> {
 List<Career> findAllByOrderByDisplayOrderAscIdAsc();
}
