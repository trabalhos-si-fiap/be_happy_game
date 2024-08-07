package happygame.api.domain.repositories;

import happygame.api.domain.models.Player;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface PlayerRepository extends JpaRepository<Player, Long> {
    Page<Player> findAllByIsActiveTrue(Pageable pageable);

    UserDetails findByMail(String mail);
}
