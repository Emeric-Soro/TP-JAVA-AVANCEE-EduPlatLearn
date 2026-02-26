package ci.eduplatlearn.dto.cours;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CoursUpdateRequestDTO(

        @NotBlank(message = "Le titre est obligatoire")
        @Size(min = 3, max = 100)
        String titre,

        @NotBlank(message = "La description est obligatoire")
        @Size(min = 10, max = 1000)
        String description,

        @NotBlank(message = "Le niveau est obligatoire")
        String niveau,

        @NotNull(message = "Le statut de publication est obligatoire")
        Boolean publie
) {}