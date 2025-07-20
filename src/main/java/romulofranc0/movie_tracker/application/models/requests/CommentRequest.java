package romulofranc0.movie_tracker.application.models.requests;

public record CommentRequest(
        String comment,
        Long reviewId
) {
}
