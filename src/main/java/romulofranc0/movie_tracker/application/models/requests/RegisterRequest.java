package romulofranc0.movie_tracker.application.models.requests;

import romulofranc0.movie_tracker.domain.entities.Role;

public record RegisterRequest(
        String username,
        String password,
        String email,
        Role role
) {
}
