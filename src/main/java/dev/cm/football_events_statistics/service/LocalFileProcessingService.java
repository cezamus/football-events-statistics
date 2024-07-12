package dev.cm.football_events_statistics.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.cm.football_events_statistics.dto.MessageDto;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.xml.validation.Validator;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

@Component
public class LocalFileProcessingService implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(LocalFileProcessingService.class);
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private final MessageServiceFactory messageServiceFactory;

    @Autowired
    public LocalFileProcessingService(MessageServiceFactory messageServiceFactory) {
        this.messageServiceFactory = messageServiceFactory;
    }

    @Override
    public void run(String... args) throws Exception {
        processAllAtDirectory("data/messages-task-description.txt");
    }

    void processAllAtDirectory(String path) {
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
            log.error("Valid file has not been found at specified direction: {}", path);
        }
        responses.forEach(log::info);
    }

    private List<String> process(MessageDto message) {
        try {
            return messageServiceFactory.getServiceFor(message).process(message);
        } catch (Exception exception) {
            return List.of(exception.getMessage());
        }
    }
}
