package com.example.algorithm.ch02;

import com.example.algorithm.utils.ArrayUtil;

/**
 * Created by example on 2018/3/7.
 */

public class SelectionSort extends Sortter {
    public static void sort(long[] arr) {
        costCalc.start("selection sort == >");
        // 记录最小的值
        int k = 0;
        long temp;
        for (int i = 0; i < arr.length - 1; i++) {
            // 记录第一个
            k = i;
            System.out.println(" o s ==>> i = " + i);
            // 已经排序过的就不再排序了
            for (int j = i; j < arr.length; j++) {
                System.out.println("    i s ==>> j = " + j);
                ArrayUtil.display(arr, arr.length);
                // 使用当前位置的元素和小值索引对应的元素进行对比
                if (arr[j] < arr[k]) {
                    // 记录小值的索引
                    k = j;
                }
                System.out.println("    i e ==>>");
                ArrayUtil.display(arr, arr.length);
            }

            // 位置交换
            temp = arr[i];
            arr[i] = arr[k];
            arr[k] = temp;
            System.out.println(" o e ==>> k = " + k);
            ArrayUtil.display(arr, arr.length);
        }
        costCalc.end();
    }
}
