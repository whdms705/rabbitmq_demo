package com.example.rabbitmqtest.producer;

import com.example.rabbitmqtest.entity.EmployeeRequest;
import com.example.rabbitmqtest.entity.PictureRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PictureProducer {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    private ObjectMapper objectMapper = new ObjectMapper();//java클래스를 json으로 변환하기 위해

    public void sendMessage(PictureRequest picture){
        try {
            String json = objectMapper.writeValueAsString(picture);//java클래스를 json string으로 변환해줌
            rabbitTemplate.convertAndSend("x.picture", picture.getType(),json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
