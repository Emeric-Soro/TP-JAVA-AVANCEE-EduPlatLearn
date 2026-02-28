package ci.eduplatlearn.dto.Enseignant;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record EnseignantUpdateRequestDTO(
        @NotBlank(message = "Le nom de l'enseignant est obligatoire")
        @Size(min = 1, max = 100, message = "Le nom de l'enseignant doit contenir au maximum 100 caractères")
        String nom,

        @NotBlank(message = "Le prénom de l'enseignant est obligatoire")
        @Size(min = 1, max = 100, message = "Le prénom de l'enseignant doit contenir au maximum 100 caractères")
        String prenom,

        @NotBlank(message = "L'email de l'enseignant est obligatoire")
        @Size(max = 100, message = "L'email de l'enseignant doit contenir au maximum 100 caractères")
        String email,

        @Size(max = 1000, message = "La bio de l'enseignant doit contenir au maximum 1000 caractères")
        String bio
) {}
