package romulofranc0.movie_tracker.infra.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
import romulofranc0.movie_tracker.domain.entities.AppUser;

@Repository
public interface UserRepository extends JpaRepository<AppUser, Long> {

    UserDetails findByUsername(String username);
}
