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
public class TopicProducer implements RabbitExchange {

    private final RabbitTemplate rabbitTemplate;
    private final RabbitProperties rabbitProperties;

    /**
     * exchange type : TOPIC
     *
     */
    @Override
    public void sendMessage(MessageDto messageDto) {
        log.info("Topic Message Sent: [{}]", messageDto.toString());
        rabbitTemplate.convertAndSend(rabbitProperties.getTopicExchange(), messageDto.getRoutingKey(), messageDto);
    }

}
