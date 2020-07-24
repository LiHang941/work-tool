package com.github.lihang941.tool.redis.lock;

import org.redisson.api.RLock;

import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

/**
 * 类描述
 *
 * @author tangyu
 * @date 2020/4/9 15:22
 */
public class RedissonLockUtil {

    private DistributedLocker redissLock;


    public void setLocker(DistributedLocker locker) {
        redissLock = locker;
    }

    /**
     * 加锁
     *
     * @param lockKey
     * @return
     */
    public RLock lock(String lockKey) {
        return redissLock.lock(lockKey);
    }

    /**
     * 释放锁
     *
     * @param lockKey
     */
    public void unlock(String lockKey) {
        redissLock.unlock(lockKey);
    }

    /**
     * 释放锁
     *
     * @param lock
     */
    public void unlock(RLock lock) {
        redissLock.unlock(lock);
    }

    /**
     * 带超时的锁
     *
     * @param lockKey
     * @param timeout 超时时间   单位：秒
     */
    public RLock lock(String lockKey, int timeout) {
        return redissLock.lock(lockKey, timeout);
    }

    /**
     * 带超时的锁
     *
     * @param lockKey
     * @param unit    时间单位
     * @param timeout 超时时间
     */
    public RLock lock(String lockKey, TimeUnit unit, int timeout) {
        return redissLock.lock(lockKey, unit, timeout);
    }

    /**
     * 尝试获取锁
     *
     * @param lockKey
     * @param waitTime  最多等待时间
     * @param leaseTime 上锁后自动释放锁时间
     * @return
     */
    public boolean tryLock(String lockKey, int waitTime, int leaseTime) {
        return redissLock.tryLock(lockKey, TimeUnit.SECONDS, waitTime, leaseTime);
    }

    /**
     * 尝试获取锁
     *
     * @param lockKey   锁定key
     * @param unit      时间单位
     * @param waitTime  最多等待时间
     * @param leaseTime 上锁后自动释放锁时间
     * @return 是否获取锁成功
     */
    public boolean tryLock(String lockKey, TimeUnit unit, int waitTime, int leaseTime) {
        return redissLock.tryLock(lockKey, unit, waitTime, leaseTime);
    }

    public <T> T lockCall(String key, TimeUnit unit, int waitTime, int leaseTime, Supplier<T> supplier) {
        try {
            boolean res = tryLock(key, unit, waitTime, leaseTime);
            if (res) {    //成功
                return supplier.get();
            } else {
                throw new InterruptedException("获取锁失败");
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            unlock(key);
        }
    }

    public <T> T lockCall(String key, Supplier<T> supplier) {
        return lockCall(key, TimeUnit.SECONDS, 1, 3, supplier);
    }

    public void lockRun(String key, Runnable runnable) {
        lockCall(key, () -> {
            runnable.run();
            return null;
        });
    }

}
