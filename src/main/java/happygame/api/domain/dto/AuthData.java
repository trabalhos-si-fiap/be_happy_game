package happygame.api.domain.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record AuthData(
        @NotBlank
        @Email
        String mail,
        @NotBlank
        String password
) {
}
