package ci.eduplatlearn.dto;

public record CoursRequest(
        String titre,
        String description,
        String niveau,
        boolean publie
) {
}
