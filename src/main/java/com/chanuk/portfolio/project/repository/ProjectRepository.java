package com.chanuk.portfolio.project.repository;
import com.chanuk.portfolio.project.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface ProjectRepository extends JpaRepository<Project, Long> {
 List<Project> findAllByOrderByDisplayOrderAscIdAsc();
}
