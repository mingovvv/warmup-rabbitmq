package com.example.warmuprabbitmq.listner;

import com.example.warmuprabbitmq.dto.MessageDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserListener {

    @RabbitListener(queues = "#{'${spring.rabbitmq.queue-name}'.split(',')[0]}")
    public void receiveMessage1(MessageDto MessageDto) {
        log.info("Received Queue name : [{}], message: [{}]", "user.human.eric", MessageDto.toString());
    }

    @RabbitListener(queues = "#{'${spring.rabbitmq.queue-name}'.split(',')[1]}")
    public void receiveMessage2(MessageDto MessageDto) {
        log.info("Received Queue name : [{}], message: [{}]", "user.human.aron", MessageDto.toString());
    }

    @RabbitListener(queues = "#{'${spring.rabbitmq.queue-name}'.split(',')[2]}")
    public void receiveMessage3(MessageDto MessageDto) {
        log.info("Received Queue name : [{}], message: [{}]", "user.human.adam", MessageDto.toString());
    }

    @RabbitListener(queues = "#{'${spring.rabbitmq.queue-name}'.split(',')[3]}")
    public void receiveMessage4(MessageDto MessageDto) {
        log.info("Received Queue name : [{}], message: [{}]", "user.devil.emma", MessageDto.toString());
    }

    @RabbitListener(queues = "#{'${spring.rabbitmq.queue-name}'.split(',')[4]}")
    public void receiveMessage5(MessageDto MessageDto) {
        log.info("Received Queue name : [{}], message: [{}]", "user.devil.issabel", MessageDto.toString());
    }

    @RabbitListener(queues = "#{'${spring.rabbitmq.queue-name}'.split(',')[5]}")
    public void receiveMessage6(MessageDto MessageDto) {
        log.info("Received Queue name : [{}], message: [{}]", "admin.none.gm", MessageDto.toString());
    }

}
