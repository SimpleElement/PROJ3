package org.example.Lab3_BLPS.service.kafka.service;

import lombok.RequiredArgsConstructor;
import org.example.Lab3_BLPS.service.censor.model.MessageEntity;
import org.example.Lab3_BLPS.service.censor.model.UserCensorEntity;
import org.example.Lab3_BLPS.service.censor.service.repository.MessageRepository;
import org.example.Lab3_BLPS.service.censor.service.repository.UserCensorRepository;
import org.example.Lab3_BLPS.service.kafka.model.MessageModel;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class CensorKafkaService {

    private static final Pattern pattern = Pattern.compile("(террор|т[её]рр?о[рг][еи]з?м|т[её]рр?а[кс][т]|взрыв|р[её]волюция)");

    private final MessageRepository messageRepository;
    private final UserCensorRepository userCensorRepository;

    public void handleMessage(MessageModel message) {
        if (pattern.matcher(message.getTopic().toLowerCase()).find() || pattern.matcher(message.getContent().toLowerCase()).find()) {
            String usernameSender = message.getSender();

            if (!userCensorRepository.existsByUsername(usernameSender)) {
                UserCensorEntity userCensor = new UserCensorEntity();

                userCensor.setUsername(message.getSender());
                userCensor.setCountCensorViolations(0);
                userCensorRepository.save(userCensor);
            }
            userCensorRepository.incCountCensorViolationsByUsername(usernameSender);

            messageRepository.save(toMessageEntity(message, userCensorRepository.findByUsername(usernameSender)));
        }
    }

    private MessageEntity toMessageEntity(MessageModel source, UserCensorEntity userCensor) {
        MessageEntity res = new MessageEntity();

        res.setId(source.getId());
        res.setSender(source.getSender());
        res.setTopic(source.getTopic());
        res.setContent(source.getContent());
        res.setRecipient(source.getRecipient());
        res.setDateOfDispatch(source.getDateOfDispatch());
        res.setUserCensor(userCensor);
        return res;
    }
}
