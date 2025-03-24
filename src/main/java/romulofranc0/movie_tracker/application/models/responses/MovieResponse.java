package romulofranc0.movie_tracker.application.models.responses;


import com.fasterxml.jackson.annotation.JsonProperty;

public record MovieResponse(
        @JsonProperty("Title") String Title,
        @JsonProperty("Director") String Director,
        @JsonProperty("Plot") String Plot,
        @JsonProperty("Year") String Year,
        @JsonProperty("Genre") String Genre,
        @JsonProperty("Type") String Type,
        @JsonProperty("imdbID") String imdbID,
        @JsonProperty("Poster") String Poster,
        @JsonProperty("Response") String Response,
        @JsonProperty("Error") String Error
) {}

