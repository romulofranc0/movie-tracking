package romulofranc0.movie_tracker.application.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import romulofranc0.movie_tracker.domain.services.FeedService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/feed")
public class FeedController {

    private final FeedService feedService;

    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserFeed(@PathVariable("userId") Long userId) {
        return ResponseEntity.ok().body(feedService.getUserFeed(userId));
    }
}
