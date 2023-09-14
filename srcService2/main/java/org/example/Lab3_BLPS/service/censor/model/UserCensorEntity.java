package org.example.Lab3_BLPS.service.censor.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Setter
@Getter
@Table(name = "user_censor_ref")
public class UserCensorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_censor_seq")
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "count_censor_violations")
    private Integer countCensorViolations;

    @OneToMany(mappedBy = "userCensor", fetch=FetchType.LAZY)
    private List<MessageEntity> likeEntities;
}
