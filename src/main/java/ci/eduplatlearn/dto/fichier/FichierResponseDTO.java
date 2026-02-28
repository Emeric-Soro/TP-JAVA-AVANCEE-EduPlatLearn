package ci.eduplatlearn.dto.fichier;

import java.time.LocalDateTime;

public record FichierResponseDTO(
        Long id,
        String titre,
        String nomFichier,
        String cheminStockage,
        Long leconId,
        String leconTitre,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {}

