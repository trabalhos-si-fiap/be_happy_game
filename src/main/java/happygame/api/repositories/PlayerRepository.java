package happygame.api.repositories;

import happygame.api.models.Player;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Long> {
    Page<Player> findAllByIsActiveTrue(Pageable pageable);
}
