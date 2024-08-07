package happygame.api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

public record RegisterPlayerDTO(
        @NotBlank
        String name,
        @NotBlank
        @Email
        String mail,
        @NotBlank
        @Length(min = 8, max = 255)
        @Pattern(regexp = "^(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?])(?=.*[A-Z])(?=\\S+$)(?!.*(012345678|00000000|11111111|22222222|33333333|44444444|55555555|66666666|77777777|88888888|99999999|123456789|23456789|3456789|987654321|87654321|7654321))[A-Za-z0-9!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?]{8,}$")
        String password,
        @NotNull
        LocalDate birthDate
) {
}
