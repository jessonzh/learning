package com.jessonzh.learning.concurrent;

public class PrintBySynchronized {

    public static void main(String[] args) {
        ResourcesBySynchronized resourcesBySynchronized = new ResourcesBySynchronized();
        new Thread(() -> {
            for (int i = 1; i <= 26; i++) {
                try {
                    resourcesBySynchronized.printNumber();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "A").start();

        new Thread(() -> {
            for (int i = 1; i <= 26; i++) {
                try {
                    resourcesBySynchronized.printAlphabet();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "B").start();
    }

}

class ResourcesBySynchronized {

    private int index = 1;

    private int number = 1;

    public synchronized void printNumber() throws InterruptedException {
        if (index % 3 == 0) {
            this.wait();//为什么一定要放在synchronized代码块中？因为wait需要释放锁，synchronized在这之前要获取到锁
        }
        System.out.print(number * 2 - 1);
        System.out.print(number * 2);
        index = index + 2;
        this.notifyAll();
    }

    public synchronized void printAlphabet() throws InterruptedException {
        if (index % 3 != 0) {
            this.wait();
        }
        System.out.print((char) (number + 64));
        number++;
        index++;
        this.notifyAll();
    }
}