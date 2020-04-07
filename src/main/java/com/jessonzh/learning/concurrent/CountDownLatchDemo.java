package com.jessonzh.learning.concurrent;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {

    private static final int THREAD_COUNT = 6;

    public static void main(String[] args) {

        CountDownLatch countDownLatch = new CountDownLatch(THREAD_COUNT);
        for (int i = 0; i < THREAD_COUNT; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "线程结束");
                countDownLatch.countDown();
            }, String.valueOf(i)).start();
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "线程结束");
    }
}
