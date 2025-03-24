package romulofranc0.movie_tracker.application.models.responses;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record SearchResponseWrapper(
        @JsonProperty("Search") List<SearchResponse> Search,
        @JsonProperty("totalResults") String totalResults,
        @JsonProperty("Response") String Response,
        @JsonProperty("Error") String Error
) {}