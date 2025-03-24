package romulofranc0.movie_tracker.domain.exceptions;

public class ReviewAlreadyExistsException extends RuntimeException {
    public ReviewAlreadyExistsException(String imdbID) {
        super("Review already exists for movie with ID: "+imdbID);
    }
}
