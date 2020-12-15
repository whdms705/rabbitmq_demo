package com.example.rabbitmqtest.producer;

import com.example.rabbitmqtest.entity.PictureRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PictureProducer2 {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    private ObjectMapper objectMapper = new ObjectMapper();//java클래스를 json으로 변환하기 위해

    /*"단어.단어", "단어.단어.단어", "*.단어.*, "단어.#" 등등..
    > *(star) : 임의의 한 단어를 대신합니다.
    > #(hash) : 0 개 이상의 단어를 대신합니다.*/
    public void sendMessage(PictureRequest picture)throws JsonProcessingException{
        StringBuilder sb = new StringBuilder();
        sb.append(picture.getSource());
        sb.append(".");

        if(picture.getSize() > 4000){
            sb.append("large");
        }else{
            sb.append("small");
        }
        sb.append(".");
        sb.append(picture.getType());

        String json = objectMapper.writeValueAsString(picture);//java클래스를 json string으로 변환해줌
        rabbitTemplate.convertAndSend("x.pricture2", sb.toString(),json);

    }
}
