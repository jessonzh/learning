package com.jessonzh.learning.designpattern;

public class SingletonTest {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                // 线程操作
                Singleton singleton = Singleton.getInstance();
                System.out.println(Thread.currentThread().getName() + "线程获取单例成功" + singleton.toString());
            }, "Thread-" + String.valueOf(i)).start();
        }
    }
}

class Singleton {
    private static volatile Singleton instance;

    private Singleton() {
    }

    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}
