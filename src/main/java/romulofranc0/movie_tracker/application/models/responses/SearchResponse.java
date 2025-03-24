package romulofranc0.movie_tracker.application.models.responses;


import com.fasterxml.jackson.annotation.JsonProperty;

public record SearchResponse(
        @JsonProperty ("Title") String Title,
        @JsonProperty("Year") String Year,
        @JsonProperty("Type") String Type,
        @JsonProperty("imdbID") String imdbID,
        @JsonProperty("Poster") String Poster
) {}

