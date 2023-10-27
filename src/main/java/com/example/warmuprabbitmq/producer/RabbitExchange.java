package com.example.warmuprabbitmq.producer;

import com.example.warmuprabbitmq.dto.MessageDto;

public interface RabbitExchange {

    void sendMessage(MessageDto messageDto);

}
