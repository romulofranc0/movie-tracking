package romulofranc0.movie_tracker.application.models.responses;

import com.fasterxml.jackson.annotation.JsonProperty;

public record MovieResponse(
        String title,
        String director,
        String plot,
        String year,
        String genre,
        String imdbID,
        String poster,
        String imdbRating
) {
}
