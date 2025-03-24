package romulofranc0.movie_tracker.application.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import romulofranc0.movie_tracker.application.models.requests.ReviewRequest;
import romulofranc0.movie_tracker.application.models.responses.ReviewResponse;
import romulofranc0.movie_tracker.domain.entities.Review;
import romulofranc0.movie_tracker.domain.services.ReviewService;
import romulofranc0.movie_tracker.infra.repositories.ReviewRepository;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/review")
public class ReviewController {
    private final ReviewService reviewService;
    private final ReviewRepository reviewRepository;

    @PostMapping
    public void createReview(@RequestBody ReviewRequest reviewRequest) {
        reviewService.createReview(reviewRequest);
    }

    @GetMapping
    public ResponseEntity<ReviewResponse> getReviewById(@RequestParam Long id){
        return ResponseEntity.ok(reviewService.getReviewById(id));
    }

    @DeleteMapping
    public void deleteReviewById(@RequestParam Long id){
        reviewService.deleteById(id);
    }

    @PutMapping
    public void updateReviewById(@RequestBody ReviewRequest reviewRequest) {
    reviewService.updateReview(reviewRequest);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ReviewResponse>> getAllReviews(){
        return ResponseEntity.ok(reviewService.getAllReviews());
    }
}
