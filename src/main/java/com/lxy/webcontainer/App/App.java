package com.lxy.webcontainer.App;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.concurrent.Callable;

@Controller
@EnableAutoConfiguration(exclude = WebMvcAutoConfiguration.class)
@ComponentScan
@EnableWebMvc
public class App {

    private static final String MESSAGE = "Hello, World!这是一条测试语句";

    public static void main(String[] args) {
        SpringApplication.run(App.class,args);
    }

    @RequestMapping(value = "/plaintext", produces = "text/plain")
    @ResponseBody
    public String plaintext() {
        return MESSAGE;
    }

    @RequestMapping(value = "/async", produces = "text/plain")
    @ResponseBody
    public Callable<String> async() {
        return () -> MESSAGE;
    }
    @RequestMapping(value = "/json", produces = "application/json")
    @ResponseBody
    public Message json() {
        return new Message(MESSAGE);
    }

    private static class Message {
        private final String message;
        public Message(String message) {
            this.message = message;
        }
        public String getMessage() {
            return message;
        }
    }

}
