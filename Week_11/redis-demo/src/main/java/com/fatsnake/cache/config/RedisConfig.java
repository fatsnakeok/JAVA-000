package com.fatsnake.cache.config;

/**
 * @Auther: fatsnake
 * @Description":
 * @Date:2021/1/2 19:53
 * Copyright (c) 2021, zaodao All Rights Reserved.
 */

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fatsnake.cache.service.OrderListener;
import com.fatsnake.cache.service.RepertoryListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.net.UnknownHostException;

/**
 * redis配置类
 *
 * @author zcc ON 2018/3/19
 **/
@Configuration
@EnableCaching //开启注解
public class RedisConfig {


    @Value("${spring.redis.host:127.0.0.1}")
    private String host;
    @Value("${spring.redis.port:6379}")
    private Integer port;
    @Value("${spring.redis.password:}")
    private String password;
    @Value("${spring.redis.database:0}")
    private Integer database;

    @Value("${spring.redis.jedis.pool.max-active:8}")
    private Integer maxActive;
    @Value("${spring.redis.jedis.pool.max-idle:8}")
    private Integer maxIdle;
    @Value("${spring.redis.jedis.pool.max-wait:-1}")
    private Long maxWait;
    @Value("${spring.redis.jedis.pool.min-idle:0}")
    private Integer minIdle;


    /**
     * redis消息监听器容器
     * 可以添加多个监听不同话题的redis监听器，只需要把消息监听器和相应的消息订阅处理器绑定，该消息监听器
     * 通过反射技术调用消息订阅处理器的相关方法进行一些业务处理
     * @param connectionFactory
     * @return
     */
    @Bean
    //相当于xml中的bean
    RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory,
                                            MessageListenerAdapter OrderAdapter, MessageListenerAdapter RepertoryAdapter) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        //订阅了一个叫chat 的通道
        container.addMessageListener(OrderAdapter, new PatternTopic("order"));
        container.addMessageListener(RepertoryAdapter, new PatternTopic("repertory"));
        //这个container 可以添加多个 messageListener
        return container;
    }

    /**
     * 消息监听器适配器，绑定消息处理器
     *
     * @return
     */
    @Bean
    MessageListenerAdapter OrderAdapter() {
        return new MessageListenerAdapter(new OrderListener());
    }

    /**
     * 消息监听器适配器，绑定消息处理器
     *
     * @return
     */
    @Bean
    MessageListenerAdapter RepertoryAdapter() {
        return new MessageListenerAdapter(new RepertoryListener());
    }



    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        RedisStandaloneConfiguration config = new RedisStandaloneConfiguration();
        config.setHostName(host);
        config.setPort(port);
        config.setPassword(RedisPassword.of(password));
        JedisConnectionFactory connectionFactory = new JedisConnectionFactory(config);
        return connectionFactory;
    }



    @Bean
    public RedisTemplate setRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate redisTemplate = new RedisTemplate();
        redisTemplate.setConnectionFactory(redisConnectionFactory);

        // 使用Jackson2JsonRedisSerialize 替换默认序列化
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();

        // key采用String的序列化方式
        redisTemplate.setKeySerializer(stringRedisSerializer);
        // hash的key也采用String的序列化方式z
        redisTemplate.setHashKeySerializer(stringRedisSerializer);
        // value序列化方式采用jackson
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
        // hash的value序列化方式采用jackson
        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);
        redisTemplate.afterPropertiesSet();
        redisTemplate.afterPropertiesSet();
        System.out.println(redisTemplate);
        return redisTemplate;
    }
}