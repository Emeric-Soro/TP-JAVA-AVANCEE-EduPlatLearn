package ci.eduplatlearn.service;

import ci.eduplatlearn.dto.texte.TexteCreateRequestDTO;
import ci.eduplatlearn.dto.texte.TexteResponseDTO;
import ci.eduplatlearn.dto.texte.TexteUpdateRequestDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TexteService {
    Page<TexteResponseDTO> getAll(Pageable pageable);

    TexteResponseDTO getById(Long id);

    TexteResponseDTO create(TexteCreateRequestDTO request);

    TexteResponseDTO update(Long id, TexteUpdateRequestDTO request);

    void delete(Long id);

    Page<TexteResponseDTO> getByLeconId(Long leconId, Pageable pageable);
}

