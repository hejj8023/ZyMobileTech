package com.example.algorithm.ch02;

import com.example.algorithm.utils.ArrayUtil;

/**
 * Created by example on 2018/3/6.
 * 冒泡排序
 */

public class BubbleSort extends Sortter {

    /**
     * 如果对于一个本身有序的序列，或则序列后面一大部分都是有序的序列，
     * 此算法就会浪费很多的时间开销
     *
     * @param arr
     */
    public static void sort(long[] arr) {
        costCalc.start("bubble sort _ 01 : ===> ");
        long temp = 0;

        for (int i = 0; i < arr.length - 1; i++) {
            System.out.println(" o s ==>>");
            // 已经排序过的就不需要再排了
            for (int j = arr.length - 1; j > i; j--) {
                ArrayUtil.display(arr, arr.length);
                System.out.println("    i s ==>>");
                if (arr[j] < arr[j - 1]) {
                    temp = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = temp;
                }
                System.out.println("    i e ==>>");
            }
            System.out.println(" o e ==>>");
            ArrayUtil.display(arr, arr.length);
        }
        costCalc.end();
    }

    /**
     * 方法1的优化:
     * 这里设置一个标志flag，如果这一趟发生了交换，则为true，
     * 否则为false。明显如果有一趟没有发生交换，说明排序已经完成
     *
     * @param arr
     */
    public static void sort2(long[] arr) {
        costCalc.start("bubble sort _ 02 : ===> ");
        int j, k = arr.length;
        long temp;
        // 发生了交换就为true,没发生变化就为false,第一次判断时必须为true
        boolean falg = true;
        while (falg) {
            // 每次开始排序前，都设置flag为未排序过
            falg = false;
            System.out.println(" o s ==>>");
            for (j = 1; j < k; j++) {
                System.out.println("    i s ==>> j = " + j);
                ArrayUtil.display(arr, arr.length);
                if (arr[j] < arr[j - 1]) {
                    temp = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = temp;

                    // 交换过数据
                    falg = true;
                }
                System.out.println("    i e ==>>");
                ArrayUtil.display(arr, arr.length);
            }
            System.out.println(" o e ==>>");
            ArrayUtil.display(arr, arr.length);
            // 减小一次排序的尾边界
            k--;
        }
        costCalc.end();
    }

    /**
     * 在有一个包含1000个数的数组，仅前面100个无序，后面900
     * 个都已排好序且都大于前面100个数字，那么在第一趟遍历后，
     * 最后发生交换的位置必定小于100，且这个位置之后的数据必定
     * 已经有序了，也就是这个位置以后的数据不需要再排序了，
     * 于是记录下这位置，第二次只要从数组头部遍历到这个位置就可以了。
     * 如果是对于上面的冒泡排序算法2来说，虽然也只排序100次，
     * 但是前面的100次排序每次都要对后面的900个数据进行比较，
     * 而对于现在的排序算法3，只需要有一次比较后面的900个数据，
     * 之后就会设置尾边界，保证后面的900个数据不再被排序
     *
     * @param arr
     */
    public static void sort3(long[] arr) {
        costCalc.start("bubble sort _ 03 : ===> ");
        int j, k;
        long temp;
        // flag来记录最后交换的位置，也就是排序的尾边界
        int falg = arr.length;
        while (falg > 0) {
            // k 来记录遍历的尾边界
            k = falg;
            falg = 0;
            System.out.println(" o s ==>>");
            for (j = 1; j < k; j++) {
                System.out.println("    i s ==>> j = " + j);
                ArrayUtil.display(arr, arr.length);
                if (arr[j] < arr[j - 1]) {
                    temp = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = temp;

                    // 交换过数据
                    falg = j; // 记录最新的边界
                }
                System.out.println("    i e ==>>");
                ArrayUtil.display(arr, arr.length);
            }
            System.out.println(" o e ==>>");
            ArrayUtil.display(arr, arr.length);
        }
        costCalc.end();
    }
}
