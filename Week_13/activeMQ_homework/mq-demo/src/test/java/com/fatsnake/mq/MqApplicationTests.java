package com.fatsnake.mq;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MqApplicationTests {


    @Autowired
    private Producer producer;


    @Test
    void contextLoads() {
    }


    @Test
    void testSend() {
        for (int i = 0; i < 100; i++) {
            producer.sendMessage("wo jiao  fatsnake" + i, "user.create");
        }
    }

    @Test
    void testPublish() {
        for (int i = 0; i < 100; i++) {
            producer.publish("Order" + i, "order.topic");
        }
    }
}
