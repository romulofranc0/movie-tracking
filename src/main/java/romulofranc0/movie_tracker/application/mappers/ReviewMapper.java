package romulofranc0.movie_tracker.application.mappers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import romulofranc0.movie_tracker.application.models.responses.ReviewResponse;
import romulofranc0.movie_tracker.domain.entities.Review;
import romulofranc0.movie_tracker.domain.exceptions.MovieNotFoundException;
import romulofranc0.movie_tracker.infra.repositories.MovieRepository;

import java.util.HashSet;
import java.util.Set;

@RequiredArgsConstructor
@Component
public class ReviewMapper {

    private final MovieMapper movieMapper;
    private final MovieRepository movieRepository;

    public ReviewResponse toReviewResponse(Review review) {
        ReviewResponse reviewResponse = new ReviewResponse(
                review.getId(),
                review.getMovie().getImdbID(),
                review.getRating(),
                review.getReviewText(),
                review.getWatchDate(),
                movieMapper.toMovieResponse(movieRepository
                        .findByImdbId(review
                                .getMovie()
                                .getImdbID())
                        .orElseThrow(() -> new MovieNotFoundException("Movie not found")))
        );
        return reviewResponse;
    }

    public Set<ReviewResponse> toReviewResponses(Set<Review> reviews){
    Set<ReviewResponse> responses = new HashSet<>();
        for (Review review : reviews) {
            ReviewResponse reviewResponse = new ReviewResponse(
                    review.getId(),
                    review.getMovie().getImdbID(),
                    review.getRating(),
                    review.getReviewText(),
                    review.getWatchDate(),
                    movieMapper.toMovieResponse(movieRepository
                            .findByImdbId(review
                                    .getMovie()
                                    .getImdbID())
                            .orElseThrow(() -> new MovieNotFoundException("Movie not found")))
            );
            responses.add(reviewResponse);
        }
        return responses;
    }
}
