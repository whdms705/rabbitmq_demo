package com.example.rabbitmqtest.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;

@Service
public class FixedRateConsumer {
    private Logger log = LoggerFactory.getLogger(FixedRateConsumer.class);

    @RabbitListener(queues = "course.fixedrate" , concurrency = "3")// 동시 소비자 : 3
    public void listen(String message) throws InterruptedException {
        log.info("FixedRete Consumer: "+message+" "+Thread.currentThread().getName());
        Thread.sleep(ThreadLocalRandom.current().nextLong(2000));
    }
}
