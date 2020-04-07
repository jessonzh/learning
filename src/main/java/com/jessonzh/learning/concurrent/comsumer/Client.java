package com.jessonzh.learning.concurrent.comsumer;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Client {

    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    private boolean hasValue = false;

    public void provide() {
        lock.lock();
        try {
            while (!hasValue) {
                condition.await();
            }
            System.out.println(Thread.currentThread().getName() + "正在消费---");
            hasValue = false;
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void consume() {
        lock.lock();
        try {
            while (hasValue) {
                condition.await();
            }
            System.out.println(Thread.currentThread().getName() + "正在生产+++");
            hasValue = true;
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
