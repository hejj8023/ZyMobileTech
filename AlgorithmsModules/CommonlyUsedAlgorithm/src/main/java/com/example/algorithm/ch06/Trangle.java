package com.example.algorithm.ch06;

/**
 * Created by example on 2018/3/8.
 * 三角数字
 */

public class Trangle {

    /**
     * 三角数实现的第一种方式
     *
     * @param n
     * @return
     */
    public static int create1(int n) {
        int total = 0;
        while (n > 0) {
            System.out.println("n -> s = " + n + " , total = " + total);
            /**
             //    n = 5 , total = 0
             //    n1 ->  total = 0+5 = 5
             //           n-- = 4
             //
             //    n2 ->  total = 5+4=9
             //           n-- = 3
             //    n3 ->  total = 9+3 = 12
             //           n-- = 2
             //    n4 -> total = 12 + 2 = 14
             //           n-- = 1
             //    n5 -> total =  14 + 1
             //          n-- = 0
             //    n6 -> break
             */
            total = total + n;
            n--;
            System.out.println("n -> e = " + n + " , total = " + total);
        }
        return total;
    }

    public static int create2(int n) {
        /**
         *递归操作详解
         //  i1 = 1 , i = 3
         //  i1 = 3 , i = 6
         //  i1 = 6 , i = 10
         //  i1 = 10 , i = 15
         //  15

         //  调用
         //  n = 5 ==> 5 + x(5-1)
         //  n = 4 ==> 4 + x(4-1)
         //  n = 3 ==> 3 + x(3-1)
         //  n = 2 ==> 2 + x(2-1)
         //  返回
         //  n = 1 <== 1
         //  n = 2 <== 2 + 1 = 3
         //  n = 3 <== 3 + 3 = 6
         //  n = 4 <== 4 + 6 = 10
         //  n = 5 <== 5 + 10 = 15
         */
        if (n == 1) {
            return 1;
        } else {
            int i1 = create2(n - 1);
            int i = n + i1;
            System.out.println("i1 = " + i1 + " , i = " + i);
            return i;
        }

    }
}
