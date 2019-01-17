package com.lxy.webcontainer.App;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;

public class AppWithoutServlet implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {

    }

    public static void main(String[] args) {
        SpringApplication.run(AppWithoutServlet.class,args);
    }
}
