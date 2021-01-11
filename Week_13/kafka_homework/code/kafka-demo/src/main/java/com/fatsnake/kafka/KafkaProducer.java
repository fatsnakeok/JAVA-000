package com.fatsnake.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @Auther: fatsnake
 * @Description":
 * @Date:2021/1/11 18:47
 * Copyright (c) 2021, zaodao All Rights Reserved.
 */
@Component
public class KafkaProducer {

    @Autowired
    private KafkaTemplate kafkaTemplate;

    public void send(String topic, String message) {
        kafkaTemplate.send(topic, message);
        //发送方式很多种可以自己研究一下
    }
}
