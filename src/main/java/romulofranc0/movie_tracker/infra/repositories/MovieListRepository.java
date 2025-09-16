package romulofranc0.movie_tracker.infra.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import romulofranc0.movie_tracker.domain.entities.MovieList;
import java.util.List;
import java.util.Optional;

@Repository
public interface MovieListRepository extends JpaRepository<MovieList, Long> {
    List<MovieList> findAllByUserId(Long userId);

    Optional<MovieList> findByIdAndUserId(Long id, Long userId);
}
