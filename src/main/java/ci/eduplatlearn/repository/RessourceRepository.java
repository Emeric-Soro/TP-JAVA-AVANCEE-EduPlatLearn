package ci.eduplatlearn.repository;

import ci.eduplatlearn.entity.Ressource;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RessourceRepository extends JpaRepository<Ressource, Long> {
}
