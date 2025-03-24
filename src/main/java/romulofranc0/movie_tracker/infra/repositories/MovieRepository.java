package romulofranc0.movie_tracker.infra.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import romulofranc0.movie_tracker.domain.entities.Movie;

public interface MovieRepository extends JpaRepository<Movie, String> {
}
