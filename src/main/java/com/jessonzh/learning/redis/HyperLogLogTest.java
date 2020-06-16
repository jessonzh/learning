package com.jessonzh.learning.redis;

import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;

import java.util.Objects;

/**
 * HyperLogLog测试
 * @author Administrator
 */
@Slf4j(topic = "HyperLogLogTest")
public class HyperLogLogTest {

    public static void main(String[] args) {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        log.info("start pfadd");
        for (int i = 0; i < 100000; i++) {
            String str = "user" + i;
            jedis.pfadd("hlltest.com", str);
            System.out.println(str);
        }
        long count = jedis.pfcount("hlltest.com");
        jedis.close();
        log.info(String.valueOf(count));
    }
}
