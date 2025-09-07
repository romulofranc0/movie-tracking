package romulofranc0.movie_tracker.application.models.requests;

import java.time.LocalDate;

public record ReviewRequest(
        Long reviewId,
        String imdbId,
        Integer rating,
        String reviewText,
        LocalDate watchDate
) {
}
