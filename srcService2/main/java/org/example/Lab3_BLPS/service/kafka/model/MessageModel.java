package org.example.Lab3_BLPS.service.kafka.model;

import lombok.Data;

@Data
public class MessageModel {
    private Long id;

    private String recipient;

    private String sender;

    private String topic;

    private String content;

    private String dateOfDispatch;
}
