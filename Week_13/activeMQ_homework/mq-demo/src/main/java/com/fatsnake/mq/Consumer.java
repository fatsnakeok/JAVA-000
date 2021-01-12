package com.fatsnake.mq;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * @Auther: fatsnake
 * @Description":
 * @Date:2021/1/10 20:55
 * Copyright (c) 2021, zaodao All Rights Reserved.
 */

@Component
public class Consumer {

    @JmsListener(destination = "user.create")
    public void receive(String message) {
        System.out.println("name.msg消费者：" + message);
    }


    @JmsListener(destination = "order.topic",containerFactory = "myJmsContainerFactory")
    public void subscribe(String message){
        System.out.println("order.topic订阅者一："+message);
    }

    @JmsListener(destination = "order.topic",containerFactory = "myJmsContainerFactory")
    public void subscribe1(String message){
        System.out.println("order.topic订阅者二："+message);
    }
}
