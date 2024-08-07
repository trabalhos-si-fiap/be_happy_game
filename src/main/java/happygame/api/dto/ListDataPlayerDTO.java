package happygame.api.dto;

import happygame.api.models.Player;

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
