package com.jessonzh.learning.algorithm.leetcode;

import java.util.*;

class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        // map用于存储频次
        HashMap<Integer, Integer> count = new HashMap();
        for (int n : nums) {
            count.put(n, count.getOrDefault(n, 0) + 1);
        }

        // 构建一个小顶堆，按照频次排序
        PriorityQueue<Integer> heap = new PriorityQueue<Integer>((k1, k2) -> count.get(k1) - count.get(k2));

        // 保持容量为k的小顶堆，最后堆中里面剩下就是最多的k个
        for (int n: count.keySet()) {
            heap.add(n);
            if (heap.size() > k)
                heap.poll();
        }

        // 从小顶堆中移出元素到list，顺序是按频次从少到多，最后需要reverse一下
        List<Integer> top_k = new ArrayList<>();
        while (!heap.isEmpty())
            top_k.add(heap.poll());
        Collections.reverse(top_k);
        return top_k.stream().mapToInt(i->i).toArray();
    }
}
