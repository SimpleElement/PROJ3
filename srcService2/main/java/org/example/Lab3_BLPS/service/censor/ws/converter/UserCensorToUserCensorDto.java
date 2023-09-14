package org.example.Lab3_BLPS.service.censor.ws.converter;

import org.example.Lab3_BLPS.service.censor.model.UserCensorEntity;
import org.example.Lab3_BLPS.service.censor.ws.dto.UserCensorDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserCensorToUserCensorDto implements Converter<UserCensorEntity, UserCensorDto> {
    @Override
    public UserCensorDto convert(UserCensorEntity source) {
        UserCensorDto res = new UserCensorDto();

        res.setId(source.getId());
        res.setUsername(source.getUsername());
        res.setCountCensorViolations(source.getCountCensorViolations());
        return res;
    }
}
