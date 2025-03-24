package romulofranc0.movie_tracker.application.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import romulofranc0.movie_tracker.application.models.requests.ReviewRequest;
import romulofranc0.movie_tracker.application.models.responses.MovieResponse;
import romulofranc0.movie_tracker.application.models.responses.SearchResponse;
import romulofranc0.movie_tracker.application.models.responses.SearchResponseWrapper;
import romulofranc0.movie_tracker.domain.services.OmdbService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/movie")
public class MovieController {

    private final OmdbService omdbService;

    @GetMapping("/search")
    public ResponseEntity<SearchResponseWrapper> searchMovie(@RequestParam String title) {
        SearchResponseWrapper searchResponse = omdbService.search(title);
        return ResponseEntity.ok(searchResponse);
    }

    @GetMapping()
    public ResponseEntity<MovieResponse> getMovie(@RequestParam String imdbId) {
        MovieResponse movieResponse = omdbService.getMovie(imdbId);
        return ResponseEntity.ok(movieResponse);
    }


}
