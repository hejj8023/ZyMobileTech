package com.example.algorithm.ch06;

/**
 * Created by example on 2018/3/9.
 * 进制转换
 * 思想：16进制占4位二进制位  8进制占3位二进制位  二进制占1位二进制位
 * 首先取二进制数最后4位  然后向右移位使用>>>符号是因为防止负数的原因。
 */

public class HexConvert {

    /**
     * 十进制-》十六进制
     *
     * @param n
     */
    public static void toHex(int n) {
        trans(n, 15, 4);
    }

    /**
     * 十进制->二进制
     *
     * @param n
     */
    public static void toBinary(int n) {
        trans(n, 1, 1);
    }

    /**
     * 十进制->> 八进制
     *
     * @param n
     */
    public static void toOctal(int n) {
        trans(n, 7, 3);
    }

    private static void trans(int n, int base, int offset) {
        if (n == 0) {
            System.out.println("0");
            return;
        }

        /*对应关系表*/
        char[] chs = {
                '0', '1', '2', '3',
                '4', '5', '6', '7',
                '8', '9', 'A', 'B',
                'C', 'D', 'E', 'F'
        };

        char[] arr = new char[32];
        int pos = arr.length;
        while (n != 0) {
            int temp = n & base;
            // 数据存储到数组中
            arr[--pos] = chs[temp];
            // 把值往后移，一直移到0为止
            n = n >>> offset;
        }
        displayArr(arr, pos);
    }

    private static void displayArr(char[] arr, int pos) {
        for (int i = pos; i < arr.length; i++) {
            //  显示数组中存储的值
            System.out.print(arr[i]);
        }
        System.out.println();
    }
}
