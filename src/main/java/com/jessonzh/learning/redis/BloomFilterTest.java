package com.jessonzh.learning.redis;

import org.redisson.Redisson;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

public class BloomFilterTest {

    public static void main(String[] args) {
        Config config = new Config();
        config.useSingleServer()
                .setTimeout(1000000)
                .setAddress("redis://192.168.56.101:6379");
        RedissonClient redissonClient = Redisson.create(config);
        RBloomFilter<String> bloomFilter = redissonClient.getBloomFilter("user_tbl");
        bloomFilter.tryInit(100000L, 0.01d);
        for (int i = 0; i < 100000; i++) {
            bloomFilter.add("user" + i);
            if (bloomFilter.contains("user" + (i + 1))) {
                System.out.println(i);
            }
        }
        bloomFilter.delete();
        redissonClient.shutdown();
    }
}
