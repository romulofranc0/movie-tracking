package romulofranc0.movie_tracker.domain.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Movie{

    @Id
    private String imdbID;
    private String title;
    private String year;
    private String director;
    private String genre;
    private String poster;
    private String plot;

    @ManyToMany(mappedBy = "movies")
    private Set<MovieList> movieLists = new HashSet<MovieList>();

}