package com.jessonzh.learning.leetcode;

import java.util.Stack;

public class LeetCodeTest {
    public static void main(String[] args) {
        CQueue obj = new CQueue();
        obj.appendTail(1);
        int param_2 = obj.deleteHead();
        System.out.println(param_2);
    }

    /**
     * Your CQueue object will be instantiated and called as such:
     * CQueue obj = new CQueue();
     * obj.appendTail(value);
     * int param_2 = obj.deleteHead();
     */
    private static class CQueue {

        private Stack<Integer> stack1;
        private Stack<Integer> stack2;

        public CQueue() {
            stack1 = new Stack<>();
            stack2 = new Stack<>();
        }

        public void appendTail(int value) {
            stack2.push(value);
        }

        public int deleteHead() {
            if (stack1.isEmpty()) {
                while (!stack2.isEmpty()) {
                    int temp = stack2.pop();
                    stack1.push(temp);
                }
            }
            if (stack1.isEmpty()) {
                return -1;
            } else {
                return stack1.pop();
            }
        }
    }
}


