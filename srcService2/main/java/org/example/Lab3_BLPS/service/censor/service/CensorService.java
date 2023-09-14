package org.example.Lab3_BLPS.service.censor.service;

import lombok.RequiredArgsConstructor;
import org.example.Lab3_BLPS.service.censor.model.MessageEntity;
import org.example.Lab3_BLPS.service.censor.model.UserCensorEntity;
import org.example.Lab3_BLPS.service.censor.service.repository.MessageRepository;
import org.example.Lab3_BLPS.service.censor.service.repository.UserCensorRepository;
import org.example.Lab3_BLPS.service.censor.ws.dto.UserCensorDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CensorService {

    private final MessageRepository messageRepository;
    private final UserCensorRepository userCensorRepository;

    public Page<UserCensorEntity> getUserCensors(Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return userCensorRepository.findAll(pageable);
    }

    public Page<MessageEntity> getMessages(String sender, Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return messageRepository.findAllBySender(sender, pageable);
    }
}
