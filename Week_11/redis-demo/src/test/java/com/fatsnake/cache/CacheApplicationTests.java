package com.fatsnake.cache;

import com.fatsnake.cache.config.RedisRepository;
//import com.fatsnake.cache.lock.RedisLock;
import com.fatsnake.cache.lock.RedisLock;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
class CacheApplicationTests {

    @Autowired
    private RedisRepository redisRepository;

    @Autowired
    private RedisLock redisLock;

    private String lockKey = "lockKeyFat";
    private String clientId = "fatClient";
    long seconds = 100000000;

    @Test
    void contextLoads() {

        redisRepository.set("kk", "张三");
        System.out.println(redisRepository.get("kk"));
    }


    @Test
    void testLock1() throws InterruptedException {
        try {
            redisLock.tryLock(lockKey, clientId);

            for (int i = 0; i < 15; i++) {
                redisRepository.set("rkey:" + i, "rvalue:" + i);
            }

            // 代码块 W1
            while (true) {
                Thread.sleep(2000);
            }
        } finally {
            redisLock.releaseLock(lockKey,clientId);
        }
    }

    @Test
    void testLock2() throws InterruptedException {
        testLock1();
    }

}
