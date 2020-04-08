package com.jessonzh.learning.jvm;

import java.util.WeakHashMap;

public class WeakHashMapTest {
    public static void main(String[] args) {
        WeakHashMap<Integer, String> map = new WeakHashMap<>();
        Integer key = 100;
        map.put(key, "Weak");

        System.out.println(map);

        key = null;
        System.gc();

        System.out.println(map);
    }
}
