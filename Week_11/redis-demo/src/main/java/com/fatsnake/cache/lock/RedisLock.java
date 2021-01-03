package com.fatsnake.cache.lock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.UUID;

/**
 * Redis 分布式锁实现
 */
@Service
public class RedisLock {

    // http://redisdoc.com/string/set.html

    private static final Long SUCCESS = 1L;
    private static String script1 = "return redis.call('SET', KEYS[1], ARGV[1], 'NX', 'PX', ARGV[2]) ";
    private RedisScript lockRedisScript1 = new DefaultRedisScript<>(script1, String.class);
    private static String script2 = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
    private RedisScript unLockRedisScript = new DefaultRedisScript<>(script2, String.class);

    /**
     * 默认有效期 3000毫秒 == 3秒
     */
    private static final Long DEFAULT_EXPIRETIME = 3000L;

    @Autowired
    private StringRedisTemplate redisTemplate;


    public boolean tryLock(String lockKey, String value) {
        return tryLock(lockKey, value, DEFAULT_EXPIRETIME);
    }


    /**
     * 获取锁
     *
     * @param lockKey    redis的key
     * @param value      redis的value要求是随机串，防止释放其他请求的锁
     * @param expireTime redis的key 的过期时间  单位（毫秒) 防止死锁，导致其他请求无法正常执行业务
     * @return
     */
    public boolean tryLock(String lockKey, String value, long expireTime) {

        long timestamp = System.currentTimeMillis();
        //锁的名称
        lockKey = lockKey + ".lock";

        UUID uuid = UUID.randomUUID();

        int tryCount = 0;

        //在超时之前，循环尝试拿锁
        while (expireTime == -1 || (System.currentTimeMillis() - timestamp) < expireTime) {
            //对非string类型的序列化
            redisTemplate.setKeySerializer(new StringRedisSerializer());
            redisTemplate.setValueSerializer(new StringRedisSerializer());
            Object result = redisTemplate.execute(lockRedisScript1, Collections.singletonList(lockKey), value, String.valueOf(expireTime));


            tryCount++;
            //返回“OK”代表拿到锁
            if (result != null && result.equals("OK")) {
                return true;
            } else {
                try {
                    //如果失败，休息50毫秒继续重试（自旋锁）
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    /**
     * 释放锁
     *
     * @param lockKey redis的key
     * @param value   redis的value  只有value比对一致，才能确定是本请求 加的锁 才能正常释放
     * @return
     */
    public boolean releaseLock(String lockKey, String value) {
        try {
            Object result = redisTemplate.execute(unLockRedisScript, Collections.singletonList(lockKey), value);
            if (SUCCESS.equals(result)) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
