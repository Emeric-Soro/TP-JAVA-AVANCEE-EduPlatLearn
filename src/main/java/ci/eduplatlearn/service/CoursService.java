package ci.eduplatlearn.service;


import ci.eduplatlearn.dto.cours.CoursCreateRequestDTO;
import ci.eduplatlearn.dto.cours.CoursResponseDTO;
import ci.eduplatlearn.dto.cours.CoursUpdateRequestDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CoursService {
    Page<CoursResponseDTO> getAll(Pageable pageable);

    CoursResponseDTO getById(Long id);

    CoursResponseDTO create(CoursCreateRequestDTO request);

    CoursResponseDTO update(Long id, CoursUpdateRequestDTO request);

    void delete(Long id);
}
