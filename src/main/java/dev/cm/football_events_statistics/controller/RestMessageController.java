package dev.cm.football_events_statistics.controller;

import dev.cm.football_events_statistics.dto.MessageDto;
import dev.cm.football_events_statistics.exception.InvalidResultException;
import dev.cm.football_events_statistics.exception.TeamNotFoundException;
import dev.cm.football_events_statistics.service.MessageServiceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "message")
public class RestMessageController {

    MessageServiceFactory messageServiceFactory;

    @Autowired
    public RestMessageController(MessageServiceFactory messageServiceFactory) {
        this.messageServiceFactory = messageServiceFactory;
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected String handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        return e.getMessage();
    }
    @ExceptionHandler(InvalidResultException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected String handleInvalidResultException(InvalidResultException e) {
        return e.getMessage();
    }
    @ExceptionHandler(TeamNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected String handleTeamNotFoundException(TeamNotFoundException e) {
        return e.getMessage();
    }

    @PostMapping
    public List<String> process(@RequestBody MessageDto messageDto) {
        return messageServiceFactory.getServiceFor(messageDto).process(messageDto);
    }
}
