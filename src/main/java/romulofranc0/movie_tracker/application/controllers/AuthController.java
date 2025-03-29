package romulofranc0.movie_tracker.application.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import jakarta.validation.Valid;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import romulofranc0.movie_tracker.application.models.requests.AuthRequest;
import romulofranc0.movie_tracker.application.models.requests.RegisterRequest;
import romulofranc0.movie_tracker.application.models.responses.LoginResponse;
import romulofranc0.movie_tracker.domain.entities.AppUser;
import romulofranc0.movie_tracker.domain.services.AuthService;
import romulofranc0.movie_tracker.infra.repositories.UserRepository;
import romulofranc0.movie_tracker.infra.security.services.TokenService;

@RestController
@RequestMapping("auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private AuthService authService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TokenService tokenService;


    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthRequest authRequest) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(authRequest.username(), authRequest.password());
        var auth = authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((AppUser)auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponse(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterRequest registerRequest) {
        if (userRepository.findByUsername(registerRequest.username()) != null) {
            return ResponseEntity.badRequest().build();
        }
        String encryptedPassword = new BCryptPasswordEncoder().encode(registerRequest.password());
        AppUser user = new AppUser(registerRequest.username(), encryptedPassword,registerRequest.email(),registerRequest.role());

        userRepository.save(user);
        return ResponseEntity.ok().build();
    }
}
