package romulofranc0.movie_tracker.application.models.requests;

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
