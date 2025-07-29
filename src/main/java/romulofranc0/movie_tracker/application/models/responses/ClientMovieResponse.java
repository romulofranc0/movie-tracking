package romulofranc0.movie_tracker.application.models.responses;


import com.fasterxml.jackson.annotation.JsonProperty;

public record ClientMovieResponse(
        @JsonProperty("Title") String Title,
        @JsonProperty("Director") String Director,
        @JsonProperty("Plot") String Plot,
        @JsonProperty("Year") String Year,
        @JsonProperty("Genre") String Genre,
        @JsonProperty("imdbID") String imdbID,
        @JsonProperty("Poster") String Poster,
        @JsonProperty("imdbRating") String imdbRating
) {}

