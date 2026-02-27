package ci.eduplatlearn.repository;

import ci.eduplatlearn.entity.Fichier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FichierRepository extends JpaRepository<Fichier, Long> {
    Page<Fichier> findByLecon_Id(Long leconId, Pageable pageable);
}

