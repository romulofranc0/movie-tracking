package romulofranc0.movie_tracker.application.models.responses;

import java.util.Set;

public record MovieListResponse(
        Long id,
        String listName,
        Set<MovieResponse> movieList
) {
}
