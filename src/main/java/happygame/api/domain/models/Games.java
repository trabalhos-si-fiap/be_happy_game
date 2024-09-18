package happygame.api.domain.models;


import happygame.api.domain.dto.games.AlterGameDTO;
import happygame.api.domain.dto.games.RegisterGameDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name="games")
@Entity(name="Game")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class Games {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String description;
    private String category;
    private String link;
    private Integer recommendedAge;
    private Boolean isActive;


    public Games(RegisterGameDTO newGameDTO) {
        this.name = newGameDTO.name();
        this.description = newGameDTO.description();
        this.category = newGameDTO.category();
        this.link = newGameDTO.link();
        this.recommendedAge = newGameDTO.recommendedAge();
        this.isActive = true;
    }

    public void alterData(AlterGameDTO game) {
        if (game.name() != null) {
            this.name = game.name();
        }
        if (game.description() != null) {
            this.description = game.description();
        }
        if (game.category() != null) {
            this.category = game.category();
        }
        if (game.link() != null) {
            this.link = game.link();
        }
        if (game.recommendedAge() != null) {
            this.recommendedAge = game.recommendedAge();
        }
        if (game.isActive() != null) {
            this.isActive = game.isActive();
        }
    }

    public void disable() {
        isActive = false;
    }

    public void enable() {
        isActive = true;
    }
}
