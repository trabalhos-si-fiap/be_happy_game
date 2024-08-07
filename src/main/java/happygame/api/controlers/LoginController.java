package happygame.api.controlers;

import happygame.api.dto.RegisterPlayerDTO;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class LoginController {
    @PostMapping("/register")
    public String register(@Valid @RequestBody RegisterPlayerDTO player) {

        return "Logado!";
    }
}
