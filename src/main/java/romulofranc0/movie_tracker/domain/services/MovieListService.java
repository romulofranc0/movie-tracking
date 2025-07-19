package romulofranc0.movie_tracker.domain.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import romulofranc0.movie_tracker.application.mappers.MovieListMapper;
import romulofranc0.movie_tracker.application.models.requests.MovieListRequest;
import romulofranc0.movie_tracker.application.models.responses.MovieListResponse;
import romulofranc0.movie_tracker.domain.entities.Movie;
import romulofranc0.movie_tracker.domain.entities.MovieList;
import romulofranc0.movie_tracker.infra.repositories.MovieListRepository;
import romulofranc0.movie_tracker.infra.repositories.MovieRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class MovieListService {

    private final MovieListRepository movieListRepository;
    private final MovieListMapper movieListMapper;
    private final MovieRepository movieRepository;

    public Set<MovieListResponse> getAllMovieListsFromUser(Long userId) {

        List<MovieList> movieLists = movieListRepository.findAllByUserId(userId);

        return movieListMapper.toMovieListResponses(movieLists);
    }

    public MovieList createMovieList(MovieListRequest request) {
        Set<Movie> movies = new HashSet<>(movieRepository.findAllById(request.imdbIds()));

        var movieList = new MovieList();
        movieList.setListName(request.name());
        movieList.setMovies(movies);

        return movieListRepository.save(movieList);
    }
}
