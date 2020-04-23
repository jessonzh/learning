package com.jessonzh.learning.concurrency;

import com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExecutorDemo {
    public static void main(String[] args) {
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(5,
                12,
                2,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(2),
                new ThreadPoolExecutor.AbortPolicy());
        RateLimiter limiter = RateLimiter.create(10);
        for (int i = 0; i < 100; i++) {
            limiter.acquire();
            poolExecutor.execute(new Thread(() -> {
                // 线程操作
                System.out.println(Thread.currentThread().getName() + "已执行");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, "Thread-" + String.valueOf(i)));
        }
//        try {
//            TimeUnit.SECONDS.sleep(3);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        //终止线程池
//        poolExecutor.shutdown();
    }
}
