package happygame.api.domain.dto.games;

public record RegisterGameDTO(
        String name,
        String description,
        String category,
        String link,
        int recommendedAge
) {
}
