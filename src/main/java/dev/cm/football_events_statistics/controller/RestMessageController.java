package dev.cm.football_events_statistics.controller;

import dev.cm.football_events_statistics.dto.MessageDto;
import dev.cm.football_events_statistics.exception.TeamNotFoundException;
import dev.cm.football_events_statistics.service.MessageServiceFactory;
import jakarta.validation.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "message")
public class RestMessageController {

    private static final Logger log = LoggerFactory.getLogger(RestMessageController.class);
    private final MessageServiceFactory messageServiceFactory;

    @Autowired
    public RestMessageController(MessageServiceFactory messageServiceFactory) {
        this.messageServiceFactory = messageServiceFactory;
    }

    @ExceptionHandler({HttpMessageNotReadableException.class, ConstraintViolationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    private String handleExceptions(Throwable e) {
        return e.getMessage();
    }

    @ExceptionHandler(TeamNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    private String handleMessageNotReadableException(TeamNotFoundException e) {
        return e.getMessage();
    }

    @PostMapping
    public List<String> process(@RequestBody MessageDto messageDto) {
        List<String> statistics = messageServiceFactory.getServiceFor(messageDto).process(messageDto);
        statistics.forEach(log::info);
        return statistics;
    }
}
