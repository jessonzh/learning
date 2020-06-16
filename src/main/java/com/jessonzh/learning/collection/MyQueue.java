package com.jessonzh.learning.collection;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class MyQueue<T> implements Queue<T> {

    private final Integer cap;

    private final AtomicInteger size = new AtomicInteger(0);

    private Node<T> head;
    private Node<T> tail;

    private final ReentrantLock putLock = new ReentrantLock();
    private final ReentrantLock takeLock = new ReentrantLock();

    public MyQueue() {
        this.cap = Integer.MAX_VALUE;
        head = tail = new Node<>(null);
    }

    public MyQueue(Integer cap) {
        if (null == cap || cap < 0) {
            throw new IllegalArgumentException();
        }
        this.cap = cap;
        head = tail = new Node<>(null);
    }

    @Override
    public boolean put(T element) {
        if (element == null) {
            return false;
        }
        try {
            boolean flag = putLock.tryLock(500, TimeUnit.MICROSECONDS);
            if (!flag) {
                System.out.println(Thread.currentThread().getName() + "获取putLock超时");
                return false;
            }
            if (size.get() >= cap) {
                // queue is full
                return false;
            }
            Node<T> node = new Node<>(element);
            tail.next = node;
            tail = node;
            size.incrementAndGet();
            return true;
        } catch (Exception e1) {
            e1.printStackTrace();
            return false;
        } finally {
            putLock.unlock();
        }
    }

    @Override
    public T take() {
        if (size.get() <= 0) {
            return null;
        }
        try {
            boolean flag = takeLock.tryLock(500, TimeUnit.MICROSECONDS);
            if (!flag) {
                System.out.println(Thread.currentThread().getName() + "获取takeLock超时");
                return null;
            }
            Node<T> node = head.next;
            T value = node.element;
            head = node;
            size.decrementAndGet();
            return value;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            takeLock.unlock();
        }

    }

    public static void main(String[] args) {
        Queue<Integer> myQueue = new MyQueue<>(3);
        for (int i = 0; i < 20; i++) {
            int finalI = i;
            new Thread(() -> {
                // 线程操作
                boolean flag = myQueue.put(finalI);
                System.out.println(Thread.currentThread().getName() + " put --> " + finalI + (flag ? " 成功" : " 失败"));
            }, "Thread-" + String.valueOf(i)).start();
        }

        for (int i = 100; i < 120; i++) {
            new Thread(() -> {
                // 线程操作
                Integer integer = myQueue.take();
                System.out.println(Thread.currentThread().getName() + " take -> " + integer);
            }, "Thread-" + String.valueOf(i)).start();
        }


    }
}

class Node<T> {
    T element;
    Node<T> next;

    public Node(T element) {
        this.element = element;
    }
}