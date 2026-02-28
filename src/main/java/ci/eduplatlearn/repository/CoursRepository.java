package ci.eduplatlearn.repository;

import ci.eduplatlearn.entity.Cours;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CoursRepository extends JpaRepository<Cours, Long> {
    @Query (" select distinct c from Cours c left join fetch c.modules ")
    List<Cours> findAllWithModule();

    Page<Cours> findByEnseignants_Id(Long enseignantId, Pageable pageable);
}
