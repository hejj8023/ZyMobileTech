package com.example.algorithm.ch07;

/**
 * Created by example on 2018/3/9.
 * 汉诺塔
 */

public class Hanoi {
    public static void play(int n, char from, char inter, char to) {
        // from 移动c，使用b作为临时棒
        if (n == 1) {
            // 1个直接移动
            System.out.println(" 盘子1从 " + from + " 塔座到 " + to + " 塔座");
        } else {
            play(n - 1, from, to, inter); // 递归调用,移动a->inter ,用c作为临时棒
            System.out.println(" 盘子 " + n + " 从 " + from + " 塔座到 " + to);
            play(n - 1, inter, from, to); // 递归调用,移动b->to ,用a作为临时棒
        }
    }
}
