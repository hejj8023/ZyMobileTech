package com.example.algorithm.sample;

import com.example.algorithm.utils.ArrayUtil;

/**
 * Created by example on 2018/3/5.
 * 题目:
 * 现有0到99，共计100个整数，各不相同，将这100个数据放到一个数组中。
 * 然后，在这个数组中添加一个0-99的任意一个整数数字(唯一重复的数字),把这101个数据随机排列。
 * 问题:
 * 将这个重复的数字找出来
 */

public class Sample02 {
    public static void run() {
        int[] arrs = new int[101];
        for (int i = 0; i < 100; i++) {
            arrs[i] = i;
        }

        arrs[100] = 88;

        ArrayUtil.printArr(arrs);

        // 数据打乱操作,需要对数据进行换位操作
        ArrayUtil.randomSort(arrs, 101);

        ArrayUtil.printArr(arrs);

        algor01(arrs);

        algor02(arrs);

        algor03(arrs);
    }

    /**
     * - 1.拿数组中0索引的元素和所有的元素^
     * - 2.使用^的结果和数组中的所有的元素再^
     * - 3.最终保留的结果就是重复元素
     * 使用数组中第0位保留^的结果
     *
     * @param arrs
     */
    private static void algor03(int[] arrs) {
        long sTime = System.nanoTime();
        for (int i = 1; i < arrs.length; i++) {
            arrs[0] ^= arrs[i];
        }
        System.out.println(" arrs[0] = " + arrs[0]);

        for (int i = 0; i < 100; i++) {
            arrs[0] ^= i;
        }
        // 以空间换时间的算法
        System.out.println(" algor03 重复的数据 = " + arrs[0]);
        long eTime = System.nanoTime();
        System.out.println(" algor03 costtime = " + (eTime - sTime));
    }

    /**
     * 巧秒的算法:
     * 1. 把数组中所有元素的值累加起来
     * 2. 用这个和减去0-9的数据,最终的结果就是重复的元素(前提:0-9元素不重复)
     *
     * @param arrs
     */
    private static void algor02(int[] arrs) {
        long sTime = System.nanoTime();
        int sum = 0;
        for (int i = 0; i < arrs.length; i++) {
            sum += arrs[i];
        }

        for (int i = 0; i < 100; i++) {
            sum -= arrs[i];
        }
        // 以时间换空间的算法
        System.out.println(" algor02 重复的数据 = " + sum);
        long eTime = System.nanoTime();
        System.out.println(" algor02 costtime = " + (eTime - sTime));
    }

    /**
     * 使用嵌套循环，拿数组中的元素，依次和后面的元素进行比较，找到就结束,初学者的，也是最简单的
     *
     * @param arrs
     */
    private static void algor01(int[] arrs) {
        long sTime = System.nanoTime();
        int count = 0;
        zy:
        for (int i = 0; i < arrs.length; i++) {
            for (int j = i + 1; j < arrs.length; j++) {
                count += 1;
                if (arrs[i] == arrs[j]) {
                    System.out.println("找到重复的元素,value = " + arrs[i] + " , source index = " + i +
                            " ,duple index = " + j);
                    System.out.println("循环次数 = " + count);
                    break zy;
                }
            }
        }
        long eTime = System.nanoTime();
        System.out.println(" algor01 costtime = " + (eTime - sTime));
    }


}
