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

//      在JDK6中，字符串常量池里保存的都是String对象

//      在JDK7/8中，对于字符串字面量（包括常量表达式，常量表达式在编译时就会拼接成一个常量）
//      如果常量池不存在这个常量，会创建并保存String对象到常量池，返回这个对象的引用
//      如果存在可能有两种情况，第一是常量池本身有一个相同的对象，则返回该对象的引用；第二是常量池存在相同字符串的堆中的引用，那么就返回这个堆中的引用

//      如果是new String()直接在堆中创建一个对象，和常量池没有关系，new多个对象也不相同，调用intern()方法会使得常量池中保存对堆内String对象的引用
//      如果是编译期不能确定的字符串，也是在堆中创建一个对象，调用intern()方法会使得常量池中保存对堆内String对象的引用
//      而不会在常量池内再生成一个对象。之所以做这种改动，可能是考虑到字符串常量池已经移动到了堆中，因此没有必要在池内和池外各保留一个对象，这样节省空间。

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

        String t4 = new StringBuilder().append("open").append("jdk").toString();
        System.out.println(t4.intern() == t4);

        System.out.println("======================================");

        String string1 = "hello";
        String string2 = "he" + "llo";
        String string3 = new String("hello");
        String string4 = new String("he") + new String("llo");
    }
}
