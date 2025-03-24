package romulofranc0.movie_tracker.domain.services;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import romulofranc0.movie_tracker.application.models.requests.ReviewRequest;
import romulofranc0.movie_tracker.application.models.responses.MovieResponse;
import romulofranc0.movie_tracker.application.models.responses.ReviewResponse;
import romulofranc0.movie_tracker.domain.entities.AppUser;
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
        if(!reviewRepository.existsReviewByMovieImdbID(reviewRequest.imdbId())){

        MovieResponse omdbResponse = omdbService.getMovie(reviewRequest.imdbId());
        AppUser user = userRepository.findById(reviewRequest.userId()).orElseThrow(() -> new RuntimeException("User not found"));

        Movie movie = new Movie();
        movie.setDirector(omdbResponse.Director());
        movie.setGenre(omdbResponse.Genre());
        movie.setTitle(omdbResponse.Title());
        movie.setYear(omdbResponse.Year());
        movie.setPlot(omdbResponse.Plot());
        movie.setPoster(omdbResponse.Poster());
        movie.setImdbID(omdbResponse.imdbID());

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
       ReviewResponse reviewResponse = new ReviewResponse(
                review.getId(),
                review.getMovie().getImdbID(),
                review.getRating(),
                review.getComment(),
                review.getWatchDate());
        return reviewResponse;
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
