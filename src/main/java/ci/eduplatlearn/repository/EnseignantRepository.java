package ci.eduplatlearn.repository;

import ci.eduplatlearn.entity.Enseignant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EnseignantRepository extends JpaRepository<Enseignant, Long> {
    @Query(" select distinct e from Enseignant e left join fetch e.cours ")
    List<Enseignant> findAllWithCours();

    Page<Enseignant> findByCours_Id(Long coursId, Pageable pageable);
}
