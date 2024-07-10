package dev.cm.football_events_statistics.controller;

import dev.cm.football_events_statistics.dto.MessageDto;
import dev.cm.football_events_statistics.service.MessageServiceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "message")
public class RestMessageController {

    MessageServiceFactory messageServiceFactory;

    @Autowired
    public RestMessageController(MessageServiceFactory messageServiceFactory) {
        this.messageServiceFactory = messageServiceFactory;
    }

    @PostMapping
    public List<String> process(@RequestBody MessageDto messageDto) {
        return messageServiceFactory.getServiceFor(messageDto).process(messageDto);
    }
}
