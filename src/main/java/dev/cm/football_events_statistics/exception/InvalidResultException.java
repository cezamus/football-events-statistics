package dev.cm.football_events_statistics.exception;

public class InvalidResultException extends IllegalArgumentException{

    public InvalidResultException(){
        super("Received result data has been invalid.");
    }
}
