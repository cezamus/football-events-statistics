package dev.cm.football_events_statistics.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("RESULT")
public class ResultMessageDto extends MessageDto {

    private final ResultDto data;

    @JsonCreator
    public ResultMessageDto(
            @JsonProperty("type") String type,
            @JsonProperty("result") ResultDto data) {
        super(type);
        this.data = data;
    }

    public ResultDto getData() {
        return data;
    }

    public static class ResultDto {

        private final String homeTeam;
        private final String awayTeam;
        private final int homeScore;
        private final int awayScore;

        @JsonCreator
        public ResultDto(@JsonProperty("home_team") String homeTeam,
                         @JsonProperty("away_team") String awayTeam,
                         @JsonProperty("home_score") int homeScore,
                         @JsonProperty("away_score") int awayScore) {
            this.homeTeam = homeTeam;
            this.awayTeam = awayTeam;
            this.homeScore = homeScore;
            this.awayScore = awayScore;
        }

        public String getHomeTeam() {
            return homeTeam;
        }

        public String getAwayTeam() {
            return awayTeam;
        }

        public int getHomeScore() {
            return homeScore;
        }

        public int getAwayScore() {
            return awayScore;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("ResultDto{");
            sb.append("homeTeam='").append(homeTeam).append('\'');
            sb.append(", awayTeam='").append(awayTeam).append('\'');
            sb.append(", homeScore=").append(homeScore);
            sb.append(", awayScore=").append(awayScore);
            sb.append('}');
            return sb.toString();
        }
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ResultMessageDto{");
        sb.append("type=").append(getType()).append(", ");
        sb.append("data=").append(data);
        sb.append("}");
        return sb.toString();
    }
}
