package ci.eduplatlearn.repository;

import ci.eduplatlearn.entity.Module;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModuleRepository extends JpaRepository<Module, Long> {
    Page<Module> findByCours_Id(Long coursId, Pageable pageable);
}
