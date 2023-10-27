package com.example.warmuprabbitmq.config;

import com.example.warmuprabbitmq.dto.ExchangeType;
import com.example.warmuprabbitmq.producer.DirectProducer;
import com.example.warmuprabbitmq.producer.FanoutProducer;
import com.example.warmuprabbitmq.producer.RabbitExchange;
import com.example.warmuprabbitmq.producer.TopicProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class AppConfig {

    private final DirectProducer directProducer;
    private final TopicProducer topicProducer;
    private final FanoutProducer fanoutProducer;

    @Bean
    public Map<ExchangeType, RabbitExchange> rabbitExchangeMap() {
        Map<ExchangeType, RabbitExchange> strategyMap = new HashMap<>();
        strategyMap.put(ExchangeType.DIRECT, directProducer);
        strategyMap.put(ExchangeType.TOPIC, topicProducer);
        strategyMap.put(ExchangeType.FANOUT, fanoutProducer);
        return strategyMap;
    }

}
