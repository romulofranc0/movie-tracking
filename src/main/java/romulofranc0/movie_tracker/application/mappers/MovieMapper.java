package romulofranc0.movie_tracker.application.mappers;

import org.springframework.stereotype.Component;
import romulofranc0.movie_tracker.application.models.responses.MovieResponse;
import romulofranc0.movie_tracker.domain.entities.Movie;
import romulofranc0.movie_tracker.domain.entities.MovieList;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class MovieMapper {

    public Set<MovieResponse> movieToResponses(Set<Movie> movies) {
        if (movies == null) return Set.of();
        return movies.stream()
                .map(this::toMovieResponse)
                .collect(Collectors.toSet());
    }

    public MovieResponse toMovieResponse(Movie movie) {
        return new MovieResponse(
                movie.getTitle(),
                movie.getDirector(),
                movie.getPlot(),
                movie.getYear(),
                movie.getGenre(),
                movie.getImdbID(),
                movie.getPoster(),
                movie.getImdbRating()
        );
    }
}
