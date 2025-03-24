package romulofranc0.movie_tracker.domain.exceptions;

public class ReviewNotFoundException extends RuntimeException {
    public ReviewNotFoundException(Long reviewId) {
        super("Review with id " + reviewId + " not found");
    }
}
