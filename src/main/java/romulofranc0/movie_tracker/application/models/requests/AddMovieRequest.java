package romulofranc0.movie_tracker.application.models.requests;

public record AddMovieRequest(
        String imdbId,
        Long listId
) {
}
