package ci.eduplatlearn.repository;

import ci.eduplatlearn.entity.Lecon;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeconRepository extends JpaRepository<Lecon, Long> {
    Page<Lecon> findByModule_Id(Long moduleId, Pageable pageable);
}
