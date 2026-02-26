package ci.eduplatlearn.dto;

public record CoursView(
        Long id,
        String titre,
        String description,
        String niveau,
        boolean publie) {
}