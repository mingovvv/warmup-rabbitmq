package com.example.warmuprabbitmq.listner;

import com.example.warmuprabbitmq.dto.MessageDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class WarmupListners {

//    @RabbitListener(queues = "mingo.fruit.apple")
//    public void receiveMessage1(MessageDto MessageDto) {
//        log.info("Received Queue name : [{}], message: [{}]", "mingo.fruit.apple", MessageDto.toString());
//    }
//
//    @RabbitListener(queues = "mingo.fruit.banana")
//    public void receiveMessage2(MessageDto MessageDto) {
//        log.info("Received Queue name : [{}], message: [{}]", "mingo.fruit.banana", MessageDto.toString());
//    }
//
//    @RabbitListener(queues = "mingo.fruit.orange")
//    public void receiveMessage3(MessageDto MessageDto) {
//        log.info("Received Queue name : [{}], message: [{}]", "mingo.fruit.orange", MessageDto.toString());
//    }
//
//    @RabbitListener(queues = "mingo.location.seoul")
//    public void receiveMessage4(MessageDto MessageDto) {
//        log.info("Received Queue name : [{}], message: [{}]", "mingo.location.seoul", MessageDto.toString());
//    }
//
//    @RabbitListener(queues = "mingo.location.busan")
//    public void receiveMessage5(MessageDto MessageDto) {
//        log.info("Received Queue name : [{}], message: [{}]", "mingo.location.busan", MessageDto.toString());
//    }
//
//    @RabbitListener(queues = "mingo.location.daegu")
//    public void receiveMessage6(MessageDto MessageDto) {
//        log.info("Received Queue name : [{}], message: [{}]", "mingo.location.busan", MessageDto.toString());
//    }

}
