package ci.eduplatlearn.service;

import ci.eduplatlearn.dto.fichier.FichierCreateRequestDTO;
import ci.eduplatlearn.dto.fichier.FichierResponseDTO;
import ci.eduplatlearn.dto.fichier.FichierUpdateRequestDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FichierService {
    Page<FichierResponseDTO> getAll(Pageable pageable);

    FichierResponseDTO getById(Long id);

    FichierResponseDTO create(FichierCreateRequestDTO request);

    FichierResponseDTO update(Long id, FichierUpdateRequestDTO request);

    void delete(Long id);

    Page<FichierResponseDTO> getByLeconId(Long leconId, Pageable pageable);
}

