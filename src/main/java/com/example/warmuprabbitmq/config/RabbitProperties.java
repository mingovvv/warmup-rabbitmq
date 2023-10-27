package com.example.warmuprabbitmq.config;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@Slf4j
@Getter
@ToString
@AllArgsConstructor
@ConfigurationProperties("spring.rabbitmq")
public class RabbitProperties {

    private String host;
    private int port;
    private String username;
    private String password;
    private List<String> queueName;
    private String directExchange;
    private String topicExchange;
    private String fanoutExchange;

    @PostConstruct
    void invoke() {
        log.info("{}", this);
    }

}
