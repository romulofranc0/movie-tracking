package romulofranc0.movie_tracker.domain.exceptions;

public class MovieAlreadyInListException extends RuntimeException {
    public MovieAlreadyInListException(String message) {
        super(message);
    }
}
