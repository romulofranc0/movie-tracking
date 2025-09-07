package romulofranc0.movie_tracker.infra.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import romulofranc0.movie_tracker.domain.entities.Movie;

import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie, String> {

    @Query("select m from Movie m where m.imdbID =:imdbId")
    Optional<Movie> findByImdbId(String imdbId);
}
