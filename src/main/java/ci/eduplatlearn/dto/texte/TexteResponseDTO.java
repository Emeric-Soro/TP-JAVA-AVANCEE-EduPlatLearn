package ci.eduplatlearn.dto.texte;

import java.time.LocalDateTime;

public record TexteResponseDTO(
        Long id,
        String titre,
        String contenu,
        Long leconId,
        String leconTitre,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {}

