package romulofranc0.movie_tracker.application.models.responses;

import java.time.LocalDate;

public record ReviewResponse(
        Long reviewId,
        String imdbId,
        Integer rating,
        String reviewText,
        LocalDate watchDate
) {

}
