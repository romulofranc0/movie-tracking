package romulofranc0.movie_tracker.infra.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import romulofranc0.movie_tracker.domain.entities.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    boolean existsReviewByMovieImdbIDAndUserId(String imdbId,Long userId);
}
