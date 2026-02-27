package ci.eduplatlearn.dto.lecon;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record LeconCreateRequestDTO(
        @Schema(example = "Introduction aux annotations Spring")
        @NotBlank(message = "Le titre est obligatoire")
        @Size(min = 3, max = 200, message = "Le titre doit contenir entre 3 et 200 caractères")
        String titre,

        @Schema(example = "Cette leçon explique les principales annotations de Spring Framework")
        @Size(max = 2000, message = "Le résumé doit contenir au maximum 2000 caractères")
        String resume,

        @Schema(example = "1")
        @NotNull(message = "L'ordre est obligatoire")
        @Positive(message = "L'ordre doit être positif")
        Integer ordre,

        @Schema(example = "45")
        @NotNull(message = "La durée en minutes est obligatoire")
        @Positive(message = "La durée doit être positive")
        Integer dureeMinutes,

        @Schema(example = "1")
        @NotNull(message = "L'ID du module est obligatoire")
        @Positive(message = "L'ID du module doit être positif")
        Long moduleId
) {}

