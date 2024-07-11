package dev.cm.football_events_statistics.service;

import dev.cm.football_events_statistics.dto.GetStatisticsMessageDto;
import dev.cm.football_events_statistics.dto.MessageDto;
import dev.cm.football_events_statistics.dto.ResultMessageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageServiceFactory {

    private final ResultServiceImpl resultService;
    private final StatisticsServiceImpl statisticsService;
    private final UnrecognizedMessageServiceImpl unrecognizedMessageService;

    @Autowired
    public MessageServiceFactory(
            ResultServiceImpl resultService,
            StatisticsServiceImpl statisticsService,
            UnrecognizedMessageServiceImpl unrecognizedMessageService) {
        this.resultService = resultService;
        this.statisticsService = statisticsService;
        this.unrecognizedMessageService = unrecognizedMessageService;
    }


    public <T extends MessageDto> MessageService<T> getServiceFor(T messageDto) {
        if(messageDto.getClass() == ResultMessageDto.class){
            return (MessageService<T>) resultService;
        } else if (messageDto.getClass() == GetStatisticsMessageDto.class) {
            return (MessageService<T>) statisticsService;
        }else {
            return unrecognizedMessageService;
        }
    }
}
