package happygame.api.domain.dto;

import happygame.api.domain.models.Player;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record DetailPlayerDataDTO(
        Long id,
        String name,
        String mail,
        boolean isActive,
        LocalDateTime registerDate,
        LocalDateTime updatedAt,
        LocalDate birthDate
) {
    public DetailPlayerDataDTO(Player player){
        this(
                player.getId(),
                player.getName(),
                player.getMail(),
                player.isActive(),
                player.getRegisterDate(),
                player.getUpdatedAt(),
                player.getBirthDate()
        );
    }
}
