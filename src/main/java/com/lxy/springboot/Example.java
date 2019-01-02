package com.lxy.springboot;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
public class Example implements CommandLineRunner {

    @RequestMapping("/")
    String home(){
        return "hello World";
    }


    public static void main(String[] args) {
        SpringApplication.run(Example.class,args);
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
