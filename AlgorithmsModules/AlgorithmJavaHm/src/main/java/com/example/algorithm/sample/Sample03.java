package com.example.algorithm.sample;

import com.example.algorithm.DefaultCostCalc;
import com.example.algorithm.listeners.ICostCalc;
import com.example.algorithm.utils.ArrayUtil;

import java.util.Random;

/**
 * Created by example on 2018/3/5.
 * <p>
 * 题目:
 * // *   现有0到99，共计100个整数，各不相同，将这100个数据放入一个数组中随机排列。
 * // *   将其中任意一个整数替换成0到99另一个数(唯一重复的数字)
 * // *   问题:
 * // *     将这个重复的数字找出来
 */

public class Sample03 {
    public void run() {
        DefaultCostCalc costCalc = new DefaultCostCalc();
        int[] arrs = new int[100];
        for (int i = 0; i < 100; i++) {
            arrs[i] = i;
        }
        ArrayUtil.printArr(arrs);

        // 随机排列数组中的数据
        ArrayUtil.randomSort(arrs, 100);
        ArrayUtil.printArr(arrs);

        Random random = new Random();
        int i = random.nextInt(100);
        int j = random.nextInt(100);
        // 两个随机数不能相等
        while (i == j) {
            i = random.nextInt(100);
            j = random.nextInt(100);
        }

        //将其中任意一个整数替换成0到99另一个数(唯一重复的数字)
        // arrs[j]处的值替换arr[i]处的值
        arrs[i] = arrs[j];

        ArrayUtil.printArr(arrs);

        // arr[i]中的数据就是重复数据
        System.out.println("重复的数据:" + arrs[i]);

        algor1(arrs, costCalc);

        algor2(arrs, costCalc);

    }

    /**
     * 创建一个新数组，遍历旧数组，拿旧数组中的元素作为新数组的索引，并把索引处的值+1
     *
     * @param arrs
     * @param costCalc
     */
    private void algor2(int[] arrs, DefaultCostCalc costCalc) {
        costCalc.start("algor2");
        int[] nArrs = new int[100];
        for (int i = 0; i < arrs.length; i++) {
            nArrs[arrs[i]]++;
            if (nArrs[arrs[i]] > 1) {
                System.out.println(" 重复的元素为:" + arrs[i]);
                break;
            }
        }
        costCalc.end();
    }

    /**
     * 依次查找
     *
     * @param arrs
     */
    private void algor1(int[] arrs, ICostCalc iCostCalc) {
        iCostCalc.start("algor1");
        for (int i = 0; i < arrs.length - 1; i++) {
            for (int j = i + 1; j < arrs.length; j++) {
                if (arrs[i] == arrs[j]) {
                    System.out.println("重复的元素为:" + arrs[i] + " s index = " + i + " , e index = " + j);
                }
            }
        }
        iCostCalc.end();
    }
}
