package com.example.rabbitmqtest.consumer;

import com.example.rabbitmqtest.entity.PictureResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class SpringPictureConsumer {
    private ObjectMapper objectMapper = new ObjectMapper();

    private static final Logger log = LoggerFactory.getLogger(SpringPictureConsumer.class);

    @RabbitListener(queues = "q.spring.image.work")
    public void listenImage(String message){
        log.info(message);
        try {
            PictureResponse picture = objectMapper.readValue(message, PictureResponse.class);
            log.info("PictureResponse is {}",picture);
            if(picture.getSize() > 9000){
                throw new IOException("Image : "+picture.getName() + " size too large : "+picture.getSize());
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RabbitListener(queues = "q.spring.vector.work")
    public void listenVector(String message){
        log.info(message);
        try {
            PictureResponse picture = objectMapper.readValue(message, PictureResponse.class);
            log.info("PictureResponse is {}",picture);
            if(picture.getSize() > 9000){
                throw new IOException("Vector : "+picture.getName() + " size too large : "+picture.getSize());
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
