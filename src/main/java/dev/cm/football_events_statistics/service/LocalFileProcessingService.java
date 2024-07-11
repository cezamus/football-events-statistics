package dev.cm.football_events_statistics.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.cm.football_events_statistics.dto.MessageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Component
public class LocalFileProcessingService {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private final MessageServiceFactory messageServiceFactory;

    @Autowired
    public LocalFileProcessingService(MessageServiceFactory messageServiceFactory) {
        this.messageServiceFactory = messageServiceFactory;
    }

    public void processAllAtDirectory(String path) {    //todo: consider unit test
        List<String> responses = new ArrayList<>();
        try (Stream<String> lines = Files.lines(Path.of(path))) {
            lines.forEach(line -> {
                try {
                    MessageDto messageDto = OBJECT_MAPPER.readValue(line, MessageDto.class);
                    responses.addAll(process(messageDto));
                } catch (JsonProcessingException exception) {
                    responses.add(exception.getMessage());
                }
            });
        } catch (IOException exception) {
            System.err.println("Valid file has not been found at specified direction: " + path);
        }
        System.out.println();
        responses.forEach(System.out::println);
    }

    private List<String> process(MessageDto message) {
        try {
            return messageServiceFactory.getServiceFor(message).process(message);
        } catch (RuntimeException exception) {
            return List.of(exception.getMessage());
        }
    }
}
