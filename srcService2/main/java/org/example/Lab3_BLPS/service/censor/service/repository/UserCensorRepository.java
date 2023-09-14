package org.example.Lab3_BLPS.service.censor.service.repository;

import org.example.Lab3_BLPS.service.censor.model.UserCensorEntity;
import org.example.Lab3_BLPS.service.censor.ws.dto.UserCensorDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface UserCensorRepository extends CrudRepository<UserCensorEntity, Long> {
    boolean existsByUsername(String username);

    UserCensorEntity findByUsername(String username);

    Page<UserCensorEntity> findAll(Pageable pageable);

    @Modifying
    @Transactional
    @Query("UPDATE UserCensorEntity uc SET uc.countCensorViolations = uc.countCensorViolations + 1 WHERE uc.username = :username")
    void incCountCensorViolationsByUsername(@Param("username") String username);
}
