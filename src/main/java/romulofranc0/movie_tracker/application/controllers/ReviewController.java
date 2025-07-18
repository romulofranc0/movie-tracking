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

    @PostMapping
    public void createReview(@RequestBody ReviewRequest reviewRequest) {
        reviewService.createReview(reviewRequest);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReviewResponse> getReviewById(@PathVariable Long id){
        return ResponseEntity.ok(reviewService.getReviewById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReviewById(@PathVariable Long id){

        reviewService.deleteById(id);
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
}
