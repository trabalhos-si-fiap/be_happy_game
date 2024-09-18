package happygame.api.controlers;

import happygame.api.domain.dto.games.AlterGameDTO;
import happygame.api.domain.dto.games.DetailGameDTO;
import happygame.api.domain.dto.games.RegisterGameDTO;
import happygame.api.domain.dto.player.AlterPlayerDTO;
import happygame.api.domain.dto.player.DetailPlayerDataDTO;
import happygame.api.domain.dto.player.ListDataPlayerDTO;
import happygame.api.domain.dto.player.RegisterPlayerDTO;
import happygame.api.domain.models.Games;
import happygame.api.domain.models.Player;
import happygame.api.domain.repositories.GamesRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/game")
@SecurityRequirement(name="bearer-key")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class GamesController {

    @Autowired
    private GamesRepository gamesRepository;

    @PostMapping("/register")

    @Transactional
    public ResponseEntity<DetailGameDTO> register(@Valid @RequestBody RegisterGameDTO newGameDTO, UriComponentsBuilder uriBuilder) {
        var gameCreated = new Games(newGameDTO);
        gamesRepository.save(gameCreated);

        var uri = uriBuilder.path("/register/{id}").buildAndExpand(gameCreated.getId()).toUri();

        return ResponseEntity.created(uri).body(new DetailGameDTO(gameCreated));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetailGameDTO> detailPlayer(@PathVariable Long id) {
        Games game = gamesRepository.getReferenceById(id);
        return ResponseEntity.ok(new DetailGameDTO(game));
    }

    @GetMapping
    public ResponseEntity<Page<DetailGameDTO>> listPlayers(@PageableDefault(size=10, sort={"id"}) Pageable pageable){
        var page = gamesRepository.findAllByIsActiveTrue(pageable).map(DetailGameDTO::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity alterPlayer(@RequestBody @Valid AlterGameDTO alterGame) {
        Games game = gamesRepository.getReferenceById(alterGame.id());
        game.alterData(alterGame);
        return ResponseEntity.ok(new DetailGameDTO(game));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable Long id) {
        Games game = gamesRepository.getReferenceById(id);
        game.disable();
        return ResponseEntity.noContent().build();
    }
}
