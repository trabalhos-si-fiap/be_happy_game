package happygame.api.domain.repositories;

import aj.org.objectweb.asm.commons.Remapper;
import happygame.api.domain.models.Games;
import happygame.api.domain.models.Player;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GamesRepository extends JpaRepository<Games, Long> {
    Page<Games> findAllByIsActiveTrue(Pageable pageable);
}
