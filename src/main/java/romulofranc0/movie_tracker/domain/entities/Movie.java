package romulofranc0.movie_tracker.domain.entities;

import jakarta.persistence.*;
import lombok.Data;

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

}