package com.jessonzh.learning.concurrent;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExecutorDemo {
    public static void main(String[] args) {
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(5,
                12,
                2,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(7),
                new ThreadPoolExecutor.AbortPolicy());
        for (int i = 0; i < 30; i++) {
            poolExecutor.execute(new Thread(() -> {
                // 线程操作
                System.out.println(Thread.currentThread().getName());
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, "Thread-" + String.valueOf(i)));
        }
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //终止线程池
        poolExecutor.shutdown();
    }
}
