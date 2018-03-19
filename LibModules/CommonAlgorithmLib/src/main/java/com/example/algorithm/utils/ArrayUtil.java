package com.example.algorithm.utils;

import java.util.Random;

/**
 * Created by example on 2018/3/5.
 */

public class ArrayUtil {
    /**
     * 打印数组中的数据
     *
     * @param arrs
     */
    public static void printArr(int[] arrs) {
        int numCount = 0;
        for (int i = 0; i < arrs.length; i++) {
            if (++numCount % 10 == 0) {
                // 10 个才进行换行操作
                System.out.println(arrs[i] + "\t");
            } else {
                System.out.print(arrs[i] + "\t");
            }
        }
        System.out.println("");
        System.out.println(" --- --- --- ");
        System.out.println("");
    }

    public static void randomSort(int[] arrs, int maxValue) {
        Random random = new Random();
        for (int i = 0; i < arrs.length; i++) {
            int x = arrs[random.nextInt(maxValue)];
            int y = arrs[random.nextInt(maxValue)];
            // 交换数组中元素的位置
            int temp = arrs[x];
            arrs[x] = arrs[y];
            arrs[y] = temp;
        }
    }

    /**
     * 显示数据
     */
    public static void display(long[] arrs, int elements) {
        System.out.print("[ ");
        for (int i = 0; i < elements; i++) {
            System.out.print(arrs[i] + " ");
        }
        System.out.println("]");
    }

    /**
     * 显示数据
     */
    public static void display_1(long[] arrs, int elements) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < elements; i++) {
            sb.append(",").append((arrs[i]));
        }
        sb.deleteCharAt(0);
        System.out.println("[ " + sb.toString() + " ] ");
    }

    public static void display(Object obj) {
        if (obj instanceof int[]) {
            int[] arr = (int[]) obj;
            printArr(arr);
        } else if (obj instanceof long[]) {
            long[] arr = (long[]) obj;
            display(arr, arr.length);
        }
    }
}
