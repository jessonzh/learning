package com.jessonzh.learning.collection;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;

public class ArrayListTest {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        String[] array = new String[]{"123", "123", "123"};
        List<String> stringArrayList = Lists.newArrayList(array);
        list.add(null);
        list.add(null);
        list.add(2,200);
        list.set(2,300);
    }
}