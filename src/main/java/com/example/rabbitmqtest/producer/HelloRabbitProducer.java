package com.example.rabbitmqtest.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HelloRabbitProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    // exchange가 아닌 기본 교환(바로 queue로)
    public void sendHello(String name){
        rabbitTemplate.convertAndSend("course.hello","hello "+name);// 큐이름과 , 메세지
    }
}
