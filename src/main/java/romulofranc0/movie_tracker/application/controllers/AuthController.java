package romulofranc0.movie_tracker.application.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import romulofranc0.movie_tracker.application.models.requests.AuthRequest;
import romulofranc0.movie_tracker.application.models.requests.RegisterRequest;
import romulofranc0.movie_tracker.application.models.responses.LoginResponse;
import romulofranc0.movie_tracker.domain.entities.AppUser;
import romulofranc0.movie_tracker.domain.exceptions.UserAlreadyExistsException;
import romulofranc0.movie_tracker.domain.services.AuthService;
import romulofranc0.movie_tracker.infra.repositories.UserRepository;
import romulofranc0.movie_tracker.infra.security.services.TokenService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final AuthService authService;
    private final UserRepository userRepository;
    private final TokenService tokenService;


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid AuthRequest authRequest) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(authRequest.username(), authRequest.password());
        var auth = authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((AppUser)auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponse(token));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid RegisterRequest registerRequest) {
        if (userRepository.findByUsername(registerRequest.username()).isPresent()) {
            throw new UserAlreadyExistsException("User already registered");
        }
        String encryptedPassword = new BCryptPasswordEncoder().encode(registerRequest.password());
        AppUser user = new AppUser(registerRequest.username(), encryptedPassword,registerRequest.email(), registerRequest.role());

        userRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }
}
