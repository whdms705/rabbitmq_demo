package com.example.rabbitmqtest;

import com.example.rabbitmqtest.entity.EmployeeRequest;
import com.example.rabbitmqtest.entity.PictureRequest;
import com.example.rabbitmqtest.producer.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@SpringBootApplication
//@EnableScheduling
public class RabbitmqtestApplication implements CommandLineRunner {

    @Autowired
    private HelloRabbitProducer helloRabbitProducer;

    @Autowired
    private EmployeeJsonProducer employeeJsonProducer;

    @Autowired
    private HumanResourceProducer humanResourceProducer; // fanout ex

    @Autowired
    private PictureProducer pictureProducer; // direct ex

    @Autowired
    private PictureProducer2 pictureProducer2; // topic

    @Autowired
    private RetryPictureProducer retryPictureProducer; // retry

    private final List<String> SOURCES = new ArrayList<>(Arrays.asList("mobile", "web"));
    private final List<String> TYPES = new ArrayList<>(Arrays.asList("jpg", "png", "svg"));

    public static void main(String[] args) {
        SpringApplication.run(RabbitmqtestApplication.class, args);
    }

    @Override
    public void run(String... args) throws JsonProcessingException {
        for(int i=0;i<10;i++){
            PictureRequest p = new PictureRequest();

            p.setName("Picture "+i);
            p.setSize(ThreadLocalRandom.current().nextLong(9001,100001));
            p.setSource(SOURCES.get(i % SOURCES.size()));
            p.setType(TYPES.get(i % TYPES.size()));
            retryPictureProducer.sendMessage(p);
        }
    }
}
