package dev.cm.football_events_statistics;

import dev.cm.football_events_statistics.service.LocalFileProcessingService;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TheFootballEventsStatisticsApplication {

    public static void main(String[] args) {
        ApplicationContext context=SpringApplication.run(TheFootballEventsStatisticsApplication.class, args);
        context
                .getBean(LocalFileProcessingService.class)
                .processAllAtDirectory("data/messages-task-description.txt");
    }
}
