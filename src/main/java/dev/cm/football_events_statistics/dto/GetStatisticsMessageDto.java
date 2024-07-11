package dev.cm.football_events_statistics.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

import java.util.*;

@JsonTypeName("GET_STATISTICS")
public class GetStatisticsMessageDto extends MessageDto {

    private final GetStatisticsDto data;

    @JsonCreator
    public GetStatisticsMessageDto(
            @JsonProperty(value = "type", required = true) String type,
            @JsonProperty(value = "get_statistics", required = true) GetStatisticsDto data) {
        super(type);
        this.data = data;
    }

    public GetStatisticsDto getData() {
        return data;
    }

    public static class GetStatisticsDto {

        private final List<String> teams;

        @JsonCreator
        public GetStatisticsDto(@JsonProperty(value = "teams",required = true) Collection<String> teams) {
            this.teams = teams != null ? new ArrayList<>(teams) : new ArrayList<>();
        }

        public List<String> getTeams() {
            return Collections.unmodifiableList(teams);
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("GetStatisticsDto{");
            sb.append("teams=").append(teams);
            sb.append('}');
            return sb.toString();
        }
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("GetStatisticsMessageDto{");
        sb.append("type=").append(getType()).append(", ");
        sb.append("data=").append(data);
        sb.append("}");
        return sb.toString();
    }
}
