package happygame.api.domain.models;


import happygame.api.domain.dto.RegisterPlayerDTO;
import happygame.api.domain.dto.AlterPlayerDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import static happygame.api.infra.security.SecurityConfigurations.passwordEncoder;

@Table(name="players")
@Entity(name="Player")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class Player  implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(min = 3, max=50, message = "O atributo nome deve conter no minimo 3 e no máximo 50 caracteres")
    private String name;
    @Column(unique = true)
    private String mail;
    private String password;
    private boolean isActive;
    private LocalDateTime registerDate;
    private LocalDateTime updatedAt;
    private LocalDate birthDate;

    public Player(RegisterPlayerDTO player) {
        name = player.name();
        mail = player.mail();
        password = passwordEncoder().encode(player.password());
        birthDate = player.birthDate();

        registerDate = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
        isActive = true;
    }
    public boolean isOfAge() {
        return true;
    }

    public void alterData(AlterPlayerDTO player) {
        if (player.name() != null) {
            this.name = player.name();
        }
        if (player.birthDate() != null) {
            this.birthDate = player.birthDate();
        }
        if (player.isActive() == false || player.isActive() == true) {
            this.isActive = player.isActive();
        }

        this.updatedAt = LocalDateTime.now();
    }

    public void disable() {
        this.isActive = false;
        this.updatedAt = LocalDateTime.now();
    }
    public void enable() {
        this.isActive = true;
        this.updatedAt = LocalDateTime.now();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getUsername() {
        return mail;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.isActive;
    }
}
