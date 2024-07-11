package dev.cm.football_events_statistics.dto;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class MessageDtoDeserializer extends JsonDeserializer<MessageDto> {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Override
    public MessageDto deserialize(JsonParser parser, DeserializationContext context) throws IOException {
        JsonNode node = parser.getCodec().readTree(parser);
        String type = node.get("type").asText();

        return switch (type) {
            case "RESULT" -> OBJECT_MAPPER.treeToValue(node, ResultMessageDto.class);
            case "GET_STATISTICS" -> OBJECT_MAPPER.treeToValue(node, GetStatisticsMessageDto.class);
            default -> OBJECT_MAPPER.treeToValue(node, GetStatisticsMessageDto.class);
        };
    }
}
