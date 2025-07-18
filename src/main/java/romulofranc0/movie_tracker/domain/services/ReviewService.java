package romulofranc0.movie_tracker.domain.services;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import romulofranc0.movie_tracker.application.models.requests.ReviewRequest;
import romulofranc0.movie_tracker.application.models.responses.MovieResponse;
import romulofranc0.movie_tracker.application.models.responses.ReviewResponse;
import romulofranc0.movie_tracker.domain.entities.User;
import romulofranc0.movie_tracker.domain.entities.Movie;
import romulofranc0.movie_tracker.domain.entities.Review;
import romulofranc0.movie_tracker.domain.exceptions.ReviewAlreadyExistsException;
import romulofranc0.movie_tracker.domain.exceptions.ReviewNotFoundException;
import romulofranc0.movie_tracker.infra.repositories.MovieRepository;
import romulofranc0.movie_tracker.infra.repositories.ReviewRepository;
import romulofranc0.movie_tracker.infra.repositories.UserRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final MovieRepository movieRepository;
    private final OmdbService omdbService;

    @Transactional
    public void createReview(ReviewRequest reviewRequest){
        final int MAX_COMMENT_LENGTH = 500;

        if(reviewRequest.rating() > 10 || reviewRequest.rating() < 0){
            throw new IllegalArgumentException("Rating must be between 0 and 10");
        }

        if (reviewRequest.comment() != null && reviewRequest.comment().length() > MAX_COMMENT_LENGTH) {
            throw new IllegalArgumentException("Comment cannot exceed " + MAX_COMMENT_LENGTH + " characters");
        }
        if(!reviewRepository.existsReviewByMovieImdbIDAndUserId(reviewRequest.imdbId(),reviewRequest.userId())) {

        MovieResponse omdbResponse = omdbService.getMovie(reviewRequest.imdbId());
        User user = userRepository.findById(reviewRequest.userId()).orElseThrow(() -> new RuntimeException("User not found"));

        Movie movie = new Movie();
        movie.setDirector(omdbResponse.Director());
        movie.setGenre(omdbResponse.Genre());
        movie.setTitle(omdbResponse.Title());
        movie.setYear(omdbResponse.Year());
        movie.setPlot(omdbResponse.Plot());
        movie.setPoster(omdbResponse.Poster());
        movie.setImdbID(omdbResponse.imdbID());
        movieRepository.save(movie);

        Review review = Review.builder()
                .reviewDate(LocalDate.now())
                .user(user)
                .movie(movie)
                .comment(reviewRequest.comment())
                .rating(reviewRequest.rating())
                .watchDate(reviewRequest.watchDate())
                .build();

           reviewRepository.save(review);


        }else {
            throw new ReviewAlreadyExistsException(reviewRequest.imdbId());
        }

    }

    public ReviewResponse getReviewById(Long reviewId){
       Review review = reviewRepository.findById(reviewId).orElseThrow(() -> new ReviewNotFoundException(reviewId));
        return new ReviewResponse(
                 review.getId(),
                 review.getMovie().getImdbID(),
                 review.getRating(),
                 review.getComment(),
                 review.getWatchDate());
    }

    public void updateReview(ReviewRequest reviewRequest){
        Review review = reviewRepository.findById(reviewRequest.reviewId()).orElseThrow(() -> new ReviewNotFoundException(reviewRequest.reviewId()));
        if(reviewRequest.comment() != null)review.setComment(reviewRequest.comment());
        if(reviewRequest.rating() != null)review.setRating(reviewRequest.rating());
        if(reviewRequest.watchDate() != null)review.setWatchDate(reviewRequest.watchDate());
        reviewRepository.save(review);
    }

    @Transactional
    public void deleteById(Long reviewId){
        reviewRepository.deleteById(reviewId);
    }

    public List<ReviewResponse> getAllReviews() {
        return reviewRepository.findAll().stream()
                .map(review -> new ReviewResponse(
                        review.getUser().getId(),
                        review.getMovie().getImdbID(),
                        review.getRating(),
                        review.getComment(),
                        review.getWatchDate()
                ))
                .collect(Collectors.toList());
    }
}
