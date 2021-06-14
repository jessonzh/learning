package com.jessonzh.learning.redis;

import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

/**
 * HyperLogLog测试
 * @author Administrator
 */
@Slf4j(topic = "HyperLogLogTest")
public class HyperLogLogTest {

    public static void main(String[] args) {
        HostAndPort hostAndPort = new HostAndPort("192.168.1.128", 6379);
        JedisCluster jedis = new JedisCluster(hostAndPort);
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
