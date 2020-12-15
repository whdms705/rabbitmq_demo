package com.example.rabbitmqtest;

import com.example.rabbitmqtest.entity.EmployeeRequest;
import com.example.rabbitmqtest.entity.PictureRequest;
import com.example.rabbitmqtest.producer.EmployeeJsonProducer;
import com.example.rabbitmqtest.producer.HelloRabbitProducer;
import com.example.rabbitmqtest.producer.HumanResourceProducer;
import com.example.rabbitmqtest.producer.PictureProducer;
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

    private final List<String> SOURCES = new ArrayList<>(Arrays.asList("mobile", "web"));
    private final List<String> TYPES = new ArrayList<>(Arrays.asList("jpg", "png", "avg"));

    public static void main(String[] args) {
        SpringApplication.run(RabbitmqtestApplication.class, args);
    }

    @Override
    public void run(String... args){
        for(int i=0;i<5;i++){
            EmployeeRequest employee = new EmployeeRequest("emp "+i,"Employee "+i, LocalDate.now());
            employeeJsonProducer.sendMessage(employee);
            humanResourceProducer.sendMessage(employee);
        }
        helloRabbitProducer.sendHello("title");

        for(int i=0;i<10;i++){
            PictureRequest p = new PictureRequest();

            p.setName("Picture "+i);
            p.setSize(ThreadLocalRandom.current().nextLong(1,100001));
            p.setSource(SOURCES.get(i % SOURCES.size()));
            p.setType(TYPES.get(i % TYPES.size()));
            pictureProducer.sendMessage(p);
        }
    }
}
