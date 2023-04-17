package com.sparta.myfirstblog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class MyfirstblogApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyfirstblogApplication.class, args);
    }

}