package romulofranc0.movie_tracker.domain.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import romulofranc0.movie_tracker.domain.entities.AppUser;
import romulofranc0.movie_tracker.domain.exceptions.UserAlreadyFollowedException;
import romulofranc0.movie_tracker.infra.repositories.UserRepository;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public void follow(Long id){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        String username = auth.getName();


        AppUser userFollowing = userRepository.findByUsername(username).orElseThrow(()->new UsernameNotFoundException("Username not found"));
        AppUser userFollowed = userRepository.findById(id).orElseThrow(()->new UsernameNotFoundException("User not found"));

        if(userFollowing.getFollowing().contains(userFollowed)){
            throw new UserAlreadyFollowedException("User already following");
        }

        userFollowing.getFollowing().add(userFollowed);
        userFollowed.getFollowers().add(userFollowing);

        userRepository.save(userFollowed);
        userRepository.save(userFollowing);
    }
}
