package com.jessonzh.learning.concurrent;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

public class CyclicBarrierDemo {

    private static final int THREAD_COUNT = 3;

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(THREAD_COUNT, new Thread(() -> {
            System.out.println("---所有的线程到达屏障点");
        }));

        for (int i = 0; i < THREAD_COUNT; i++) {
            new Thread(() -> {
                try {
                    System.out.println(Thread.currentThread().getName() + "线程从起点出发");
                    TimeUnit.SECONDS.sleep(2);
                    System.out.println(Thread.currentThread().getName() + "线程即将到达屏障1，当前已有" + cyclicBarrier.getNumberWaiting() + "到达");
                    cyclicBarrier.await();    // 到此如果没有达到公共屏障点，则该线程处于等待状态
                                              // 如果达到公共屏障点则所有处于等待的线程都继续往下运行

                    System.out.println(Thread.currentThread().getName() + "线程从屏障1出发");
                    TimeUnit.SECONDS.sleep(2);
                    System.out.println(Thread.currentThread().getName() + "线程即将到达屏障2，当前已有" + cyclicBarrier.getNumberWaiting() + "到达");
                    cyclicBarrier.await();    // 到此如果没有达到公共屏障点，则该线程处于等待状态
                                              // 如果达到公共屏障点则所有处于等待的线程都继续往下运行
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();
        }
    }
}
