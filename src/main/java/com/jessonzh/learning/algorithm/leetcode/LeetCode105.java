package com.jessonzh.learning.algorithm.leetcode;

import java.util.HashMap;
import java.util.Map;

public class LeetCode105 {

    public static void main(String[] args) {

    }


    private static class TreeNode {
          int val;
          TreeNode left;
          TreeNode right;
          TreeNode(int x) { val = x; }
      }

    private static class Solution {
        public TreeNode buildTree(int[] preorder, int[] inorder) {
            Map<Integer, Integer> inorderMap = new HashMap<>();
            for (int i = 0; i < inorder.length; i++) {
                inorderMap.put(i + 1, inorder[i]);
            }
            return null;
        }
    }
}
