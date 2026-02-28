package ci.eduplatlearn.service;


import ci.eduplatlearn.dto.cours.CoursCreateRequestDTO;
import ci.eduplatlearn.dto.cours.CoursResponseDTO;
import ci.eduplatlearn.dto.cours.CoursUpdateRequestDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CoursService {
    Page<CoursResponseDTO> getAll(Pageable pageable);

    CoursResponseDTO getById(Long id);

    CoursResponseDTO create(CoursCreateRequestDTO request);

    CoursResponseDTO update(Long id, CoursUpdateRequestDTO request);

    void delete(Long id);

    CoursResponseDTO addEnseignant(Long coursId, Long enseignantId);

    CoursResponseDTO removeEnseignant(Long coursId, Long enseignantId);

    Page<CoursResponseDTO> getByEnseignantId(Long enseignantId, Pageable pageable);
}
