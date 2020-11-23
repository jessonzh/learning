package com.jessonzh.learning.concurrency.spinlock;

import org.springframework.lang.Nullable;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 自定义的自旋锁
 * 利用AtomicReference实现
 */
public class SpinLock {
    AtomicReference<Thread> lock = new AtomicReference<>();

    public void lock() {
        Thread thread = Thread.currentThread();
        while (!lock.compareAndSet(null, thread)) {
            System.out.println(Thread.currentThread().getName() + "自旋set失败，继续自旋...");
        }
    }

    public void unlock() {
        Thread thread = Thread.currentThread();
        lock.compareAndSet(thread, null);
        System.out.println(Thread.currentThread().getName() + "释放了锁");
    }
}
