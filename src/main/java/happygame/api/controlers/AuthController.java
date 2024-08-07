package happygame.api.controlers;

import happygame.api.domain.dto.AuthData;
import happygame.api.infra.security.TokenService;
import happygame.api.infra.security.dto.JWTTokenDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthController {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity login(@RequestBody @Valid AuthData data) {

        var authToken = new UsernamePasswordAuthenticationToken(data.mail(), data.password());
        var authentication = authManager.authenticate(authToken);

        var tokenJTW = new JWTTokenDTO(tokenService.generateToken((UserDetails) authentication.getPrincipal()));

        return ResponseEntity.ok(tokenJTW);
    }

}
