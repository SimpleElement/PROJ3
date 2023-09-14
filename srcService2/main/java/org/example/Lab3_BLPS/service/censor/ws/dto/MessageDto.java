package org.example.Lab3_BLPS.service.censor.ws.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageDto {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("recipient")
    private String recipient;

    @JsonProperty("sender")
    private String sender;

    @JsonProperty("topic")
    private String topic;

    @JsonProperty("content")
    private String content;

    @JsonProperty("dateOfDispatch")
    private String dateOfDispatch;
}
