package com.example.algorithm.ch06;

/**
 * Created by example on 2018/3/8.
 * <p>
 * 裴波那契数列
 */

public class Fibonacci {

    public static int get(int n) {
        if (n == 1) {
            return 0;
        } else if (n == 2) {
            return 1;
        } else {
            return get(n - 1) + get(n - 2);
        }
    }
}
