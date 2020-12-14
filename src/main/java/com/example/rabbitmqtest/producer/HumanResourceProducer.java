package com.example.rabbitmqtest.producer;

import com.example.rabbitmqtest.entity.EmployeeRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HumanResourceProducer {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    private ObjectMapper objectMapper = new ObjectMapper();//java클래스를 json으로 변환하기 위해

    public void sendMessage(EmployeeRequest emp){
        try {
            String json = objectMapper.writeValueAsString(emp);//java클래스를 json string으로 변환해줌
            rabbitTemplate.convertAndSend("x.hr","",json);// rounting key가없어 공백으로 처리
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
