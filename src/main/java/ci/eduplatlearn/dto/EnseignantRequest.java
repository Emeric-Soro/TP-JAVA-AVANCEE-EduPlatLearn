package ci.eduplatlearn.dto;

public record EnseignantRequest(
        String nom,
        String prenom,
        String email,
        String bio
) {}
