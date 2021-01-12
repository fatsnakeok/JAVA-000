package com.fatsnake.mq;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import javax.jms.Destination;


/**
 * @Auther: fatsnake
 * @Description":
 * @Date:2021/1/10 20:46
 * Copyright (c) 2021, zaodao All Rights Reserved.
 */
@Component
public class Producer {

    /**
     * jmsMessagingTemplate jmsMessagingTemplate
     */
    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    /**
     * 点对点模式
     * @param msg             消息内容
     * @param destinationName destinationName
     * @return boolean
     */
    public boolean sendMessage(String msg, String destinationName) {
        boolean result = true;

        try {
            Destination destination = new ActiveMQQueue(destinationName);
            jmsMessagingTemplate.convertAndSend(destination, msg);
        } catch (Exception e) {
            e.printStackTrace();
            result = false;
        }
        return result;
    }


    /**
     * 发布订阅模式
     * @param msg
     * @param destinationName
     * @return
     */
    public boolean publish(String msg, String destinationName) {

        boolean result = true;
        try {
            Destination destination = new ActiveMQTopic(destinationName);
            jmsMessagingTemplate.convertAndSend(destination, msg);
        } catch (Exception e) {
            e.printStackTrace();
            result = false;
        }
        return result;
    }


}
