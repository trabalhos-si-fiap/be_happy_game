package happygame.api.dto;

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