package ci.eduplatlearn.repository;

import ci.eduplatlearn.entity.Module;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModuleRepository extends JpaRepository<Module, Long> {
}
