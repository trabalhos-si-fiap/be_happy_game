package happygame.api.domain.dto.games;

public record AlterGameDTO(
        Long id,
        String name,
        String description,
        String category,
        String link,
        Boolean isActive,
        Integer recommendedAge

) {
}
