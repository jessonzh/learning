package com.jessonzh.learning.algorithm.leetcode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        return rserialize(root, "");
    }

    public String rserialize(TreeNode root, String str) {
        if (root != null) {
            str = str + root.val + "!";
            str = rserialize(root.left, str);
            str = rserialize(root.right, str);
        } else {
            str = str + "#!";
        }
        return str;
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] array = data.split("!");
        List<String> list = new LinkedList<>(Arrays.asList(array));
        return rdeserialize(list);
    }

    public TreeNode rdeserialize(List<String> list) {
        if (list.get(0).equals("#")) {
            return null;
        }
        TreeNode root = new TreeNode(Integer.parseInt(list.get(0)));

    }
}
