package romulofranc0.movie_tracker.application.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import romulofranc0.movie_tracker.application.models.responses.*;
import romulofranc0.movie_tracker.domain.services.MovieListService;
import romulofranc0.movie_tracker.domain.services.OmdbService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/movie")
public class MovieController {

    private final OmdbService omdbService;
    private final MovieListService movieListService;

    @GetMapping("/search")
    public ResponseEntity<List<SearchMovieResponse>> searchMovie(@RequestParam String title) {
        return ResponseEntity.ok(omdbService.search(title));
    }

    @GetMapping()
    public ResponseEntity<MovieResponse> getMovie(@RequestParam String imdbId) {
        MovieResponse movieResponse = omdbService.getMovie(imdbId);
        return ResponseEntity.ok(movieResponse);
    }


}
