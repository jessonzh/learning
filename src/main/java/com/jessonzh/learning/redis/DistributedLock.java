package com.jessonzh.learning.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.params.SetParams;

import java.util.Collections;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class DistributedLock {

    private static final JedisPool pool;

    static {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxIdle(8);
        config.setMaxTotal(18);
        pool = new JedisPool(config, "192.168.56.104", 6379, 2000);
    }

    /**
     * 占用锁
     *
     * @param lockKey 锁key
     * @return 锁id
     */
    public String lock(String lockKey) {
        //获得jedis实例
        Jedis jedis = pool.getResource();
        //锁id（必须拥有此id才能释放锁）
        String lockId = UUID.randomUUID().toString();
        //占用锁同时设置失效时间
        String isSuccees = jedis.set(lockKey, lockId, new SetParams().nx().ex(10));
        //占用锁成功返回锁id，否则返回null
        if ("OK".equals(isSuccees)) {
            return lockId;
        } else {
            return null;
        }
    }

    /**
     * 释放锁
     *
     * @param lockKey 锁key
     * @param lockId  加锁id
     */
    public void unlock(String lockKey, String lockId) {
        if (lockId != null) {
            //获得jedis实例
            Jedis jedis = pool.getResource();
            //执行Lua代码删除lockId匹配的锁
            String script = "if redis.call('get', KEYS[1]) == ARGV[1] " +
                            "then return redis.call('del', KEYS[1]) else return 0 end";
            jedis.eval(script, Collections.singletonList(lockKey),
                    Collections.singletonList(lockId));
        }
    }
}

class TestDistributedLock {
    public static void main(String[] args) {
        DistributedLock lock = new DistributedLock();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                // 线程操作
                String lockId = lock.lock("ddd");
                try {
                    System.out.println(Thread.currentThread().getName());
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock("ddd", lockId);
                }
            }, "Thread-" + String.valueOf(i)).start();
        }
    }
}
