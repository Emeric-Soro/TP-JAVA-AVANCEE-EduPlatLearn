package ci.eduplatlearn.dto.Enseignant;

import java.time.LocalDateTime;

public record EnseignantResponseDTO (
     Long id,
     String nom,
     String prenom,
     String email,
     String bio,
     LocalDateTime createdAt,
     LocalDateTime updatedAt
){}
