package ci.eduplatlearn.service;

import ci.eduplatlearn.dto.Enseignant.EnseignantCreateRequestDTO;
import ci.eduplatlearn.dto.Enseignant.EnseignantResponseDTO;
import ci.eduplatlearn.dto.Enseignant.EnseignantUpdateRequestDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EnseignantService {
    Page<EnseignantResponseDTO> getAll(Pageable pageable);

    EnseignantResponseDTO getById(Long id);

    EnseignantResponseDTO create(EnseignantCreateRequestDTO request);

    EnseignantResponseDTO update(Long id, EnseignantUpdateRequestDTO request);

    void delete(Long id);

    Page<EnseignantResponseDTO> getByCoursId(Long coursId, Pageable pageable);
}
