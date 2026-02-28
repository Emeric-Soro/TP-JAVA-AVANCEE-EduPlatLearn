package ci.eduplatlearn.dto.module;

import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import io.swagger.v3.oas.annotations.media.Schema;



public record ModuleUpdateRequestDTO(
        @Positive(message = "L'ordre doit être positif")
        @NotNull(message = "L'ordre est obligatoire")
        @Schema(example = "1")
        Integer ordre,

        @Size(max = 2000, message = "La description doit contenir au maximum 2000 caractères")
        @Schema(example = "Ce module couvre les bases de Spring Boot")
        String description,

        @Size(min = 3, max = 200, message = "Le titre doit contenir entre 3 et 200 caractères")
        @NotBlank(message = "Le titre est obligatoire")
        @Schema(example = "Introduction à Spring Boot - Mise à jour")
        String titre

) {
}