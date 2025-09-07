package romulofranc0.movie_tracker.domain.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Data
@Entity
public class MovieList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String listName;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany
    @JoinTable(
            name = "movielist_movie",
            joinColumns = @JoinColumn(name = "movielist_id"),
            inverseJoinColumns = @JoinColumn(name = "movie_id")
    )
    private Set<Movie> movies;
}
