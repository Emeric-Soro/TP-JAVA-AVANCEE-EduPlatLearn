package ci.eduplatlearn.dto.video;

import java.time.LocalDateTime;

public record VideoResponseDTO(
        Long id,
        String titre,
        String url,
        Integer dureeSecondes,
        Long leconId,
        String leconTitre,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {}

