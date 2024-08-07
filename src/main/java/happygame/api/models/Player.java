package happygame.api.models;


import happygame.api.dto.AlterPlayerDTO;
import happygame.api.dto.RegisterPlayerDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Table(name="players")
@Entity(name="Player")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class Player {
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

    public Player(RegisterPlayerDTO player){
        name = player.name();
        mail = player.mail();
        password = player.password();
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
}
