package com.jessonzh.learning.concurrent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PrintByLock {
    public static void main(String[] args) {
        ResourcesByLock resourcesByLock = new ResourcesByLock();
        new Thread(() -> {
            for (int i = 1; i <= 26; i++) {
                resourcesByLock.printOdd();
            }
        }, "t-1").start();
        new Thread(() -> {
            for (int i = 1; i <= 26; i++) {
                resourcesByLock.printEven();
            }
        }, "t-2").start();
        new Thread(() -> {
            for (int i = 1; i <= 26; i++) {
                resourcesByLock.printAlphabet();
            }
        }, "t-3").start();
    }
}

/**
 * 线程操作资源类
 */
class ResourcesByLock {
    private int n = 1;
    private int flag = 1;

    private Lock lock = new ReentrantLock();
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();

    public void printOdd() {
        lock.lock();
        try {
            while (flag != 1) {
                condition1.await();
            }
            System.out.print(n * 2 - 1);
            flag = 2;
            condition2.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void printEven() {
        lock.lock();
        try {
            while (flag != 2) {
                condition2.await();
            }
            System.out.print(n * 2);
            flag = 3;
            condition3.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void printAlphabet() {
        lock.lock();
        try {
            while (flag != 3) {
                condition3.await();
            }
            System.out.print((char) (n + 64));
            flag = 1;
            n++;
            condition1.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}