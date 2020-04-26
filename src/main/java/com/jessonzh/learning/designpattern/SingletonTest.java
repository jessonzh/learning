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
