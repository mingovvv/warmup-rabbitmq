package com.example.warmuprabbitmq.controller;

import com.example.warmuprabbitmq.dto.ExchangeType;
import com.example.warmuprabbitmq.dto.MessageDto;
import com.example.warmuprabbitmq.producer.RabbitExchange;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
public class WarmupController {

    /**
     * POST http://localhost:8080/rabbitmq/send
     * Content-Type: application/json
     *
     * {
     *   "message": "[adam] 유저님에게만 보내는 개인 메시지입니다.",
     *   "exchangeType": "DIRECT",
     *   "routingKey": "user.human.adam"
     *
     * }
     */

    private final Map<ExchangeType, RabbitExchange> rabbitExchangeMap;

    @PostMapping("/rabbitmq/send")
    public ResponseEntity<String> produce(@RequestBody MessageDto messageDto) {
        rabbitExchangeMap.getOrDefault(messageDto.getExchangeType(), rabbitExchangeMap.get(ExchangeType.DIRECT))
                .sendMessage(messageDto);
        return ResponseEntity.ok(messageDto.getExchangeType().name() + " successfully send");
    }

}
