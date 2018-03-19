package com.example.algorithm.sample;

import java.util.HashMap;

/**
 * Created by example on 2018/3/5.
 * 爬楼梯
 * 有一座高度是10级台阶的楼梯，从下往上走，
 * 每跨一步只能向上1级或者2级台阶。要求用程序来求出一共有多少种走法。
 * 参考资料:http://blog.csdn.net/hellorichen/article/details/73499124
 */

public class Homework03 {
    public void run() {
        System.out.println(getClimbingWays(10));
        System.out.println(getClimbingWays2(10));
        System.out.println(getClimbingWays3(10));
    }

    /**
     * 动态规划
     *
     * @param n
     * @return
     */
    private int getClimbingWays3(int n) {
        if (n < 0) {
            return 0;
        }

        if (n == 1) {
            return 1;
        }

        if (n == 2) {
            return 2;
        }

        int a = 1;
        int b = 2;
        int temp = 0;
        for (int i = 0; i <= n; i++) {
            temp = a + b;
            a = b;
            b = temp;
        }

        return temp;
    }

    HashMap<Integer, Integer> cache = new HashMap<Integer, Integer>();

    /**
     * 备忘录算法
     *
     * @param n
     * @return
     */
    private int getClimbingWays2(int n) {
        if (n < 0) {
            return 0;
        }

        if (n == 1) {
            return 1;
        }

        if (n == 2) {
            return 2;
        }

        if (cache.containsKey(n)) {
            return cache.get(n);
        } else {
            int result = getClimbingWays2(n - 1) + getClimbingWays2(n - 2);
            cache.put(n, result);
            return result;
        }
    }

    /**
     * 递归方式
     *
     * @param n
     * @return
     */
    private int getClimbingWays(int n) {
        if (n < 0) {
            return 0;
        }

        if (n == 1) {
            return 1;
        }

        if (n == 2) {
            return 2;
        }
        return getClimbingWays(n - 1) + getClimbingWays(n - 2);
    }
}
