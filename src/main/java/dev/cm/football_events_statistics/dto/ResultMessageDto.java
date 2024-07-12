package dev.cm.football_events_statistics.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;


@JsonTypeName("RESULT")
public class ResultMessageDto extends MessageDto {
    @Valid
    private final ResultDto data;

    @JsonCreator
    public ResultMessageDto(
            @JsonProperty(value = "type", required = true) String type,
            @JsonProperty(value = "result", required = true)  ResultDto data) {
        super(type);
        this.data = data;
    }

    public ResultDto getData() {
        return data;
    }

    public static class ResultDto {

        @NotBlank
        private final String homeTeam;
        @NotBlank
        private final String awayTeam;
        @PositiveOrZero
        private final int homeScore;
        @PositiveOrZero
        private final int awayScore;

        @JsonCreator
        public ResultDto(@JsonProperty(value = "home_team", required = true) String homeTeam,
                         @JsonProperty(value = "away_team", required = true) String awayTeam,
                         @JsonProperty(value = "home_score", required = true)  int homeScore,
                         @JsonProperty(value = "away_score", required = true)  int awayScore) {
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
