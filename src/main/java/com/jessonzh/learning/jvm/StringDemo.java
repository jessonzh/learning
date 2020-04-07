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


        System.out.println("=====================================");

//      在JDK6中，字符串常量池里保存的都是String对象。

//      在JDK7/8中，对于字符串字面量（当然也包括常量表达式），常量池里会直接保存String对象。
//      如果是编译期不能确定的字符串，调用intern()方法会使得常量池中保存对堆内String对象的引用，
//      而不会在常量池内再生成一个对象。之所以做这种改动，可能是考虑到字符串常量池已经移动到了堆中，
//      因此没有必要在池内和池外各保留一个对象，这样节省空间。

        String s1 = new String("a");   // #1
        String ss1 = s1.intern();      // #2
        String s2 = "a";               // #3
        System.out.println(s1 == s2);
        System.out.println(ss1 == s2);

        String s3 = s2 + s2;           // #4
        String ss3 = s3.intern();      // #5
        String s4 = "aa";              // #6
        System.out.println(s3 == s4);
        System.out.println(ss3 == s4);



        System.out.println("=====================================");

        String t3 = "java";

        String t1 = new StringBuilder().append("String").append("Test").toString();
        System.out.println(t1.intern() == t1);

        String t2 = new StringBuilder().append("ja").append("va").toString();
        System.out.println(t2.intern() == t2);

    }
}
