package com.jessonzh.learning.jvm;

import lombok.extern.slf4j.Slf4j;
import org.openjdk.jol.info.ClassLayout;

import java.io.IOException;

@Slf4j(topic = "BiasedLockTest")
public class BiasedLockTest {
    public static void main(String[] args) throws IOException {
        Dog d = new Dog();
        ClassLayout classLayout = ClassLayout.parseInstance(d);
        new Thread(() -> {
            System.out.println("synchronized 前");
            System.out.println(classLayout.toPrintable());
            synchronized (d) {
                System.out.println("synchronized 中");
                System.out.println(classLayout.toPrintable());
            }
            System.out.println("synchronized 后");
            System.out.println(classLayout.toPrintable());
        }, "t1").start();
    }
}

class Dog {
}
