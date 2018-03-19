package com.example.algorithm.sample;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by example on 2018/3/5.
 * 找出数组中的重复值
 * 思路:
 * 使用嵌套循环，拿数组中的元素，依次和后面的元素进行比较，找到就结束
 * // *     arr = {1,2,3,5,3}
 * // *     o:0-> arr.len -2 ( 少比较一次)
 * // *     i:oI+1 -> arr.len
 * // *      for(int i=0;i<=arr.len-2;i++){  // 优化的代码的处理
 * // *          for(int j=i+1;j<=arr.len-1;j++){
 * // *              if(arr[i] == arr[j]{
 * // *                  break;
 * // *              }
 * // *          }
 * // *      }
 */

public class Sample01 {
    public void run(int[] arr) {
        if (arr == null)
            return;

        if (arr.length == 0)
            return;

        Map<Integer, String> dupleList = new HashMap<>();
        for (int i = 0; i <= arr.length - 2; i++) {
            int def = arr[i];
            for (int j = i + 1; j <= arr.length - 1; j++) {
                if (def == arr[j]) {
                    dupleList.put(i, "index ( i = " + i + " & j = " + j + " ) , value = " +
                            arr[i]);
                    break;
                }
            }
        }
        int dupleCount = dupleList.size();
        System.out.println("相同的元素个数: " + dupleCount);
        for (Map.Entry<Integer, String> entry : dupleList.entrySet()) {
            System.out.println(entry.getValue());
        }

    }
}
