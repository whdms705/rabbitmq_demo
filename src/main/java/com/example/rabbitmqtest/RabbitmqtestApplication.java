package com.example.rabbitmqtest;

import com.example.rabbitmqtest.entity.EmployeeRequest;
import com.example.rabbitmqtest.producer.EmployeeJsonProducer;
import com.example.rabbitmqtest.producer.HelloRabbitProducer;
import com.example.rabbitmqtest.producer.HumanResourceProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
//@EnableScheduling
public class RabbitmqtestApplication implements CommandLineRunner {

    @Autowired
    private HelloRabbitProducer helloRabbitProducer;

    @Autowired
    private EmployeeJsonProducer employeeJsonProducer;

    @Autowired
    private HumanResourceProducer humanResourceProducer;

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
    }
}
