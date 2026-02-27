package ci.eduplatlearn.dto.lecon;

import java.time.LocalDateTime;

public record LeconResponseDTO(
        Long id,
        String titre,
        String resume,
        Integer ordre,
        Integer dureeMinutes,
        Long moduleId,
        String moduleTitre,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {}

