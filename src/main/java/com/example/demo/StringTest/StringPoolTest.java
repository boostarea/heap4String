package com.example.demo.StringTest;

import java.util.Random;

/**
 * @description:
 * @author: chenr
 * @date: 2017/8/30
 */
public class StringPoolTest {

    static final int MAX = 1000 * 10000;
    static final String[] arr = new String[MAX];

    public static void main(String[] args) {
        /*使用new创建String，每次创建新对象*/
        /*String s1 = new String("asd");
        String s2 = new String("asd");
        System.out.println(s1 == s2);

        String s3 = "asd";
        System.out.println(s1.intern() == s1);*/
        Integer[] DB_DATA = new Integer[10];
        Random random = new Random(10 * 10000);
        for (int i = 0; i < DB_DATA.length; i++) {
            DB_DATA[i] = random.nextInt();
        }
        long t = System.currentTimeMillis();
        for (int i = 0; i < MAX; i++) {
            arr[i] = new String(String.valueOf(DB_DATA[i % DB_DATA.length]));
            // arr[i] = new String(String.valueOf(DB_DATA[i % DB_DATA.length])).intern();
        }

        System.out.println((System.currentTimeMillis() - t) + "ms");
        System.gc();

    }
}
