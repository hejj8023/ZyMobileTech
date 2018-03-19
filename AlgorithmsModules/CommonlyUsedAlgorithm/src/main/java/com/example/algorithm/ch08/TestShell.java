package com.example.algorithm.ch08;

import com.example.algorithm.utils.ArrayUtil;

/**
 * Created by example on 2018/3/9.
 * <p>
 * 测试希尔排序
 */

public class TestShell {
    public static void main(String[] args) {
        int[] arr = {
                21, 12, 5, -1, 3
        };
        ArrayUtil.display(arr);

        ShellSort.sort(arr);

        ArrayUtil.display(arr);
    }

    /**
     21	12	5	-1	3
     --- --- ---

     o h = 1
     w > h = 1
     f > temp = 12 , j = 1
     w f w s -------->>
     21	21	5	-1	3
     --- --- ---

     w f w e -------->>
     12	21	5	-1	3
     --- --- ---

     f > temp = 5 , j = 2
     w f w s -------->>
     12	21	21	-1	3
     --- --- ---

     w f w e -------->>
     w f w s -------->>
     12	12	21	-1	3
     --- --- ---

     w f w e -------->>
     5	12	21	-1	3
     --- --- ---

     f > temp = -1 , j = 3
     w f w s -------->>
     5	12	21	21	3
     --- --- ---

     w f w e -------->>
     w f w s -------->>
     5	12	12	21	3
     --- --- ---

     w f w e -------->>
     w f w s -------->>
     5	5	12	21	3
     --- --- ---

     w f w e -------->>
     -1	5	12	21	3
     --- --- ---

     f > temp = 3 , j = 4
     w f w s -------->>
     -1	5	12	21	21
     --- --- ---

     w f w e -------->>
     w f w s -------->>
     -1	5	12	12	21
     --- --- ---

     w f w e -------->>
     w f w s -------->>
     -1	5	5	12	21
     --- --- ---

     w f w e -------->>
     -1	3	5	12	21
     --- --- ---

     -1	3	5	12	21
     --- --- ---

     w e > h = 0
     -1	3	5	12	21
     --- --- ---
     */
}
