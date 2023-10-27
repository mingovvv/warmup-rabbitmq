package com.example.warmuprabbitmq.dto;

import lombok.Data;

@Data
public class MessageDto {

    private String message;
    private ExchangeType exchangeType;
    private String routingKey;

}
