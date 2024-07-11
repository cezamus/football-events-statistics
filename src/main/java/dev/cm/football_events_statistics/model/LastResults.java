package dev.cm.football_events_statistics.model;

import jakarta.persistence.*;

import java.io.Serializable;

@Embeddable
public class LastResults implements Serializable {

    private String lastResult;
    private String secondLastResult;
    private String thirdLResult;

    public LastResults() {
        lastResult = "";
        secondLastResult = "";
        thirdLResult = "";
    }

    @Override
    public String toString() {
        return lastResult + secondLastResult + thirdLResult;
    }

    public void update(int goalDifference) {
        thirdLResult = secondLastResult;
        secondLastResult = lastResult;
        lastResult = goalDifference > 0 ? "W" : goalDifference < 0 ? "L" : "D";
    }
}
