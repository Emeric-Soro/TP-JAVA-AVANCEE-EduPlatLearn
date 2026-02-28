package ci.eduplatlearn.repository;

import ci.eduplatlearn.entity.Texte;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TexteRepository extends JpaRepository<Texte, Long> {
    Page<Texte> findByLecon_Id(Long leconId, Pageable pageable);
}

