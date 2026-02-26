package ci.eduplatlearn.dto.cours;

import java.time.LocalDateTime;

public record CoursResponseDTO(
        Long id,
        String titre,
        String description,
        String niveau,
        Boolean publie,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {}
