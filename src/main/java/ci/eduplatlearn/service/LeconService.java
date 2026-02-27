package ci.eduplatlearn.service;

import ci.eduplatlearn.dto.lecon.LeconCreateRequestDTO;
import ci.eduplatlearn.dto.lecon.LeconResponseDTO;
import ci.eduplatlearn.dto.lecon.LeconUpdateRequestDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LeconService {
    Page<LeconResponseDTO> getAll(Pageable pageable);

    LeconResponseDTO getById(Long id);

    LeconResponseDTO create(LeconCreateRequestDTO request);

    LeconResponseDTO update(Long id, LeconUpdateRequestDTO request);

    void delete(Long id);

    Page<LeconResponseDTO> getByModuleId(Long moduleId, Pageable pageable);
}

