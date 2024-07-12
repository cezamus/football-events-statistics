package dev.cm.football_events_statistics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Locale;

@SpringBootApplication
public class TheFootballEventsStatisticsApplication {

    static {
        Locale.setDefault(Locale.ENGLISH);
    }

    public static void main(String[] args) {
        SpringApplication.run(TheFootballEventsStatisticsApplication.class, args);
    }
}
