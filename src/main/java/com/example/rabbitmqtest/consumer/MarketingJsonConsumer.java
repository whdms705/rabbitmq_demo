package com.example.rabbitmqtest.consumer;

import com.example.rabbitmqtest.entity.EmployeeResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class MarketingJsonConsumer {
    private ObjectMapper objectMapper = new ObjectMapper();

    private static final Logger log = LoggerFactory.getLogger(MarketingJsonConsumer.class);

    @RabbitListener(queues = "q.hr.marketing")
    public void listen(String message){
        EmployeeResponse emp;
        System.out.println(message);
        try {
            emp = objectMapper.readValue(message, EmployeeResponse.class);
            log.info("marketing Employee is {}",emp);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
