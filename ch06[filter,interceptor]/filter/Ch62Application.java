package com.example.ch62;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;


@ServletComponentScan
public class Ch62Application {

    public static void main(String[] args) {
        SpringApplication.run(Ch62Application.class, args);
    }

}
