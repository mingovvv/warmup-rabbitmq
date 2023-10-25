package com.example.warmuprabbitmq.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Configuration
public class RabbitConfig {

    private final RabbitProperties rabbitProperties;

    public RabbitConfig(RabbitProperties rabbitProperties) {
        this.rabbitProperties = rabbitProperties;
    }

    @Bean
    public Declarables fanoutBindings() {
        List<String> queueName = rabbitProperties.getQueueName().stream().toList();

        List<Declarable> declarables = new ArrayList<>();
        FanoutExchange fanoutExchange = new FanoutExchange(rabbitProperties.getFanoutExchange());
        List<Binding> bindings = new ArrayList<>();

        queueName.forEach(queue -> {
            Queue q = new Queue(queue);
            declarables.add(q);
            bindings.add(BindingBuilder.bind(q).to(fanoutExchange));
        });

        declarables.add(fanoutExchange);
        declarables.addAll(bindings);

        return new Declarables(declarables);
    }

    @Bean
    public Declarables topicHumanBindings() {

        List<String> queueName = rabbitProperties.getQueueName().stream().filter(q -> q.startsWith("user.human")).toList();

        List<Declarable> declarables = new ArrayList<>();
        TopicExchange topicExchange = new TopicExchange(rabbitProperties.getTopicExchange());
        List<Binding> bindings = new ArrayList<>();

        queueName.forEach(queue -> {
            Queue q = new Queue(queue);
            declarables.add(q);
            bindings.add(BindingBuilder.bind(q).to(topicExchange).with("human.*"));
        });

        declarables.add(topicExchange);
        declarables.addAll(bindings);

        return new Declarables(declarables);

    }

    @Bean
    public Declarables topicDevilBindings() {

        List<String> queueName = rabbitProperties.getQueueName().stream().filter(q -> q.startsWith("user.devil")).toList();

        List<Declarable> declarables = new ArrayList<>();
        TopicExchange topicExchange = new TopicExchange(rabbitProperties.getTopicExchange());
        List<Binding> bindings = new ArrayList<>();

        queueName.forEach(queue -> {
            Queue q = new Queue(queue);
            declarables.add(q);
            bindings.add(BindingBuilder.bind(q).to(topicExchange).with("devil.*"));
        });

        declarables.add(topicExchange);
        declarables.addAll(bindings);

        return new Declarables(declarables);

    }

    @Bean
    public Declarables directBindings() {

        List<String> queueName = rabbitProperties.getQueueName().stream().toList();

        List<Declarable> declarables = new ArrayList<>();
        DirectExchange directExchange = new DirectExchange(rabbitProperties.getDirectExchange());
        List<Binding> bindings = new ArrayList<>();

        queueName.forEach(queue -> {
            Queue q = new Queue(queue);
            declarables.add(q);
            bindings.add(BindingBuilder.bind(q).to(directExchange).with(queue));
        });

        declarables.add(directExchange);
        declarables.addAll(bindings);

        return new Declarables(declarables);

    }

//    /**
//     * Queue 빈 등록
//     */
//    @Bean
//    public Queue queue1() {
//        return new Queue("mingo.fruit.apple");
//    }
//
//    @Bean
//    public Queue queue2() {
//        return new Queue("mingo.fruit.banana");
//    }
//
//    @Bean
//    public Queue queue3() {
//        return new Queue("mingo.fruit.orange");
//    }
//
//    @Bean
//    public Queue queue4() {
//        return new Queue("mingo.location.seoul");
//    }
//
//    @Bean
//    public Queue queue5() {
//        return new Queue("mingo.location.busan");
//    }
//
//    /**
//     * Exchange 빈 등록
//     *
//     */
//    @Bean
//    public DirectExchange exchange1() {
//        return new DirectExchange("mingo.direct");
//    }
//
//    @Bean
//    public TopicExchange exchange2() {
//        return new TopicExchange("mingo.topic");
//    }
//
//    @Bean
//    public FanoutExchange exchange3() {
//        return new FanoutExchange("mingo.fanout");
//    }
//
//
//    /**
//     * Exchange와 Queue를 routing하는 Binding객체 빈 등록
//     */
//    @Bean
//    public Binding binding1(@Qualifier("queue1") Queue queue, TopicExchange exchange) {
//        return BindingBuilder.bind(queue)
//                 .to(exchange)
//                 .with("mingo.fruit.*");
//    }
//
//    @Bean
//    public Binding binding2(@Qualifier("queue2") Queue queue, TopicExchange exchange) {
//        return BindingBuilder.bind(queue)
//                .to(exchange)
//                .with("mingo.fruit.*");
//    }
//
//    @Bean
//    public Binding binding3(@Qualifier("queue3") Queue queue, TopicExchange exchange) {
//        return BindingBuilder.bind(queue)
//                .to(exchange)
//                .with("mingo.#");
//    }
//
//    @Bean
//    public Binding binding4(@Qualifier("queue4") Queue queue, TopicExchange exchange) {
//        return BindingBuilder.bind(queue)
//                .to(exchange)
//                .with("mingo.location.*");
//
//    }
//
//    @Bean
//    public Binding binding5(@Qualifier("queue4") Queue queue, FanoutExchange exchange) {
//        return BindingBuilder.bind(queue)
//                .to(exchange);
//
//    }


    /**
     * RabbitMQ 연결을 위한 ConnectionFactory 빈을 생성하여 반환
     *
     * @return ConnectionFactory 객체
     */
    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setHost(rabbitProperties.getHost());
        connectionFactory.setPort(rabbitProperties.getPort());
        connectionFactory.setUsername(rabbitProperties.getUsername());
        connectionFactory.setPassword(rabbitProperties.getPassword());
        return connectionFactory;
    }

    /**
     * RabbitTemplate을 생성하여 반환
     *
     * @param connectionFactory RabbitMQ와의 연결을 위한 ConnectionFactory 객체
     * @return RabbitTemplate 객체
     */
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        // JSON 형식의 메시지를 직렬화하고 역직렬할 수 있도록 설정
        rabbitTemplate.setMessageConverter(jackson2JsonMessageConverter());
        return rabbitTemplate;
    }

    /**
     * Jackson 라이브러리를 사용하여 메시지를 JSON 형식으로 변환하는 MessageConverter 빈을 생성
     *
     * @return MessageConverter 객체
     */
    @Bean
    public MessageConverter jackson2JsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpAdmin amqpAdminOrder() {
        return new RabbitAdmin(connectionFactory());
    }

}
