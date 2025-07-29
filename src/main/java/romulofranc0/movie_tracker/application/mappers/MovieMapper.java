package romulofranc0.movie_tracker.application.mappers;

import org.springframework.stereotype.Component;
import romulofranc0.movie_tracker.application.models.responses.MovieResponse;
import romulofranc0.movie_tracker.domain.entities.Movie;
import romulofranc0.movie_tracker.domain.entities.MovieList;

import java.util.HashSet;
import java.util.Set;

@Component
public class MovieMapper {

    public Set<MovieResponse> movieToResponses(Set<Movie> movies){
        Set<MovieResponse> movieResponses= new HashSet<MovieResponse>();
        for (Movie movie : movies) {
            MovieResponse movieResponse = new MovieResponse(
                    movie.getTitle(),
                    movie.getDirector(),
                    movie.getPlot(),
                    movie.getYear(),
                    movie.getGenre(),
                    movie.getImdbID(),
                    movie.getPoster(),
                    movie.getImdbRating()

            );

            movieResponses.add(movieResponse);
        }
        return movieResponses;
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
