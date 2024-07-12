package dev.cm.football_events_statistics.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.validation.Valid;

import javax.xml.transform.Source;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type", visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(name = "RESULT", value = ResultMessageDto.class),
        @JsonSubTypes.Type(name = "GET_STATISTICS", value = GetStatisticsMessageDto.class)
})
public abstract class MessageDto {

    private final String type;

    public MessageDto(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
