package happygame.api.controlers;

import happygame.api.dto.AlterPlayerDTO;
import happygame.api.dto.ListDataPlayerDTO;
import happygame.api.dto.RegisterPlayerDTO;
import happygame.api.models.Player;
import happygame.api.repositories.PlayerRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/player")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PlayerController {

    @Autowired
    private PlayerRepository playerRepository;

    @PostMapping("/register")
    @Transactional
    public String register(@Valid @RequestBody RegisterPlayerDTO player) {
        playerRepository.save(new Player(player));
        return "Registrado!";
    }

    @GetMapping
    public Page<ListDataPlayerDTO> listPlayers(@PageableDefault(size=10, sort={"id"}) Pageable pageable){
        return playerRepository.findAllByIsActiveTrue(pageable).map(ListDataPlayerDTO::new);
    }

    @PutMapping
    @Transactional
    public void alterPlayer(@RequestBody @Valid AlterPlayerDTO player) {
        Player player1 = playerRepository.getReferenceById(player.id());
        player1.alterData(player);
    }


    @DeleteMapping("/{id}")
    @Transactional
    public Long delete(@PathVariable Long id) {
        Player player1 = playerRepository.getReferenceById(id);

        player1.disable();

        return id;
    }
}
