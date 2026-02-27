package ci.eduplatlearn.dto.fichier;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record FichierCreateRequestDTO(
        @Schema(example = "Documentation Spring Boot PDF")
        @NotBlank(message = "Le titre est obligatoire")
        @Size(min = 3, max = 200, message = "Le titre doit contenir entre 3 et 200 caractères")
        String titre,

        @Schema(example = "spring-boot-guide.pdf")
        @NotBlank(message = "Le nom du fichier est obligatoire")
        @Size(max = 255, message = "Le nom du fichier doit contenir au maximum 255 caractères")
        String nomFichier,

        @Schema(example = "/uploads/cours/spring-boot-guide.pdf")
        @NotBlank(message = "Le chemin de stockage est obligatoire")
        @Size(max = 500, message = "Le chemin de stockage doit contenir au maximum 500 caractères")
        String cheminStockage,

        @Schema(example = "1")
        @NotNull(message = "L'ID de la leçon est obligatoire")
        @Positive(message = "L'ID de la leçon doit être positif")
        Long leconId
) {}

