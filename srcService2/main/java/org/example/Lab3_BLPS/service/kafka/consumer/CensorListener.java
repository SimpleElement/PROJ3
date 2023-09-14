package org.example.Lab3_BLPS.service.kafka.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.example.Lab3_BLPS.service.kafka.model.MessageModel;
import org.example.Lab3_BLPS.service.kafka.service.CensorKafkaService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CensorListener {

    private static final String orderTopic = "${kafka.topic.name}";

    private final ObjectMapper objectMapper;
    private final CensorKafkaService censorKafkaService;

    @SneakyThrows
    @KafkaListener(topics = orderTopic)
    public void consumeMessage(String message) {
        censorKafkaService.handleMessage(objectMapper.readValue(message, MessageModel.class));
    }
}
