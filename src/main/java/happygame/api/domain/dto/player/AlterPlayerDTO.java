package happygame.api.domain.dto.player;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record AlterPlayerDTO(
        @NotNull
        Long id,
        String name,
        LocalDate birthDate,
        Boolean isActive
) {
}
