package com.example.warmuprabbitmq.producer;

import com.example.warmuprabbitmq.config.RabbitProperties;
import com.example.warmuprabbitmq.dto.MessageDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class DirectProducer implements RabbitExchange {

    private final RabbitTemplate rabbitTemplate;
    private final RabbitProperties rabbitProperties;

    @Override
    public void sendMessage(MessageDto messageDto) {
        log.info("Direct Message Sent: [{}]", messageDto.toString());
        rabbitTemplate.convertAndSend(rabbitProperties.getDirectExchange(), messageDto.getRoutingKey(), messageDto);
    }

}
