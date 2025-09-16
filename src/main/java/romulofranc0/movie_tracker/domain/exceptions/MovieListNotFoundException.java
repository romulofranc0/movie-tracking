package romulofranc0.movie_tracker.domain.exceptions;

public class MovieListNotFoundException extends RuntimeException {
    public MovieListNotFoundException(String message) {
        super(message);
    }
}
