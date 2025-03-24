package romulofranc0.movie_tracker.application.models.requests;

import jakarta.persistence.*;
import romulofranc0.movie_tracker.domain.entities.AppUser;
import romulofranc0.movie_tracker.domain.entities.Movie;

import java.time.LocalDate;

public record ReviewRequest(
        Long reviewId,
        Long userId,
        String imdbId,
        Float rating,
        String comment,
        LocalDate watchDate
) {
}
