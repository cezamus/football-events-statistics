package dev.cm.football_events_statistics.service;

import dev.cm.football_events_statistics.dto.MessageDto;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
public interface MessageService<T extends MessageDto> {

    List<String> process(@Valid T message);
}
