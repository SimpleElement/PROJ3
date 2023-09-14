package org.example.Lab3_BLPS.service.censor.service.repository;

import org.example.Lab3_BLPS.service.censor.model.MessageEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface MessageRepository extends CrudRepository<MessageEntity, Long> {
    Page<MessageEntity> findAllBySender(String sender, Pageable pageable);
}
