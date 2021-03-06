package com.example.rabbitmqtest.consumer;

import java.io.IOException;

import com.example.rabbitmqtest.entity.EmployeeResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;

//@Service
public class RetryMarketingConsumer {

    private static final Logger log = LoggerFactory.getLogger(RetryMarketingConsumer.class);
    private ObjectMapper objectMapper;

    public RetryMarketingConsumer() {
        this.objectMapper = new ObjectMapper();
    }

    @RabbitListener(queues = "q.guideline2.marketing.work")
    public void listen(Message message, Channel channel)
            throws InterruptedException, JsonParseException, JsonMappingException, IOException {
        EmployeeResponse e = objectMapper.readValue(message.getBody(), EmployeeResponse.class);
        log.info("On marketing : " + e);
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }
}
