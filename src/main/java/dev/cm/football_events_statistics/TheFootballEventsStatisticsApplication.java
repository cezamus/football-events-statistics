package dev.cm.football_events_statistics;

import dev.cm.football_events_statistics.controller.LocalFileController;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class TheFootballEventsStatisticsApplication {

    public static void main(String[] args) throws IOException {
        ApplicationContext context=SpringApplication.run(TheFootballEventsStatisticsApplication.class, args);
        context.getBean(LocalFileController.class).processAll("data/messages-task-description.txt");
    }
}
