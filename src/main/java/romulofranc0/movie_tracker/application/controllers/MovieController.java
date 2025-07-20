package romulofranc0.movie_tracker.application.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import romulofranc0.movie_tracker.application.models.requests.MovieListRequest;
import romulofranc0.movie_tracker.application.models.responses.MovieResponse;
import romulofranc0.movie_tracker.application.models.responses.SearchResponseWrapper;
import romulofranc0.movie_tracker.domain.services.MovieListService;
import romulofranc0.movie_tracker.domain.services.OmdbService;

@RestController
@AllArgsConstructor
@RequestMapping("/api/movie")
public class MovieController {

    private final OmdbService omdbService;
    private final MovieListService movieListService;

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

    @PostMapping("/list")
    public ResponseEntity<?> createList(@RequestBody MovieListRequest request){
        var response = movieListService.createMovieList(request);
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
