package dev.cm.football_events_statistics.service;

import dev.cm.football_events_statistics.dto.MessageDto;

import java.util.List;

public interface MessageService<T extends MessageDto> {

    List<String> process(T message);
}
