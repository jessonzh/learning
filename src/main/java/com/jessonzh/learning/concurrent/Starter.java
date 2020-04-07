package com.jessonzh.learning.concurrent;

public class Starter {

    private static final int THREAD_AMOUNT = 10;

    public static void main(String[] args) {
        for (int i = 0; i < THREAD_AMOUNT; i++) {
            final int j = i;
            new Thread(() -> {
                // 线程操作
                System.out.println(Thread.currentThread().getName() + "输出了 : " + j);
            }, "Thread-" + String.valueOf(i)).start();
        }

    }
}
