package ci.eduplatlearn.dto.video;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record VideoCreateRequestDTO(
        @Schema(example = "Tutoriel Spring Boot")
        @NotBlank(message = "Le titre est obligatoire")
        @Size(min = 3, max = 200, message = "Le titre doit contenir entre 3 et 200 caractères")
        String titre,

        @Schema(example = "https://www.youtube.com/watch?v=abc123")
        @NotBlank(message = "L'URL est obligatoire")
        @Size(max = 500, message = "L'URL doit contenir au maximum 500 caractères")
        String url,

        @Schema(example = "1800")
        @NotNull(message = "La durée en secondes est obligatoire")
        @Positive(message = "La durée doit être positive")
        Integer dureeSecondes,

        @Schema(example = "1")
        @NotNull(message = "L'ID de la leçon est obligatoire")
        @Positive(message = "L'ID de la leçon doit être positif")
        Long leconId
) {}

