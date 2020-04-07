package com.jessonzh.learning.jvm;

public class StringDemo {

    public static void main(String[] args) {
        String str2 = "123";
        String str3 = new String("123");
        String str4 = str3.intern(); // 返回"123"在字符串常量池中的存的对象引用
        String str1 = "123"; // 返回

        String str5 = new String("123");
        String str6 = new String("123");
        System.out.println( str1 == str4);
    }
}
