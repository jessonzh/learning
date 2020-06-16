package com.jessonzh.learning.jvm;

public class StringConstantPoolTest {

    public static void main(String[] args) {
        String str1 = "123"; // str1为常量池对象引用
        String str2 = new String("123"); // str2为堆内对象引用，同时回去试图去常量池创建字面量的对象，但是已有"123"
        String str3 = str2.intern(); // str3为常量池内"123"对象的引用，也就是str1
        String str4 = str1.intern(); // str4为常量池内"123"对象的引用，也就是str1
        String str5 = "123"; // str5为常量池中"123"对象的引用，也就是str1

        System.out.println( str1 == str2); // false
        System.out.println( str1 == str3); // true
        System.out.println( str1 == str4); // true
        System.out.println( str1 == str5); // true

        System.out.println("========== s1==s3==s4==s5 都是相同的");
        System.out.println();

        // 在JDK6中，字符串常量池里保存的都是String对象

        // 在JDK7/8中，对于字符串字面量（包括常量表达式，常量表达式在编译时就会拼接成一个常量）
        // 先看常量池是否存在相同字符串对象，如果存在则返回池内对象的引用；
        // 再看常量池是否存在相同字符串的堆内的引用，如果就返回堆内对象的引用；
        // 如果都不存在，会创建String对象到字符串常量池，返回对这个对象的引用；

        // 如果使用new String()在堆内创建对象，和常量池没有关系，创建多个对象也不相同；

        // 任何字符串调用intern()方法，先看是否常量池中存在相同的字符串对象，常量池如果存在相同字符串的对象，如果存在就返回池内的对象引用

        // 然后再看是否存在相同字符串的堆内对象的引用，如果存在就返回的是堆内的对象引用
        // 都不存在的话，情况只可能就是运行期产生了一个在堆中的字符串对象，调用intern()方法，此时在常量池中保存对堆内String对象的引用即可，返回的也是堆内引用
        // 任何已创建字面量到常量池中产生对象的，这个时候再调用intern()，返回常量池中相同字符串对象的引用

        // 要特别注意String s1 = new String("abc");这种创建类型，会先字面量"abc"创建常量池的对象，然后又在堆内创建一个"abc"的对象

        // 如果是编译期不能确定的字符串（也就是运行期才能生成的字符串），在堆内创建对象，调用intern()方法会使得常量池中保存对堆内String对象的引用。
        // 而不会在常量池内再生成一个对象，之所以做这种改动，可能是考虑到字符串常量池已经移动到了堆中，因此没有必要在池内和池外各保留一个对象，这样节省空间。

        // 原则：尽量少的在堆内创建对象，其实也是池化技术的本质

        String s1 = new String("abc");  // #1 s1为堆内"abc"对象的引用，同时在常量池也产生了一个"abc"的对象
        String s1_intern = s1.intern();         // #2 s1_intern为常量池中的对象的引用
        String s2 = "abc";                      // #3 s2为常量池中对象的引用
        // #1语句中，创建了多少个对象？这是面试中极常见的问题，答案是2个，堆中及字符串常量池中各一个。由于"a"是字面量，因此它会自动驻留。
        // #2语句调用intern()时，字符串常量池中就已经存在它了。
        // #3语句会直接找到常量池中的"a"，故s1与s2的引用地址是不同的。
        System.out.println(s1 == s2);
        System.out.println(s1_intern == s2);

        String s3 = s2 + s2; // 运行时产生了对象在堆内，s3为堆内对象引用
        String s3_intern = s3.intern(); // 常量池中存放的堆内的引用，返回的为堆内的引用
        String s4 = "abcabc";
        System.out.println(s3 == s4);
        System.out.println(s3_intern == s4);

        System.out.println("=====================================");

        String t1 = new StringBuilder().append("String").append("Test").toString();
        System.out.println(t1.intern() == t1);

        String t2 = new StringBuilder().append("ja").append("va").toString();
        System.out.println(t2.intern() == t2);

        String t4 = new StringBuilder().append("open").append("jdk").toString();
        System.out.println(t4.intern() == t4);

        String test = "";
        test = null;
        String sss = (String) test;
    }
}
