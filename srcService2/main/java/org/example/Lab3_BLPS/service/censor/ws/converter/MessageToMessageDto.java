package org.example.Lab3_BLPS.service.censor.ws.converter;

import org.example.Lab3_BLPS.service.censor.model.MessageEntity;
import org.example.Lab3_BLPS.service.censor.ws.dto.MessageDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class MessageToMessageDto implements Converter<MessageEntity, MessageDto> {
    @Override
    public MessageDto convert(MessageEntity source) {
        MessageDto res = new MessageDto();

        res.setId(source.getId());
        res.setSender(source.getSender());
        res.setTopic(source.getTopic());
        res.setContent(source.getContent());
        res.setRecipient(source.getRecipient());
        res.setDateOfDispatch(source.getDateOfDispatch());
        return res;
    }
}
