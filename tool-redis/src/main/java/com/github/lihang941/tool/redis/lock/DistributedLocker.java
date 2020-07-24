package com.github.lihang941.tool.redis.lock;

import org.redisson.api.RLock;

import java.util.concurrent.TimeUnit;

/**
 * 类描述
 *
 * @author tangyu
 * @date 2020/4/9 15:10
 */
public interface DistributedLocker {

    RLock lock(String lockKey);

    RLock lock(String lockKey, int timeout);

    RLock lock(String lockKey, TimeUnit unit, int timeout);

    boolean tryLock(String lockKey, TimeUnit unit, int waitTime, int leaseTime);

    void unlock(String lockKey);

    void unlock(RLock lock);
}
