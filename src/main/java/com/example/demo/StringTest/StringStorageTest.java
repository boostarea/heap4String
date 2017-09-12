package com.example.demo.StringTest;

import org.junit.Test;

import java.lang.reflect.Field;

/**
 * @description:
 * @author: chenr
 * @date: 2017/8/31
 */
public class StringStorageTest {

    /**
     * 字符串常量池与堆空间
     * output:
     *      s1 storeAdr: 73c6c3b2
     *      s2 storeAdr: 48533e64
     *      s1_stringPool store address: 48533e64
     */
    @Test
    public void poolAndHeapStore() {
        String s1 = new String("Hello");    // string pool未存在“Hello”，先将“Hello”置入String pool，再进行对象创建
        String s2 = "Hello";    // string pool已存在“Hello”，直接引用
        String s1_stringPool = s1.intern();
        System.out.println("s1 storeAdr: " + Integer.toHexString(System.identityHashCode(s1)));
        System.out.println("s2 storeAdr: " + Integer.toHexString(System.identityHashCode(s2)));
        System.out.println("s1_stringPool store address: " + Integer.toHexString(System.identityHashCode(s1_stringPool)));
    }

    /**
     * String.intern()的地址映射
     * output:
     *      false
     *      true
     */
    @Test
    public void basicStringPoolStore() {
        String s1 = new String("Hello");
        String s2 = "Hello";
        System.out.println(s1 == s2);
        System.out.println(s1.intern() == s2);
    }

    /**
     * 字符串常量池在JDK6/7中的区别
     * output:
     *      JDK6:   false;
     *      JDK7:   true;
     */
    public void stringPoolDiff() {
        // 等价于 String Hello = "Hello"; String world = "World"; String s1 = Hello + world;
        // 会被编译成 s1 = new StringBuilder().append(new String("Hello")).append(new String()).toString();
        String s1 = new String("Hello") + new String("World");
        s1.intern();        //jdk7 string pool中存储s1的引用
        String s2 = "HelloWorld";
        System.out.println(s1 == s2);
    }

    /**
     * 编译器自动优化字符字面量
     * output:
     *      true
     *      true
     *      false
     */
    @Test
   public void compileAutoOptimize() {
        // 编译器自动优化，“Hello”和“World”不存入string pool，直接置入“HelloWorld”
        String s1 = "Hello" + "World";
        String s2 = "HelloWorld";

        // 验证"Hello"并未置入string pool
        String s3 = new String("Hel") + new String("lo");
        s3.intern();
        String s4 = "Hello";
        // 只有字符常量拼接，编译器才进行优化
        String s5 = "Hello".concat("World");
        System.out.println(s1 == s2);
        System.out.println(s3 == s4);
        System.out.println(s2 == s5);
    }

    private static void poolAndHeapStore4() {
        String s1 = new String("Hello");
        String s2 = new String("Hello");
        System.out.println(s1 == s2);
        System.out.println(s1.intern() == s2.intern());
    }

    /**
     *
     */
    @Test
    public void poolAndHeapStore5() {
        String s1 = "Hello".concat("World");
        String s2 = "HelloWorld";
        System.out.println(s1 == s2);
    }

    /**
     * 常量字符串编译期才放入string pool
     * output:
     *      false
     *      true
     */
    @Test
    public void poolAndHeapStore6() {
        // 常量字符串在编译期才放入string pool，s2通过s1而得到，属于运行时赋值，存于堆中;
        String s1 = "Hello";
        String s2 = s1 + "";
        String s3 = "Hello" + "";
        System.out.println(s1 == s2);
        System.out.println(s1 == s3);
    }

    @Test
    public void poolAndHeapStore7() {
        String s1 = "Hello";
        String s3 = "Hel";
        String s2 = "lo";
        System.out.println(OtherString.hello == s1);    // true
        System.out.println(OtherString.hello == "Hello");   // true
        System.out.println("Hel" + "lo" == s1);     // true
        System.out.println("Hel" + s2 == s1);       // false
        System.out.println(s3 + s2 == s1);          // false
    }

    /**
     * 通过动态反射改变String的value值
     *
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    private static void modifyStringValue() throws NoSuchFieldException, IllegalAccessException {
        String oriStr = new String("Hello");
        System.out.println("oriStr store address: " + Integer.toHexString(System.identityHashCode(oriStr)));
        System.out.println(oriStr);

        Field field = String.class.getDeclaredField("value");
        field.setAccessible(true);
        field.set(oriStr, "World".toCharArray());
        System.out.println("newStr store address: " + Integer.toHexString(System.identityHashCode(oriStr)));
        System.out.println(oriStr);
    }

    // public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
    //     // poolAndHeapStore();
    //     // poolAndHeapStore2();
    //     // poolAndHeapStore3();
    //     // poolAndHeapStore4();
    //     // poolAndHeapStore5();
    //     // poolAndHeapStore6();
    //     // poolAndHeapStore7();
    //     // modifyStringValue();
    //     basicStringPoolStore();
    // }
}

class OtherString {
    static String hello = "Hello";
}