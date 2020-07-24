package com.github.lihang941.tool.common.utils;


/**
 * Snow Flake ID Service
 */
public class SnowFlakeIdWorker {
    /**
     * 起始的时间戳
     */
    private static final long START_TS = 1480166465631L;

    /**
     * 序列号占用的位数
     */
    private static final long SEQUENCE_BIT = 12;
    /**
     * 机器标识占用的位数
     */
    private static final long MACHINE_BIT = 5;
    /**
     * 数据中心占用的位数
     */
    private static final long DATA_CENTER_BIT = 5;

    /**
     * 每一部分的最大值
     */
    private static final long MAX_DATA_CENTER_NUM = ~(-1L << DATA_CENTER_BIT);
    private static final long MAX_MACHINE_NUM = ~(-1L << MACHINE_BIT);
    private static final long MAX_SEQUENCE = ~(-1L << SEQUENCE_BIT);

    /**
     * 每一部分向左的位移
     */
    private static final long MACHINE_LEFT = SEQUENCE_BIT;
    private static final long DATA_CENTER_LEFT = SEQUENCE_BIT + MACHINE_BIT;
    private static final long TIMESTAMP_LEFT = DATA_CENTER_LEFT + DATA_CENTER_BIT;

    /**
     * 数据中心
     */
    private final long dataCenterId;
    /**
     * 机器标识
     */
    private final long machineId;
    /**
     * 序列号
     */
    private long sequence = 0L;
    /**
     * 上一次时间戳
     */
    private long lastTs = -1L;

    public SnowFlakeIdWorker(long dataCenterId, long machineId) {
        if (dataCenterId > MAX_DATA_CENTER_NUM || dataCenterId < 0) {
            throw new IllegalArgumentException("data center Id can't be greater than MAX_DATA_CENTER_NUM or less than 0");
        }
        if (machineId > MAX_MACHINE_NUM || machineId < 0) {
            throw new IllegalArgumentException("machineId can't be greater than MAX_MACHINE_NUM or less than 0");
        }
        this.dataCenterId = dataCenterId;
        this.machineId = machineId;
    }

    public String nextIdWithPrefix(String prefix) {
        return prefix + this.nextId();
    }

    public synchronized Long nextId() {
        long currentTimestamp = getCurrentTimestamp();
        if (currentTimestamp < lastTs) {
            throw new RuntimeException("Clock moved backwards.  Refusing to generate id");
        }

        if (currentTimestamp == lastTs) {
            //相同毫秒内，序列号自增
            sequence = (sequence + 1) & MAX_SEQUENCE;
            //同一毫秒的序列数已经达到最大
            if (sequence == 0L) {
                currentTimestamp = getNextMill();
            }
        } else {
            //不同毫秒内，序列号置为0
            sequence = 0L;
        }

        lastTs = currentTimestamp;

        return (currentTimestamp - START_TS) << TIMESTAMP_LEFT | dataCenterId << DATA_CENTER_LEFT | machineId << MACHINE_LEFT | sequence;
    }

    private long getNextMill() {
        long mill = getCurrentTimestamp();
        while (mill <= lastTs) {
            mill = getCurrentTimestamp();
        }
        return mill;
    }

    private long getCurrentTimestamp() {
        return System.currentTimeMillis();
    }
}
