package romulofranc0.movie_tracker.application.mappers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import romulofranc0.movie_tracker.application.models.responses.MovieListResponse;
import romulofranc0.movie_tracker.domain.entities.Movie;
import romulofranc0.movie_tracker.domain.entities.MovieList;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Component
public class MovieListMapper {

    private final MovieMapper movieMapper;

    public Set<MovieListResponse> toMovieListResponses(List<MovieList> movieLists) {
        var movieListResponses= new HashSet<MovieListResponse>();
        for (MovieList movieList : movieLists) {
            var movieListResponse = toMovieListResponse(movieList);
            movieListResponses.add(movieListResponse);
        }
        return movieListResponses;
    }

    public MovieListResponse toMovieListResponse(MovieList movieList) {
        var movieListResponse = new MovieListResponse(
                movieList.getId(),
                movieList.getListName(),
                movieMapper.movieToResponses(movieList.getMovies())
        );

        return movieListResponse;
    }
}
