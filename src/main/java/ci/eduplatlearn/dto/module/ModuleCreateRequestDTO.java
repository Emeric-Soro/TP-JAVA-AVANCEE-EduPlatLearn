package ci.eduplatlearn.dto.module;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record ModuleCreateRequestDTO(
        @Schema(example = "Introduction à Spring Boot")
        @NotBlank(message = "Le titre est obligatoire")
        @Size(min = 3, max = 200, message = "Le titre doit contenir entre 3 et 200 caractères")
        String titre,

        @Schema(example = "Ce module couvre les bases de Spring Boot")
        @Size(max = 2000, message = "La description doit contenir au maximum 2000 caractères")
        String description,

        @Schema(example = "1")
        @NotNull(message = "L'ordre est obligatoire")
        @Positive(message = "L'ordre doit être positif")
        Integer ordre,

        @Schema(example = "1")
        @NotNull(message = "L'ID du cours est obligatoire")
        @Positive(message = "L'ID du cours doit être positif")
        Long coursId
) {}

