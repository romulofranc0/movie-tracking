package romulofranc0.movie_tracker.application.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import romulofranc0.movie_tracker.application.models.requests.CommentRequest;
import romulofranc0.movie_tracker.application.models.requests.ReviewRequest;
import romulofranc0.movie_tracker.application.models.responses.ReviewResponse;
import romulofranc0.movie_tracker.domain.entities.Review;
import romulofranc0.movie_tracker.domain.services.ReviewService;
import romulofranc0.movie_tracker.infra.repositories.ReviewRepository;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/reviews")
public class ReviewController {
    private final ReviewService reviewService;

    @PostMapping
    public ResponseEntity<?> createReview(@RequestBody ReviewRequest reviewRequest) {
        reviewService.createReview(reviewRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<ReviewResponse> getReviewById(@PathVariable Long id){
        return ResponseEntity.ok(reviewService.getReviewById(id));
    }

    @GetMapping("/imdb/{imdbId}")
    public ResponseEntity<ReviewResponse> getReviewByImdbId(@PathVariable String imdbId){
        return ResponseEntity.ok(reviewService.getReviewByImdbId(imdbId));
    }

    @GetMapping("/verify/{imdbId}")
    public boolean verifyReview(@PathVariable String imdbId) {
        return reviewService.reviewsExists(imdbId);
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<Void> deleteReviewById(@PathVariable Long reviewId){

        reviewService.deleteById(reviewId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<Void> updateReviewById(@RequestBody ReviewRequest reviewRequest) {
    reviewService.updateReview(reviewRequest);
    return ResponseEntity.noContent().build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<ReviewResponse>> getAllReviews(){
        return ResponseEntity.ok(reviewService.getAllReviews());
    }

    @PostMapping("/comment")
    public ResponseEntity<?> createComment(@RequestBody CommentRequest commentRequest){
        reviewService.createComment(commentRequest);
       return ResponseEntity.noContent().build();
    }


}
