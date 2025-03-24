package romulofranc0.movie_tracker.domain.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import romulofranc0.movie_tracker.application.clients.OmdbClient;
import romulofranc0.movie_tracker.application.models.responses.MovieResponse;
import romulofranc0.movie_tracker.application.models.responses.SearchResponse;
import romulofranc0.movie_tracker.application.models.responses.SearchResponseWrapper;

import java.util.List;
import java.util.Optional;
@Service
public class OmdbService {
    @Value("${omdb.api.key}")
    private String apiKey;

    private OmdbClient omdbClient;

    public OmdbService(OmdbClient omdbClient) {
        this.omdbClient = omdbClient;
    }

    public SearchResponseWrapper search(String title) {
        return omdbClient.searchMovie(title,apiKey);
    }

    public MovieResponse getMovie(String imdbId) {
        return omdbClient.getMovie(imdbId, apiKey);
    }
}
