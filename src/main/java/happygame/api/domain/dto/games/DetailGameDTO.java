package happygame.api.domain.dto.games;

import happygame.api.domain.models.Games;

public record DetailGameDTO(
        String name,
        String description,
        String category,
        String link,
        int recommendedAge
) {

    public DetailGameDTO(Games gameCreated) {
        this(
                gameCreated.getName(),
                gameCreated.getDescription(),
                gameCreated.getCategory(),
                gameCreated.getLink(),
                gameCreated.getRecommendedAge()
        );
    }
}
