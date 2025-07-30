package romulofranc0.movie_tracker.infra.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import romulofranc0.movie_tracker.domain.entities.User;
import romulofranc0.movie_tracker.infra.repositories.UserRepository;
import romulofranc0.movie_tracker.infra.security.services.TokenService;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    private final TokenService tokenService;
    private final UserRepository userRepository;

    public SecurityFilter(TokenService tokenService, UserRepository userRepository) {
        this.tokenService = tokenService;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var token = this.extractTokenFromHeader(request);
        if (token != null) {
            try {
                var username = tokenService.validateToken(token);
                User userDetails = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));

                var authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } catch (Exception e) {
                SecurityContextHolder.clearContext();
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }
        }

        filterChain.doFilter(request, response);
    }


    private String extractTokenFromHeader(HttpServletRequest request) {
        var header = request.getHeader("Authorization");
        if (header == null || !header.startsWith("Bearer ")) return null;
        return header.substring(7).trim();
    }

}
