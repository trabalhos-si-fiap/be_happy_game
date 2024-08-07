package happygame.api.controlers;

import happygame.api.domain.dto.AlterPlayerDTO;
import happygame.api.domain.dto.DetailPlayerDataDTO;
import happygame.api.domain.dto.ListDataPlayerDTO;
import happygame.api.domain.dto.RegisterPlayerDTO;
import happygame.api.domain.models.Player;
import happygame.api.domain.repositories.PlayerRepository;
import happygame.api.infra.security.SecurityConfigurations;
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
@RequestMapping("/player")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PlayerController {

    @Autowired
    private PlayerRepository playerRepository;

    @PostMapping("/register")
    @Transactional
    public ResponseEntity register(@Valid @RequestBody RegisterPlayerDTO playerDTO, UriComponentsBuilder uriBuilder) {
        var playerCreated = new Player(playerDTO);
        playerRepository.save(playerCreated);

        var uri = uriBuilder.path("/register/{id}").buildAndExpand(playerCreated.getId()).toUri();

        return ResponseEntity.created(uri).body(new DetailPlayerDataDTO(playerCreated));
    }

    @SecurityRequirement(name="bearer-key")
    @GetMapping("/{id}")
    public ResponseEntity<DetailPlayerDataDTO> detailPlayer(@PathVariable Long id) {
        Player player1 = playerRepository.getReferenceById(id);
        return ResponseEntity.ok(new DetailPlayerDataDTO(player1));
    }

    @SecurityRequirement(name="bearer-key")
    @GetMapping
    public ResponseEntity<Page<ListDataPlayerDTO>> listPlayers(@PageableDefault(size=10, sort={"id"}) Pageable pageable){
        var page =  playerRepository.findAllByIsActiveTrue(pageable).map(ListDataPlayerDTO::new);
        return ResponseEntity.ok(page);
    }

    @SecurityRequirement(name="bearer-key")
    @PutMapping
    @Transactional
    public ResponseEntity alterPlayer(@RequestBody @Valid AlterPlayerDTO player) {
        Player player1 = playerRepository.getReferenceById(player.id());
        player1.alterData(player);
        return ResponseEntity.ok(new DetailPlayerDataDTO(player1));
    }

    @SecurityRequirement(name="bearer-key")
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable Long id) {
        Player player1 = playerRepository.getReferenceById(id);
        player1.disable();
        return ResponseEntity.noContent().build();
    }
}
