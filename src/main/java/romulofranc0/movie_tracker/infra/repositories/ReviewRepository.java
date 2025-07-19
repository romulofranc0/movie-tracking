package romulofranc0.movie_tracker.infra.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import romulofranc0.movie_tracker.domain.entities.Review;
import romulofranc0.movie_tracker.domain.entities.User;

import java.util.Set;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    boolean existsReviewByMovieImdbIDAndUserId(String imdbId,Long userId);

    Set<Review> findByUserIdIn(Set<Long> usersId);
}
