package com.example.demo.StringTest;

import java.lang.reflect.Field;

/**
 * @description:
 * @author: chenr
 * @date: 2017/8/31
 */
public class StringStorageTest {

    /**
     * 字符串常量池与堆空间
     */
    private static void poolAndHeapStore() {
        String s1 = "Hello";    // 字面量直接放入string pool
        String s2 = new String("Hello");    // string pool已存在“Hello”， 只进行new对象
        System.out.println("s1 storeAdr: " + Integer.toHexString(System.identityHashCode(s1)));
        System.out.println("s2 storeAdr: " + Integer.toHexString(System.identityHashCode(s2)));
        System.out.println(s1 == s2);

        String s2_stringPool = s2.intern();
        System.out.println(s1 == s2);
        System.out.println(s1 == s2_stringPool);
        System.out.println(s2 == s2_stringPool);
    }

    /**
     *
     */
    private static void poolAndHeapStore2() {
        // 等价于 String Hello = "Hello"; String world = "World"; String s1 = Hello + world;
        // 会被编译成 s1 = new StringBuilder().append(new String("Hello")).append(new String()).toString();
        String s1 = new String("Hello") + new String("World");
        s1.intern();        //jdk7 string pool中存储s1的引用
        String s2 = "HelloWorld";
        System.out.println(s1 == s2);
    }

    private static void poolAndHeapStore3() {
        //todo 编译器自动优化，“Hello”和“World”不存入string pool，直接置入“HelloWorld”
        String s1 = "Hello" + "World";
        String s2 = "HelloWorld";

        // 验证"Hello"并未置入string pool
        String s3 = new String("Hel") + new String("lo");
        s3.intern();
        String s4 = "Hello";
        System.out.println(s1 == s2);
        System.out.println(s3 == s4);
    }

    private static void poolAndHeapStore4() {
        String s1 = new String("Hello");
        String s2 = new String("Hello");
        System.out.println(s1 == s2);
        System.out.println(s1.intern() == s2.intern());
    }

    private static void poolAndHeapStore5() {
        String s1 = "Hello".concat("World");
        s1.intern();
        String s2 = "HelloWorld";
        System.out.println(s1 == s2);
    }

    private static void poolAndHeapStore6() {
        // 常量字符串在编译期才放入string pool， s2通过s1而得到，属于运行时赋值，存于堆中;
        String s1 = "Hello";
        String s2 = s1 + "";
        String s3 = "Hello" + "";
        System.out.println(s1 == s2);
        System.out.println(s1 == s3);
    }

    private static void poolAndHeapStore7() {
        String s1 = "Hello";
        String s2 = "lo";
        System.out.println(OtherString.hello == s1);    // true
        System.out.println(OtherString.hello == "Hello");   // true
        System.out.println("Hel" + "lo" == s1);     // true
        System.out.println("Hel" + s2 == s1);       // false
    }

    /**
     * 通过动态反射改变String的value值
     *
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    private static void modifyStringValue() throws NoSuchFieldException, IllegalAccessException {
        String oriStr = new String("Hello");
        String compareStr = oriStr;
        System.out.println("oriStr storeAddr: " + Integer.toHexString(System.identityHashCode(oriStr)));
        System.out.println(oriStr);

        Field field = String.class.getDeclaredField("value");
        field.setAccessible(true);
        field.set(oriStr, "world".toCharArray());
        System.out.println("newStr storeAddr: " + Integer.toHexString(System.identityHashCode(oriStr)));
        System.out.println(oriStr);

        System.out.println(compareStr);
    }

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        // poolAndHeapStore();
        // poolAndHeapStore2();
        // poolAndHeapStore3();
        // poolAndHeapStore4();
        // poolAndHeapStore5();
        // poolAndHeapStore6();
        poolAndHeapStore7();
        // modifyStringValue();
    }
}

class OtherString {
    static String hello = "Hello";
}