package com.example.algorithm.sample;

/**
 * Created by example on 2018/3/5.
 * 求1000阶乘的结果末尾有多少个0
 * 1000的阶乘已经是天文数字了，所以不可能计算出来，再看有多少个0
 * 解题思路：两个素数2、5，相乘即可得到10，我们可以认为，有多少组2、5，结尾就有多少个0
 * 操作方法：操作1到1000中所有的数，看每个数能被2和5整除几次，并分别统计，假设被2整除8次，被5整除12次，那我们可以认为有8组（2，5），即8个0
 */

public class Homework02 {
    public void run() {
        algor();
    }

    private void algor() {
        int count2 = 0;
        int count5 = 0;

        for (int i = 1; i <= 1000; i++) {
            int dNum = i;
            while (dNum % 2 == 0) {
                count2++;
                dNum /= 2;
            }

            System.out.println("dNum = " + dNum + " , count2 = " + count2);
            while (dNum % 5 == 0) {
                count5++;
                dNum /= 5;
            }
            System.out.println("dNum = " + dNum + " , count5 = " + count5);
        }
        System.out.println("结尾0的个数为:" + Math.min(count2, count5));
    }
}

/**
 * 素数是个科学计算中很重要的一个概念，素，可以理解为很单纯的意思，比如：白素纸，白素贞，元素。素数也叫质数，质也可以理解为单纯的意思吧，质量，物质，本质。
 * 素和质连起来就是素质，素质用来形容人的话，可以理解为：最基本的道德，最原始的人性，等等。
 * 素数就是最纯净的数，没有任何其它成分的数，其它的数都可以说是由素数相乘出来的。
 * 所以，理解好素数，对于数学和程序来说，有重要的意义。
 */