package com.fatsnake.kafka;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class KafkaApplicationTests {

    @Autowired
    private KafkaProducer kafkaProducer;


    @Test
    void contextLoads() {

    }

    @Test
    void testSend() {
        for (int i = 0; i < 100; i++) {
            kafkaProducer.send("topic", "message" + i);
        }
    }


}
