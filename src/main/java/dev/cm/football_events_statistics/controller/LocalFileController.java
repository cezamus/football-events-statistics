package dev.cm.football_events_statistics.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.cm.football_events_statistics.dto.MessageDto;
import dev.cm.football_events_statistics.service.MessageServiceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Controller
public class LocalFileController {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private final MessageServiceFactory messageServiceFactory;

    @Autowired
    public LocalFileController(MessageServiceFactory messageServiceFactory) {
        this.messageServiceFactory = messageServiceFactory;
    }

    public void processAll(String path) throws IOException {
        print(process(load(path)));
    }

    private List<MessageDto> load(String path) throws IOException {
        List<dev.cm.football_events_statistics.dto.MessageDto> messages = new ArrayList<>();
        try (Stream<String> lines = Files.lines(Path.of(path))) {
            lines.forEach(line -> {
                try {
                    messages.add(OBJECT_MAPPER.readValue(line, MessageDto.class));
                } catch (JsonProcessingException exception) {
                    System.err.println(exception.getMessage());
                }
            });
        }
        return messages;
    }

    private List<String> process(List<MessageDto> messages) {
        return messages.stream()
                .flatMap(message -> messageServiceFactory.getServiceFor(message).process(message).stream())
                .toList();
    }

    private void print(List<String> messages) {
        System.out.println();
        messages.forEach(System.out::println);
    }
}
