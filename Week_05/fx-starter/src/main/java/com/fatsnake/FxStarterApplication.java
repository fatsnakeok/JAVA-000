package com.fatsnake;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication

public class FxStarterApplication {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(FxStarterApplication.class, args);

        String[] beanNames = ctx.getBeanDefinitionNames();

        for (String beanName : beanNames) {
            System.out.println(beanName);
        }
    }

}
