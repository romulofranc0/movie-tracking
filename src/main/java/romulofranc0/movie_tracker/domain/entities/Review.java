package romulofranc0.movie_tracker.domain.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "movie_id", nullable = false)
    private Movie movie;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @Column(nullable = false)
    private Float rating;
    @Column(nullable = false)
    private String comment;
    @Column(nullable = false)
    private LocalDate watchDate;
    private LocalDate reviewDate;

    @Builder
    public Review(Movie movie, User user, Float rating, String comment, LocalDate watchDate, LocalDate reviewDate) {
        this.movie = movie;
        this.user = user;
        this.rating = rating;
        this.comment = comment;
        this.watchDate = watchDate;
        this.reviewDate = reviewDate;
    }

}