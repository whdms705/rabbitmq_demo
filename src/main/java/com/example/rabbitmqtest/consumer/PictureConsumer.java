package com.example.rabbitmqtest.consumer;

import com.example.rabbitmqtest.entity.EmployeeResponse;
import com.example.rabbitmqtest.entity.PictureResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class PictureConsumer {
    private ObjectMapper objectMapper = new ObjectMapper();

    private static final Logger log = LoggerFactory.getLogger(PictureConsumer.class);

    @RabbitListener(queues = "q.picture.image")
    public void listen(String message){
        PictureResponse emp;
        System.out.println(message);
        try {
            emp = objectMapper.readValue(message, PictureResponse.class);
            log.info("PictureResponse is {}",emp);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
