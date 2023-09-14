package org.example.Lab3_BLPS.service.censor.ws;

import lombok.RequiredArgsConstructor;
import org.example.Lab3_BLPS.common.object.PageResponse;
import org.example.Lab3_BLPS.service.censor.model.MessageEntity;
import org.example.Lab3_BLPS.service.censor.model.UserCensorEntity;
import org.example.Lab3_BLPS.service.censor.service.CensorService;
import org.example.Lab3_BLPS.service.censor.ws.dto.MessageDto;
import org.example.Lab3_BLPS.service.censor.ws.dto.UserCensorDto;
import org.example.Lab3_BLPS.service.censor.ws.filter.UserCensorFilter;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/censor")
public class CensorController {

    private final CensorService censorService;
    private final ConversionService conversionService;

    @GetMapping("/getUsers")
    public PageResponse<UserCensorDto> getUsers(@Valid UserCensorFilter filter) {
        Page<UserCensorEntity> page = censorService.getUserCensors(filter.getPageNumber(), filter.getPageSize());

        PageResponse<UserCensorDto> response = conversionService.convert(page, PageResponse.class);
        response.setContent(
                page.getContent().stream()
                        .map(userCensorEntity -> conversionService.convert(userCensorEntity, UserCensorDto.class))
                        .collect(Collectors.toList())
        );
        response.setTotalElements(page.getTotalElements());
        return response;
    }

    @GetMapping("/getMessages/{sender}")
    public PageResponse<MessageDto> getMessages(@PathVariable String sender, @Valid UserCensorFilter filter) {
        Page<MessageEntity> page = censorService.getMessages(sender, filter.getPageNumber(), filter.getPageSize());

        PageResponse<MessageDto> response = conversionService.convert(page, PageResponse.class);
        response.setContent(
                page.getContent().stream()
                        .map(messageEntity -> conversionService.convert(messageEntity, MessageDto.class))
                        .collect(Collectors.toList())
        );
        response.setTotalElements(page.getTotalElements());
        return response;
    }
}
