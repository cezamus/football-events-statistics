package dev.cm.football_events_statistics.model;

import jakarta.persistence.*;

@Entity
@Table
public class TeamStatistics {

    private static final String TEAM_SEQUENCE = "team_sequence";
    @Id
    @SequenceGenerator(
            name = TEAM_SEQUENCE,
            sequenceName = TEAM_SEQUENCE,
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = TEAM_SEQUENCE
    )
    private Long id;
    private String name;
    @OneToOne
    private LastResults lastResults;
    private int playedEventsNumber;
    private int points;
    private int goalsScored;
    private int goalsConceded;

    public TeamStatistics() {
    }

    public TeamStatistics(String name) {
        this.name = name;
        this.lastResults = new LastResults();
        playedEventsNumber = 0;
        points = 0;
        goalsScored = 0;
        goalsConceded = 0;
    }

    public TeamStatistics(
            String name,
            LastResults lastResults,
            int playedEventsNumber,
            int points,
            int goalsScored,
            int goalsConceded) {
        this.name = name;
        this.lastResults = lastResults;
        this.playedEventsNumber = playedEventsNumber;
        this.points = points;
        this.goalsScored = goalsScored;
        this.goalsConceded = goalsConceded;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LastResults getLastResults() {
        return lastResults;
    }

    public void setLastResults(LastResults lastResults) {
        this.lastResults = lastResults;
    }

    public int getPlayedEventsNumber() {
        return playedEventsNumber;
    }

    public void setPlayedEventsNumber(int playedEventsNumber) {
        this.playedEventsNumber = playedEventsNumber;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getGoalsScored() {
        return goalsScored;
    }

    public void setGoalsScored(int goalsScored) {
        this.goalsScored = goalsScored;
    }

    public int getGoalsConceded() {
        return goalsConceded;
    }

    public void setGoalsConceded(int goalsConceded) {
        this.goalsConceded = goalsConceded;
    }

    public void updatePoints(int goalDifference) {
        if (goalDifference > 0) {
            points += 3;
        } else if (goalDifference == 0) {
            points += 1;
        }
    }

    public String getSimplifiedStatistics() {
        final StringBuilder sb = new StringBuilder(name);
        sb.append(" ").append(playedEventsNumber);
        sb.append(" ").append(points);
        sb.append(" ").append(goalsScored);
        sb.append(" ").append(goalsConceded);
        return sb.toString();
    }

    public String getStatistics() {
        final StringBuilder sb = new StringBuilder(name);
        sb.append(" ").append(lastResults.toString());
        sb.append(" ").append(playedEventsNumber == 0 ? 0 : (float) (goalsScored + goalsConceded) / playedEventsNumber);
        sb.append(" ").append(playedEventsNumber);
        sb.append(" ").append(points);
        sb.append(" ").append(goalsScored);
        sb.append(" ").append(goalsConceded);
        return sb.toString();
    }
}
