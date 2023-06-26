package com.example.warmuprabbitmq.component;

import com.example.warmuprabbitmq.config.RabbitProperties;
import com.example.warmuprabbitmq.dto.MessageDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class WarmupProducer {

    private final RabbitTemplate rabbitTemplate;
    private final RabbitProperties rabbitProperties;

    /**
     * Queue로 메시지를 발행
     *
     * @param messageDto 발행할 메시지의 DTO 객체
     */
    public void sendMessage(MessageDto messageDto) {
        log.info("message sent: {}", messageDto.toString());
        rabbitTemplate.convertAndSend(
                rabbitProperties.getCustom().getExchange().getDirect(),
                rabbitProperties.getCustom().getRouting().getDirect(),
                messageDto);
    }

}
