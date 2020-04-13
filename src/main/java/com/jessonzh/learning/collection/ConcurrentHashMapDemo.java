package com.jessonzh.learning.collection;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapDemo {

    public static void main(String[] args) {
        Map<Integer, Integer> map = new ConcurrentHashMap<>();
        for (int i = 0; i < 100; i++) {
            final int j = i;
            new Thread(() -> {
                // 线程操作
                map.put(j, j + 1);
            }, "Thread-" + String.valueOf(i)).start();
        }
        System.out.println(map);
    }
}

