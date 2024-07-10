package dev.cm.football_events_statistics.model;

import jakarta.persistence.*;

@Entity
@Table
public class LastResults {

    private static final String LAST_RESULT_SEQUENCE = "last_results_sequence";
    @Id
    @SequenceGenerator(
            name = LAST_RESULT_SEQUENCE,
            sequenceName = LAST_RESULT_SEQUENCE,
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = LAST_RESULT_SEQUENCE
    )
    private Long id;
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
