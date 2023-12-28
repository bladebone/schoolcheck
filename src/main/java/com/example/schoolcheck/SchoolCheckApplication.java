package com.example.schoolcheck;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class SchoolCheckApplication {

    public static void main(String[] args) {
        SpringApplication.run(SchoolCheckApplication.class, args);
    }

}
