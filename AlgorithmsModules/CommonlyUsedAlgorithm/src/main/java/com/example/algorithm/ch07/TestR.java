package com.example.algorithm.ch07;

/**
 * Created by example on 2018/3/9.
 */

public class TestR {
    public static void main(String[] args) {
        Hanoi.play(3, 'A', 'B', 'C');
    }

    /***
     盘子1从 A 塔座到 C 塔座
     盘子 2 从 A 塔座到 B
     盘子1从 C 塔座到 B 塔座
     盘子 3 从 A 塔座到 C
     盘子1从 B 塔座到 A 塔座
     盘子 2 从 B 塔座到 C
     盘子1从 A 塔座到 C 塔座
     */
}
