package com.example.algorithm.ch06;

/**
 * Created by example on 2018/3/9.
 * 求指定数的阶乘
 */

public class OFactorial {
    public static long get(int n) {
        System.out.println("n = " + n);
        if (n <= 1) {
            return 1;
        }


        long i = get(n - 1);
        System.out.println("i = " + i);
        long i1 = n * i;
        System.out.println("i1 = " + i1);
        return i1;
    }
}
