package romulofranc0.movie_tracker.infra.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import romulofranc0.movie_tracker.domain.entities.Review;
import java.util.Optional;
import java.util.Set;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    boolean existsReviewByMovieImdbIDAndUserId(String imdbId,Long userId);

    Set<Review> findAllByUserId(Long userId);

    @Query("select r from Review r where r.id =:id")
    Optional<Review> findById(Long id);

    Boolean existsByMovieImdbIDAndUserId(String imdbId, Long userId);

    Optional<Review> findByMovieImdbIDAndUserId(String imdbId, Long userId);
}
