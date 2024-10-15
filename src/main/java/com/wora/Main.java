package com.wora;

import com.wora.config.AppConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);


    }
}