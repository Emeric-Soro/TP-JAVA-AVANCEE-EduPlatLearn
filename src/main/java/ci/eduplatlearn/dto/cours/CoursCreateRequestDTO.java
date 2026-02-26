package ci.eduplatlearn.dto.cours;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CoursCreateRequestDTO(

        @Schema(example = "Programmation Java Avancée")
        @NotBlank(message = "Le titre est obligatoire")
        @Size(min = 3, max = 100, message = "Le titre doit contenir entre 3 et 100 caractères")
        String titre,

        @Schema(example = "Approfondissement JPA et Spring Boot")
        @NotBlank(message = "La description est obligatoire")
        @Size(min = 10, max = 1000, message = "La description doit contenir entre 10 et 1000 caractères")
        String description,

        @Schema(example = "Approfondissement JPA et Spring Boot")
        @NotBlank(message = "Le niveau est obligatoire")
        String niveau,

        @Schema(example = "false")
        @NotNull(message = "Le statut de publication est obligatoire")
        Boolean publie
) {}