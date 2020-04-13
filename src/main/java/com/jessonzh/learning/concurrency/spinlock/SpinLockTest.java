package com.jessonzh.learning.concurrency.spinlock;

public class SpinLockTest {

    public static void main(String[] args) {
        SpinLock spinLock = new SpinLock();

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                // 线程操作
                spinLock.lock();
                System.out.println(Thread.currentThread().getName() + "获取到锁");
                spinLock.unlock();
            }, "Thread-" + String.valueOf(i)).start();
        }
    }
}
