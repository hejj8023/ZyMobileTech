package com.example.algorithm.ch02;

import com.example.algorithm.utils.ArrayUtil;

/**
 * Created by example on 2018/3/7.
 */

public class ShellSort extends Sortter {

    public static void sort(long[] arr) {
        double d1 = arr.length;
        long temp = 0;

        while (true) {
            d1 = Math.ceil(d1 / 2);
            int d = (int) d1;
            System.out.println(" d1 = " + d1 + " , d = " + d);
            for (int x = 0; x < d; x++) {
                System.out.println("x = " + x + " , o s =====>");
                System.out.println("");
                for (int i = x + d; i < arr.length; i += d) {
                    System.out.println("i = " + i + " i s =====>");
                    int j = i - d;
                    temp = arr[i];
                    System.out.println(" j = " + j + " , temp = " + temp);
                    for (; j >= 0 && temp < arr[j]; j -= d) {
                        arr[j + d] = arr[j];
                    }
                    arr[j + d] = temp;
                    System.out.println(" i e =====>");
                }
                System.out.println(" o e =====>");
                System.out.println("");
            }

            if (d == 1) {
                break;
            }

            ArrayUtil.display(arr, arr.length);
        }
    }
}