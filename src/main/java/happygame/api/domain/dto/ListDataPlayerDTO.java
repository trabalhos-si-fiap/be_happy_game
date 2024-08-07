package happygame.api.domain.dto;

import happygame.api.domain.models.Player;

import java.time.LocalDate;

public record ListDataPlayerDTO(
        Long id,
        String name,
        String mail,
        LocalDate birthDate
) {
    public ListDataPlayerDTO(Player player) {
        this(
                player.getId(),
                player.getName(),
                player.getMail(),
                player.getBirthDate()
        );
    }
}
