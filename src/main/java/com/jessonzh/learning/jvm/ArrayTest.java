package com.jessonzh.learning.jvm;

public class ArrayTest {

    public static void main(String[] args) {
        int[][] array = new int[][]{{1, 2},{3, 4}};
        methodA(array);
        int i = 0;
        String str = "123";
        System.out.println("");
    }

    private static void methodA(int[][] array) {
        array[0][0] = 0;
    }
}
