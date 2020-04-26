package com.jessonzh.learning.algorithm.leetcode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class LeetCode297 {

    /**
     * 思路：所有节点的value都加上"!"结尾，为空则加"#"符号来区分
     */
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

    /**
     * 思路：按照"!"分隔符来切分成数组
     */
    public TreeNode deserialize(String data) {
        List<String> list = new LinkedList<>(Arrays.asList(data.split("!")));
        return rdeserialize(list);
    }

    public TreeNode rdeserialize(List<String> list) {
        if (list.get(0).equals("#")) {
            list.remove(0);
            return null;
        }
        TreeNode root = new TreeNode(Integer.parseInt(list.get(0)));
        list.remove(0);
        root.left = rdeserialize(list);
        root.right = rdeserialize(list);
        return root;
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
