package common;

/**
 * @Auther: fatsnake
 * @Description":
 * @Date:2020/11/30 13:35
 * Copyright (c) 2020, zaodao All Rights Reserved.
 */

import java.util.Random;

/**
 * Created by dave on 2016/10/14.
 * 基于snowflake算法实现
 */
public class IdWorker {

    /**
     * twepoch
     */
    private final long twepoch = 1288834974657L;

    /**
     * workerIdBits
     */
    private final long workerIdBits = 5L;

    /**
     * datacenterIdBits
     */
    private final long datacenterIdBits = 5L;

    /**
     * maxWorkerId
     */
    private final long maxWorkerId = -1L ^ (-1L << workerIdBits);

    /**
     * maxDatacenterId
     */
    private final long maxDatacenterId = -1L ^ (-1L << datacenterIdBits);

    /**
     * sequenceBits
     */
    private final long sequenceBits = 12L;

    /**
     * workerIdShift
     */
    private final long workerIdShift = sequenceBits;

    /**
     * datacenterIdShift
     */
    private final long datacenterIdShift = sequenceBits + workerIdBits;

    /**
     * timestampLeftShift
     */
    private final long timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits;

    /**
     * sequenceMask
     */
    private final long sequenceMask = -1L ^ (-1L << sequenceBits);

    /**
     * sequenceMask
     */
    private long workerId;

    /**
     * datacenterId
     */
    private long datacenterId;

    /**
     * sequence
     */
    private long sequence = 0L;

    /**
     * lastTimestamp
     */
    private long lastTimestamp = -1L;

    private static Random rand1 = new Random();

    private static  Random rand2 = new Random();

    public IdWorker() {
        this(rand1.nextInt(32), rand2.nextInt(30) + 2);
    }

    /**
     * 构造方法
     *
     * @param workerId     long
     * @param datacenterId long
     */
    public IdWorker(long workerId, long datacenterId) {
        if (workerId > maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0",
                    maxWorkerId));
        }
        if (datacenterId > maxDatacenterId || datacenterId < 0) {
            throw new IllegalArgumentException(String.format("datacenter Id can't be greater than %d or less than 0",
                    maxDatacenterId));
        }
        this.workerId = workerId;
        this.datacenterId = datacenterId;
    }

    /**
     * nextId
     * @Description
     * @return long
     * @Date 2017/7/25 15:34
     */
    public synchronized long nextId() {
        long timestamp = timeGen();
        if (timestamp < lastTimestamp) {
            throw new RuntimeException(
                    String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds",
                            lastTimestamp - timestamp));
        }
        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) & sequenceMask;
            if (sequence == 0) {
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            sequence = 0L;
        }

        lastTimestamp = timestamp;

        return ((timestamp - twepoch) << timestampLeftShift) | (datacenterId << datacenterIdShift)
                | (workerId << workerIdShift) | sequence;
    }

    /**
     * tilNextMillis
     * @Description
     * @param lastTimestamp long
     * @return long
     * @Date 2017/7/25 15:35
     */
    protected long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    /**
     * timeGen
     * @Description
     * @return long
     * @Date 2017/7/25 15:38
     */
    protected long timeGen() {
        return System.currentTimeMillis();
    }

}