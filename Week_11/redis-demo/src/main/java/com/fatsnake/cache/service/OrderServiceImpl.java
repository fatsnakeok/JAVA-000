package com.fatsnake.cache.service;

import com.fatsnake.cache.config.RedisRepository;
import com.fatsnake.cache.interfaces.IOrderService;
import com.fatsnake.cache.lock.RedisLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

/**
 * @Auther: fatsnake
 * @Description":
 * @Date:2021/1/3 09:23
 * Copyright (c) 2021, zaodao All Rights Reserved.
 */
@Service
public class OrderServiceImpl implements IOrderService {

    @Autowired
    private RedisRepository redisRepository;

    @Autowired
    private RedisLock lock;

    /**
     * 分布式锁key
     */
    @Value("${repertory.amount.lockKey}")
    private String REPERTORY_AMOUNT_LOCK_KEY;


    /**
     * 库存最大数量
     * 可放在nacos或是redis中做成可配置的
     */
    @Value("${repertory.amount.max}")
    private int REPERTORY_AMOUNT_MAX;

    /**
     * 已购买数量
     */
    @Value("${repertory.amount.amountOrder}")
    private String REPERTORY_AMOUNT_ORDER_KEY;

    /**
     * 订单计数器
     *
     * @return String
     */
    public String denialOfService(String userId) {

        try {
            lock.tryLock(REPERTORY_AMOUNT_LOCK_KEY, userId);
            Integer orderNum = redisRepository.get(REPERTORY_AMOUNT_ORDER_KEY, Integer.class);
            if (orderNum == null || REPERTORY_AMOUNT_MAX > orderNum) {
                // 已购买库存加1
                redisRepository.incr(REPERTORY_AMOUNT_ORDER_KEY);
            } else {
                return "没有库存了";
            }
            int residueNum = REPERTORY_AMOUNT_MAX - redisRepository.get(REPERTORY_AMOUNT_ORDER_KEY, Integer.class);
            return "剩余数量" + residueNum;
        } finally {
            lock.releaseLock(REPERTORY_AMOUNT_LOCK_KEY, userId);
        }
    }
}
