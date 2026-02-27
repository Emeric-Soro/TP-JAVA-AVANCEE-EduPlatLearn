package ci.eduplatlearn.dto.module;

import java.time.LocalDateTime;

public record ModuleResponseDTO(
        Long id,
        String titre,
        String description,
        Integer ordre,
        Long coursId,
        String coursTitre,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {}

