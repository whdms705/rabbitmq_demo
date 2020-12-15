package com.example.rabbitmqtest.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter @Getter @ToString
public class PictureResponse {
    private String name;
    private String type;
    private String source;
    private long size;
}
