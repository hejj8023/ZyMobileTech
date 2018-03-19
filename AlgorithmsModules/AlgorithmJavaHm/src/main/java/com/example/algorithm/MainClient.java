package com.example.algorithm;

import com.example.algorithm.sample.Homework01;
import com.example.algorithm.sample.Homework02;
import com.example.algorithm.sample.Homework03;
import com.example.algorithm.sample.Sample01;
import com.example.algorithm.sample.Sample02;
import com.example.algorithm.sample.Sample03;
import com.example.algorithm.sample.Sample04;
import com.example.algorithm.sample.Sample05;

public class MainClient {
    public static void main(String[] args) {
        //  testSample02();
        // testSample03();
        // testSample04();
//         testSample05();

//        testHwork1();
//        testHwork2();
        testHwork3();
    }

    private static void testHwork3() {
        Homework03 homework03 = new Homework03();
        homework03.run();
    }

    private static void testHwork2() {
        Homework02 homework02 = new Homework02();
        homework02.run();
    }

    private static void testHwork1() {
        Homework01 homework01 = new Homework01();
        homework01.run();
    }

    private static void testSample05() {
        Sample05 sample05 = new Sample05();
        sample05.run();
    }

    private static void testSample04() {
        Sample04 sample04 = new Sample04();
        sample04.run();
    }

    private static void testSample03() {
        Sample03 sample03 = new Sample03();
        sample03.run();
    }

    private static void testSample01() {
        Sample01 sample01 = new Sample01();
        sample01.run(new int[]{15, 33, 22, 17, 9, 12, 15, 22, 27, 88, 100, 44, 9});
    }

    private static void testSample02() {
        Sample02 sample02 = new Sample02();
        sample02.run();
    }

    private static void basicTest(String arg) {
        long startTime = System.nanoTime();
        System.out.println("args = [" + arg + "]");

        System.out.println(3 ^ 4 ^ 4);
        long endTime = System.nanoTime();
        System.out.println("cost time = " + (endTime - startTime) + " ns");
    }

}
