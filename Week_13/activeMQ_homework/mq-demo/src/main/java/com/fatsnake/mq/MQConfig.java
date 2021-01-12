package com.fatsnake.mq;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.config.SimpleJmsListenerContainerFactory;

import javax.jms.ConnectionFactory;

/**
 * @Auther: fatsnake
 * @Description":
 * @Date:2021/1/10 21:30
 * Copyright (c) 2021, zaodao All Rights Reserved.
 */
@Configuration
public class MQConfig {

    /**
     * 在pub/sub模式中，对消息的监听需要对containerFactory进行以下配置
     *
     * @param connectionFactory
     * @return
     */
    @Bean
    public JmsListenerContainerFactory<?> myJmsContainerFactory(ConnectionFactory connectionFactory) {
        SimpleJmsListenerContainerFactory factory = new SimpleJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setPubSubDomain(true);
        return factory;
    }
}
