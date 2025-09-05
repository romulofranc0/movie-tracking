package romulofranc0.movie_tracker.domain.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class ReviewComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

   private String commentText;

   @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   @JoinColumn(name = "review_id")
   private Review review;

   @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   @JoinColumn(name = "user_id")
   private User user;
}
