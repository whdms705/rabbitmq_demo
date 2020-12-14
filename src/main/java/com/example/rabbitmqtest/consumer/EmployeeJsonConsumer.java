package com.example.rabbitmqtest.consumer;

import com.example.rabbitmqtest.entity.EmployeeRequest;
import com.example.rabbitmqtest.entity.EmployeeResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;


@Service
public class EmployeeJsonConsumer {
    private ObjectMapper objectMapper = new ObjectMapper();

    private static final Logger log = LoggerFactory.getLogger(EmployeeJsonConsumer.class);

    @RabbitListener(queues = "course.employee")
    public void listen(String message){
        EmployeeResponse emp;
        System.out.println(message);
        try {
            emp = objectMapper.readValue(message, EmployeeResponse.class);
            log.info("Employee is {}",emp);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
