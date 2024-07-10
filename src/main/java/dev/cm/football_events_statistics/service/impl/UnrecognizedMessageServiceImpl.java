package dev.cm.football_events_statistics.service.impl;

import dev.cm.football_events_statistics.dto.MessageDto;
import dev.cm.football_events_statistics.service.MessageService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UnrecognizedMessageServiceImpl implements MessageService {
    @Override
    public List<String> process(MessageDto message) { //todo: update cause program never reaches this method
        return List.of("Unrecognized message with type " + message.getType() + ". Skipping...");
    }
}
