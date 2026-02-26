package ci.eduplatlearn.dto.cours;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CoursCreateRequestDTO(

        @NotBlank(message = "Le titre est obligatoire")
        @Size(min = 3, max = 100, message = "Le titre doit contenir entre 3 et 100 caractères")
        String titre,

        @NotBlank(message = "La description est obligatoire")
        @Size(min = 10, max = 1000, message = "La description doit contenir entre 10 et 1000 caractères")
        String description,

        @NotBlank(message = "Le niveau est obligatoire")
        String niveau,

        @NotNull(message = "Le statut de publication est obligatoire")
        Boolean publie
) {}