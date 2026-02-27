package ci.eduplatlearn.dto;

public record EnseignantView(
        Long id,
        String nom,
        String prenom,
        String email,
        String bio
) {}
