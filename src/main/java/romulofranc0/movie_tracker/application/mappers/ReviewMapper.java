package romulofranc0.movie_tracker.application.mappers;

import org.springframework.stereotype.Component;
import romulofranc0.movie_tracker.application.models.responses.ReviewResponse;
import romulofranc0.movie_tracker.domain.entities.Review;

import java.util.HashSet;
import java.util.Set;

@Component
public class ReviewMapper {

    public Set<ReviewResponse> toReviewResponses(Set<Review> reviews){
    Set<ReviewResponse> responses = new HashSet<>();
        for (Review review : reviews) {
            ReviewResponse reviewResponse = new ReviewResponse(
                    review.getUser().getId(),
                    review.getMovie().getImdbID(),
                    review.getRating(),
                    review.getComment(),
                    review.getWatchDate()
            );
            responses.add(reviewResponse);
        }
        return responses;
    }
}
