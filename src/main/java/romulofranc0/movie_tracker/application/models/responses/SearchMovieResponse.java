package romulofranc0.movie_tracker.application.models.responses;

public record SearchMovieResponse(
        String title,
        String year,
        String type,
        String imdbID,
        String poster
) {
}
