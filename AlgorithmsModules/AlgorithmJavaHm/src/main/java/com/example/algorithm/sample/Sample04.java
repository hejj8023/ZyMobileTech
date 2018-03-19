package com.example.algorithm.sample;

import com.example.algorithm.DefaultCostCalc;

/**
 * Created by example on 2018/3/5.
 * 交换两个数的位置
 */

public class Sample04 {
    public void run() {
        DefaultCostCalc costCalc = new DefaultCostCalc();
        for (int i = 0; i < 1000; i++) {
            int m = 10;
            int n = 20;

            // 空间换时间 : costtime = 2566 纳秒
            algorith01(m, n, costCalc);

            // 时间换空间 : costtime = 8661 纳秒
            algorith02(costCalc, m, n);
        }
    }

    private void algorith02(DefaultCostCalc costCalc, int i, int j) {
        costCalc.start("时间换空间 :");
        i = i ^ j; // i = i ^ j
        System.out.println("i = " + i + " , j = " + j);
        j = i ^ j; // j = (i ^ j) ^ j = i
        System.out.println("i = " + i + " , j = " + j);
        i = i ^ j; // i = i ^ j = (i ^ j) ^ i = j
        System.out.println("i = " + i + " , j = " + j);
        costCalc.end();
    }

    private void algorith01(int i, int j, DefaultCostCalc costCalc) {
        costCalc.start("空间换时间 :");
        int temp = i;
        i = j;
        j = temp;
        System.out.println("i = " + i + " , j = " + j);
        costCalc.end();
    }
}
