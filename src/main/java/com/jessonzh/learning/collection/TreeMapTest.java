package com.jessonzh.learning.collection;

import java.util.Map;
import java.util.TreeMap;

public class TreeMapTest {
    public static void main(String[] args) {
        Map<String, String> map = new TreeMap<>((str1, str2) -> str1.length() - str2.length());
        map.put("123", "123");
        map.put("1", "1");
    }
}
