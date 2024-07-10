package dev.cm.football_events_statistics.service;

import dev.cm.football_events_statistics.dto.GetStatisticsMessageDto;
import dev.cm.football_events_statistics.dto.MessageDto;
import dev.cm.football_events_statistics.dto.ResultMessageDto;
import dev.cm.football_events_statistics.service.impl.ResultServiceImpl;
import dev.cm.football_events_statistics.service.impl.StatisticsServiceImpl;
import dev.cm.football_events_statistics.service.impl.UnrecognizedMessageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageServiceFactory {

    private final ResultServiceImpl resultService;
    private final StatisticsServiceImpl statisticsService;
    private final UnrecognizedMessageServiceImpl unrecognizedMessageService;
//    private Map<Class<? extends MessageDto>, MessageService<? extends MessageDto>> HANDLERS
//            = Map.of(
//            GetStatisticsMessageDto.class, new GetStatisticsMessageDtoProcessor(
//                    TeamAggregateStorageFactory.getStorage(TeamAggregateStorageFactory.StorageMode.IN_MEMORY)),
//            ResultMessageDto.class, new ResultServiceImpl()
//    );

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
        return messageDto.getClass() == ResultMessageDto.class ? (MessageService<T>) resultService :
                messageDto.getClass() == GetStatisticsMessageDto.class ? (MessageService<T>) statisticsService :
                        unrecognizedMessageService;
    }
}
