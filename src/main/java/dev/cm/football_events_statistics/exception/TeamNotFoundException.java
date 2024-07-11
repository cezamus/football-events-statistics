package dev.cm.football_events_statistics.exception;

import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.NoSuchElementException;

public class TeamNotFoundException extends NoSuchElementException {
    public TeamNotFoundException(String teamName) {
        super("Team " + teamName + " has played no events yet. Please check if provided team name is correct.");
    }
}
