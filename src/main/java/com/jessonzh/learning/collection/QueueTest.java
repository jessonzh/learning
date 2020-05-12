package com.jessonzh.learning.collection;

import java.util.ArrayDeque;
import java.util.Queue;

public class QueueTest {
    public static void main(String[] args) {
        Queue<Integer> queue = new ArrayDeque<>(3);

        queue.add(1);
        queue.add(2);
        queue.add(3);
        // 都是无界队列
        queue.add(4);
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.element());
    }
}
