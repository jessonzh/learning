package com.jessonzh.learning.jvm;

import lombok.extern.slf4j.Slf4j;
import org.openjdk.jol.info.ClassLayout;

import java.io.IOException;

/**
 * -XX:-UseCompressedOops 关闭指针压缩
 * -XX:BiasedLockingStartupDelay=0 偏向锁是默认是延迟的，不会在程序启动时立即生效，如果想避免延迟，可以加VM参数
 * -XX:-UseBiasedLocking 禁用偏向锁
 */
@Slf4j(topic = "BiasedLockTest")
public class BiasedLockTest {
    public static void main(String[] args) throws IOException {
        Dog d = new Dog();
        ClassLayout classLayout = ClassLayout.parseInstance(d);
        new Thread(() -> {
            log.info("synchronized 前");
            System.out.println(classLayout.toPrintable());
            synchronized (d) {
                log.info("synchronized 中");
                System.out.println(classLayout.toPrintable());
            }
            log.info("synchronized 后");
            System.out.println(classLayout.toPrintable());
        }, "t1").start();
    }
}

class Dog {
}
