package ci.eduplatlearn.dto.texte;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record TexteCreateRequestDTO(
        @Schema(example = "Guide d'installation Spring Boot")
        @NotBlank(message = "Le titre est obligatoire")
        @Size(min = 3, max = 200, message = "Le titre doit contenir entre 3 et 200 caractères")
        String titre,

        @Schema(example = "Voici le contenu détaillé du guide...")
        @NotBlank(message = "Le contenu est obligatoire")
        String contenu,

        @Schema(example = "1")
        @NotNull(message = "L'ID de la leçon est obligatoire")
        @Positive(message = "L'ID de la leçon doit être positif")
        Long leconId
) {}

