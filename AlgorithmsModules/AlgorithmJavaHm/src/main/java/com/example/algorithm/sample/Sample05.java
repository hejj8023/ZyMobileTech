package com.example.algorithm.sample;

import com.example.algorithm.DefaultCostCalc;

import java.util.HashMap;

/**
 * Created by example on 2018/3/5.
 * 裴波那契数列
 * 1,1,2,3,5,8,13,21,34,55
 * 求第10个位置的元素的数据
 * 规律：
 * 从第3个开始，每一个数据都是前两个数据的和
 * 第1个数据和第2个数据的值是1
 */

public class Sample05 {
    // 统计调用次数
    private int count = 0;

    public void run() {
        DefaultCostCalc defaultCostCalc = new DefaultCostCalc();

        algorith01(defaultCostCalc);

        System.out.println(" ----   ----  ");
        algorith02(defaultCostCalc);
        System.out.println(" ----   ----  ");

        algorith03(defaultCostCalc);
    }

    /**
     * 动态规划
     * m:832040
     动态规划实现 costtime = 7378 纳秒
     调用次数:1
     * @param defaultCostCalc
     */
    private void algorith03(DefaultCostCalc defaultCostCalc) {
        count = 0;
        defaultCostCalc.start("动态规划实现");
        int m = getNumber3(10);
        System.out.println("m:" + m);
        defaultCostCalc.end();
        System.out.println("调用次数:" + count);
    }

    private int getNumber3(int m) {
        count++;
        int f1 = 1, f2 = 1;
        int temp = 0;
        for (int i = 3; i <= m; i++) {
//            System.out.println(" s -> i = " + i + " , f1 = " + f1 + " , f2 = " + f2 + " , temp = " +
//                    "" + temp);
            temp = f1 + f2;
            f1 = f2;
            f2 = temp;
//            System.out.println(" e -> i = " + i + " , f1 = " + f1 + " , f2 = " + f2 + " , temp = " +
//                    "" + temp);
        }
        return temp;
    }


    /**
     * 备忘录算法实现
     * 使用缓存，减少同参数的函数的调用次数
     * algorith02 备忘录实现 costtime = 120612 纳秒
     * 调用次数:57
     *
     * @param defaultCostCalc
     */
    private void algorith02(DefaultCostCalc defaultCostCalc) {
        count = 0;

        defaultCostCalc.start("algorith02 备忘录实现");
        int m = getNumber2(10);
        System.out.println("m = " + m);
        defaultCostCalc.end();
        System.out.println("调用次数:" + count);
    }

    private static HashMap<Integer, Integer> cache = new HashMap<>();

    /**
     * 优化之后的算法
     *
     * @param m
     * @return
     */
    private int getNumber2(int m) {
        count++;
        // 指的是位置
        if (m == 1 || m == 2) {
            return 1;
        }

        if (cache.containsKey(m)) {
            return cache.get(m);
        } else {
            int result = getNumber2(m - 1) + getNumber2(m - 2);
            cache.put(m, result);
            return result;
        }
    }

    /**
     * 递归实现: costtime = 4409374518 纳秒
     * 调用次数:1664079
     * 相同参数的方法会被调用多次
     *
     * @param defaultCostCalc
     */
    private void algorith01(DefaultCostCalc defaultCostCalc) {
        defaultCostCalc.start("递归实现:");
        int m = getNumber(10);
        System.out.println("m = " + m);
        defaultCostCalc.end();

        System.out.println("调用次数:" + count);
    }

    /**
     * 使用递归实现
     *
     * @param m
     * @return
     */
    private int getNumber(int m) {
        count++;
        // 指的是位置
        if (m == 1 || m == 2) {
            return 1;
        }
        int number = getNumber(m - 1);
        int number2 = getNumber(m - 2);
        int result = number + number2;
        System.out.println("m = " + m + " , n1 = " + number + " , n2 = " + number2);
        return result;
    }
}
