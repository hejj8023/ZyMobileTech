package com.example.algorithm.sample;

import com.example.algorithm.DefaultCostCalc;

/**
 * Created by example on 2018/3/5.
 * <p>
 * //题设：某门户网站，具有如下业务功能
 * 客户输入个人信息时，当输入年龄，会根据输入的年龄值
 * 显示其所属年龄段
 * //     0 ～  9 儿童
 * //     10 ～ 19 少年
 * //     20 ～ 29 青年
 * //     30 ～ 39 青壮年
 * //     40 ～ 49 壮年
 * //     50 ～ 59 中年
 * //     60 ～ 69 中老年
 * //     70 ～ 79 老年
 * //     80 ～ 89 老老年
 * //     90 ～ 99 老老老年
 * 问题：上述业务日均访问量超百万次，设计完成上述功能的程序
 * 百万次访问次数多，要求速度最快，一定是速度优先，时间尽量少
 */

public class Homework01 {
    public void run() {
        DefaultCostCalc costCalc = new DefaultCostCalc();
        al01(30, costCalc);
        al02(30, costCalc);
        al03(30, costCalc);
    }

    /**
     * 普通方式三
     * @param num
     * @param costCalc
     */
    private void al03(int num, DefaultCostCalc costCalc) {
        costCalc.start("al03");
        String[] sexs = {
                "儿童", "少年", "青年", "青壮年", "壮年", "中年",
                "中老年", "老年", "老老年", "老老老年"
        };
        costCalc.end();
        System.out.println(sexs[num / 10]);
    }

    /**
     * 普通方式b
     *
     * @param age
     * @param costCalc
     */
    private void al01(int age, DefaultCostCalc costCalc) {
        costCalc.start("al01");
        String type = "";
        if (age < 0 && age < 10) {
            type = "儿童";
        } else if (age <= 10 && age <= 19) {
            type = "少年";
        } else if (age <= 20 && age <= 29) {
            type = "青年";
        } else if (age <= 30 && age <= 39) {
            type = "青壮年";
        } else if (age <= 40 && age <= 49) {
            type = "壮年";
        } else if (age <= 50 && age <= 59) {
            type = "中年";
        } else if (age <= 60 && age <= 69) {
            type = "中老年";
        } else if (age <= 70 && age <= 79) {
            type = "老年";
        } else if (age <= 80 && age <= 89) {
            type = "老老年";
        } else if (age <= 90 && age <= 99) {
            type = "老老老年";
        }
        System.out.println(" type = " + type);
        costCalc.end();
    }

    /**
     * 普通方式a
     *
     * @param age
     * @param costCalc
     */
    private void al02(int age, DefaultCostCalc costCalc) {
        costCalc.start("al02");
        String type = "";
        switch (age / 10) {
            case 0:
                type = "儿童";
                break;

            case 1:
                type = "少年";
                break;
            case 2:
                type = "青年";
                break;
            case 3:
                type = "青壮年";
                break;
            case 4:
                type = "壮年";
                break;
            case 5:
                type = "中年";
                break;
            case 6:
                type = "中老年";
                break;
            case 7:
                type = "老年";
                break;
            case 8:
                type = "老老年";
                break;
            case 9:
                type = "老老老年";
                break;
        }
        System.out.println(" type = " + type);
        costCalc.end();
    }
}


/**
 * type = 青壮年
 * al01 costtime = 264319 纳秒
 * type = 青壮年
 * al02 costtime = 18925 纳秒
 * al03 costtime = 6416 纳秒
 * 青壮年
 */