package romulofranc0.movie_tracker.application.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import romulofranc0.movie_tracker.application.models.requests.AddMovieRequest;
import romulofranc0.movie_tracker.application.models.requests.MovieListRequest;
import romulofranc0.movie_tracker.application.models.responses.MovieListResponse;
import romulofranc0.movie_tracker.domain.services.MovieListService;

import java.util.Set;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/movie-lists")
public class MovieListController {

    private final MovieListService movieListService;

    @PostMapping()
    public ResponseEntity<?> createList(@RequestBody MovieListRequest request){
        var response = movieListService.createMovieList(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/add-movie")
    public ResponseEntity<?> updateList(@RequestBody AddMovieRequest request){
        var response = movieListService.addMovieToList(request.imdbId(),request.listId());
        return ResponseEntity.ok().body(response);

    }

    @GetMapping("/{listId}")
    public ResponseEntity<MovieListResponse> getList(@PathVariable Long listId){
        return ResponseEntity.ok().body(movieListService.getMovieList(listId));
    }

    @GetMapping
    public ResponseEntity<Set<MovieListResponse>> getAllLists(){
       return ResponseEntity.ok().body(movieListService.getAllMovieListsFromUser());
    }

    @DeleteMapping("/{listId}")
    public ResponseEntity<?> deleteList(@PathVariable Long listId) {
        if (movieListService.existsById(listId)) {
            movieListService.deleteMovieListForUser(listId);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
