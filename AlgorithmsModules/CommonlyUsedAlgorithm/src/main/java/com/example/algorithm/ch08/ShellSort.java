package com.example.algorithm.ch08;

import com.example.algorithm.utils.ArrayUtil;

/**
 * Created by example on 2018/3/9.
 * 希尔排序
 */

public class ShellSort {
    public static void sort(int[] arr) {
        int h = 1;
        // 计算最大间隔
        while (h < arr.length / 3) {
            h = h * 3 + 1;
        }

        System.out.println(" o h = " + h);

        while (h > 0) {
            // 进行插入排序
            int temp = 0;

            System.out.println(" w > h = " + h);

            for (int i = h; i < arr.length; i++) {
                temp = arr[i];
                int j = i;
                System.out.println(" f > temp = " + temp + " , j = " + j);

                // TODO: 2018/3/9 只要j>h-1&&arr[j-h]>=temp这个循环就不会结束，就会一直对它之前
                // TODO: 的元素进行换位操作，其实这里就是为元素找到的合适的插入位置
                while (j > h - 1 && arr[j - h] >= temp) {
                    arr[j] = arr[j - h];
                    j -= h;

                    System.out.println(" w f w s -------->>");
                    ArrayUtil.display(arr);
                    System.out.println(" w f w e -------->>");
                }
                arr[j] = temp;
                ArrayUtil.display(arr);
            }
            ArrayUtil.display(arr);
            // 减小间隔
            h = (h - 1) * 3;
            System.out.println(" w e > h = " + h);
        }

    }
}


/**
 * // 12	21	5	-1	3
 * // 原理分析:
 * //     i = 2,temp = 5,j = 2, h =1
 * //     while (j > h - 1 && arr[j - h] >= temp) {
 * //         arr[j] = arr[j - h];
 * //         j -= h;
 * //     }
 * //
 * //     loop:
 * //       ->1 :
 * //            2 > 1-1 && arr[2-1] >= 5
 * //                arr[2] = arr[2-1]  12,21,21,-1,3
 * //                j -= h j = 1
 * //       ->2 :
 * //            1 > 1-1 && arr[1] >=5
 * //                arr[1] = arr[1-1] 12,12,21,-1,3
 * //                j-=h j = 0
 * //
 * //       arr[j] = temp
 * //           5,12,21,-1,3
 */