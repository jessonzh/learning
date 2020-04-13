package com.jessonzh.learning.algorithm.leetcode;

public class LeetCode509 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.fib(5));
    }

    private static class Solution {
        public int fib(int n) {
            if (n == 0) {
                return 0;
            }
            if (n == 1) {
                return 1;
            }
            int a = 0, b = 1;
            int c;
            for (int i = 2; i <= n; i++) {
                c = (a + b) % 1000000007;
                a = b;
                b = c;
            }
            return b;
        }
    }
}