package com.fatsnake.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @Auther: fatsnake
 * @Description":
 * @Date:2021/1/11 18:49
 * Copyright (c) 2021, zaodao All Rights Reserved.
 */
@Component
public class KafkaCustomer {

    @KafkaListener(topics = {"topic"})
    public void receive(String message){
        System.out.println("topic========topic");
        System.out.println(message);
    }
}
