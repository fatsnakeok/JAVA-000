package com.fatsnake.cache.service;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

/**
 * @Auther: fatsnake
 * @Description":
 * @Date:2021/1/3 09:54
 * Copyright (c) 2021, zaodao All Rights Reserved.
 */
@Component
public class OrderListener implements MessageListener {
    @Override
    public void onMessage(Message message, byte[] bytes) {
        System.out.println("我是订单监听" + message.toString());
    }
}
