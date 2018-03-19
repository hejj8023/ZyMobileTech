package com.example.algorithm.ch02;

import com.example.algorithm.utils.ArrayUtil;

/**
 * Created by example on 2018/3/7.
 * <p>
 * 插入排序
 */

public class InsertSort extends Sortter {

    /**
     * @param arr
     */
    public static void sort(long[] arr) {
        long temp = 0;
        // 从1开始找插入点
        for (int i = 1; i < arr.length; i++) {
            temp = arr[i];
            // 记录插入的位置
            int j = i;
            System.out.println(" os == >>> ");
            System.out.println("i = " + i + " , temp = " + temp + " , j = " + j);
            while (j > 0 && arr[j - 1] > temp) {
                System.out.println(" -------- is  -------- ");
                System.out.println(" j = " + j + " , arr[j - 1] = " + arr[j - 1] + " , temp = " + temp);
                // 当前的元素位置 比初始的数据要大
                // 数据要往后移
                arr[j] = arr[j - 1];
                j--;
                System.out.println(" -------- is  -------- ");
            }
            arr[j] = temp;
            ArrayUtil.display(arr, arr.length);
            System.out.println(" os == >>> ");
            System.out.println("");
        }
    }

    /**
     * //     [ 13 17 5 22 -3 0 4 -1 ]
     * //     [ 5 13 17 22 -3 0 4 -1 ]
     * //     [ 5 13 17 22 -3 0 4 -1 ]
     * //     [ 5 13 17 22 -3 0 4 -1 ]
     * //     [ -3 5 13 17 22 0 4 -1 ]
     * //     [ -3 0 5 13 17 22 4 -1 ]
     * //     [ -3 0 4 5 13 17 22 -1 ]
     * //     [ -3 -1 0 4 5 13 17 22 ]
     * //     [ -3 -1 0 4 5 13 17 22 ]
     *
     * @param arr
     */
    public static void sort4(long[] arr) {
        long temp = 0;
        // 从1开始找插入点
        for (int i = 1; i < arr.length; i++) {
            temp = arr[i];
            // 记录插入的位置
            int j = i - 1;
            System.out.println("i = " + i);
            while (j >= 0 && arr[j] >= temp) {
                // 当前的元素位置 比初始的数据要大
                // 数据要往后移
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = temp;
            ArrayUtil.display(arr, arr.length);
        }
    }

    /**
     * //     [ 13 5 17 22 -3 0 4 -1 ]
     * //     [ 5 13 17 22 -3 0 4 -1 ]
     * //     [ 5 13 17 22 -3 0 4 -1 ]
     * //     [ 5 13 17 22 -3 0 4 -1 ]
     * //     [ -3 5 13 17 22 0 4 -1 ]
     * //     [ -3 0 5 13 17 22 4 -1 ]
     * //     [ -3 0 4 5 13 17 22 -1 ]
     * //     [ -3 -1 0 4 5 13 17 22 ]
     * //     [ -3 -1 0 4 5 13 17 22 ]
     *
     * @param arr
     */
    public static void sort3(long[] arr) {
        long temp = 0;
        for (int i = 1; i < arr.length; i++) {
            temp = arr[i];
            int j = i - 1;
            for (; j >= 0 && temp < arr[j]; j--) {
                arr[j + 1] = arr[j]; // 将大于temp的值整体后移一个单位
            }
            arr[j + 1] = temp;
            ArrayUtil.display(arr, arr.length);
        }
    }

    public static void sort2(long[] arr) {
        int i, j;
        long t;
        for (i = 1; i < arr.length; i++) {
            t = arr[i]; // 取出一个未排序的数据
            for (j = i - 1; j >= 0 && t < arr[j]; --j) { // 在排序序列中查找位置
                arr[j + 1] = arr[j]; // 向后移数据
            }
            arr[j + 1] = t; // 插入数据到序列
        }
    }
}