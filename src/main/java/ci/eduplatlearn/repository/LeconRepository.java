package ci.eduplatlearn.repository;

import ci.eduplatlearn.entity.Lecon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeconRepository extends JpaRepository<Lecon, Long> {
}
