package com.jessonzh.learning.redis;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import redis.clients.jedis.Jedis;

import java.lang.reflect.Type;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class RedisDelayingQueue<T> {

    static class TaskItem<T> {
        public String id;
        public T msg;
    }

    private Type taskType = new TypeReference<TaskItem<T>>() {}.getType();

    private Jedis jedis;
    private String queueKey;

    public RedisDelayingQueue(Jedis jedis, String queueKey) {
        this.jedis = jedis;
        this.queueKey = queueKey;
    }

    public void delay(T msg) {
        TaskItem<T> taskItem = new TaskItem<>();
        taskItem.id = UUID.randomUUID().toString();
        taskItem.msg = msg;
        String string = JSON.toJSONString(taskItem);
        jedis.zadd(queueKey, System.currentTimeMillis() + 5000, string);
    }

    public T loop() {
        while (!Thread.interrupted()) {
            Set<String> stringSet = jedis.zrangeByScore(queueKey, 0, System.currentTimeMillis(), 0, 1);
            if (stringSet.isEmpty()) {
                try {
                    TimeUnit.MICROSECONDS.sleep(500);
                } catch (InterruptedException e) {
                    break;
                }
                continue;
            }
            String string = stringSet.iterator().next();
            if (jedis.zrem(queueKey, string) > 0) {
                TaskItem<T> taskItem = JSON.parseObject(string, taskType);
                return taskItem.msg;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        Jedis jedis = new Jedis("192.168.56.101", 6379);
        RedisDelayingQueue<String> delayingQueue = new RedisDelayingQueue<>(jedis, "redis-queue");
        Thread provider = new Thread(() -> {
            // 线程操作
            delayingQueue.delay("number");
        }, "Provider");
        provider.start();

        Thread consumer = new Thread(() -> {
            // 线程操作
            System.out.println("consume -> " + delayingQueue.loop());
        }, "Consumer");
        consumer.start();
    }
}
