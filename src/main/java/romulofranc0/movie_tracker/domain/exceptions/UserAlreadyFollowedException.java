package romulofranc0.movie_tracker.domain.exceptions;

public class UserAlreadyFollowedException extends RuntimeException {
    public UserAlreadyFollowedException(String message) {
        super(message);
    }
}
