package com.aditya.Acme;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

// same as @Configuration @EnableAutoConfiguration @ComponentScan

@SpringBootApplication(scanBasePackages={"com.aditya.Acme"})
@EnableScheduling
public class Acme{

    public static void main(String[] args) {
        SpringApplication.run(Acme.class,args);
    }
}

