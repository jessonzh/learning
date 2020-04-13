package com.jessonzh.learning.concurrency.comsumer;

public class Starter {

    public static void main(String[] args) {
        Client client = new Client();

        new Thread(() -> {
            for (int i = 0; i < Integer.MAX_VALUE; i++) {
                client.consume();
            }
        }, "ProviderA").start();

        new Thread(() -> {
            for (int i = 0; i < Integer.MAX_VALUE; i++) {
                client.provide();
            }
        }, "ConsumerC").start();

        new Thread(() -> {
            for (int i = 0; i < Integer.MAX_VALUE; i++) {
                client.consume();
            }
        }, "ProviderB").start();

        new Thread(() -> {
            for (int i = 0; i < Integer.MAX_VALUE; i++) {
                client.provide();
            }
        }, "ConsumerD").start();
    }
}
