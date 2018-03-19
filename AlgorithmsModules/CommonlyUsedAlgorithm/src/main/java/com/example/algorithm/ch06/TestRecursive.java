package com.example.algorithm.ch06;

/**
 * Created by example on 2018/3/8.
 * <p>
 * 测试递归
 */

public class TestRecursive {
    public static void main(String[] args) {

        // calc(10);

        // System.out.println(Trangle.create2(5));
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


        // System.out.println(Trangle.create2(5));
        /**
         //   调用
         //   n = 5 ==> 5 + x(5-1)
         //   n = 4 ==> 4 + x(4-1)
         //   n = 3 ==> 3 + x(3-1)
         //   n = 2 ==> 2 + x(2-1)
         //   返回
         //   n = 1 <== 1
         //   n = 2 <== 2 + 1 = 3
         //   n = 3 <== 3 + 3 = 6
         //   n = 4 <== 4 + 6 = 10
         //   n = 5 <== 5 + 10 = 15
         */

        /*1 2 3 4 5
        0 1 1 2 3 (前两个数的和)*/
        // System.out.println(Fibonacci.get(5));

        // 求指定数的阶乘

        /**
         6*5*4*3*2*1=720
         {1,2,3,4,5,6}
         1*1 = 1
         2*1 = 2
         3*2*1 = 6
         4*3*2*1 = 24
         5*4*3*2*1 = 120
         6*5*4*3*2*1 = 720
         结论公式: n*f(n-1) ==> 递归实现
         */
        // System.out.println(OFactorial.get(5));


        /**
         * 从后面存，从前面取
         */
        /*arr = {
            ,,,,,,,,,,
            ,,,,,,,,,,
            ,,,,,,,,,,
            A,
        }*/
        // HexConvert.toHex(10); // A
        /*
            10 & 7 = 2
              {.. n31= 2}
            2 >>>   3 = 1
            1 & 6 = 1
              {.. n30 = 1}
            1 >>> 3 = 0
            {12}
         */
        HexConvert.toOctal(10); // 12
        // {同理}
       // HexConvert.toBinary(10);  // 1010

    }

    private static void calc(int n) {
        if (n == 0) {
            return;
        }

        System.out.println("n = [" + n + "]");
        n -= 1;
        calc(n);
    }
}
