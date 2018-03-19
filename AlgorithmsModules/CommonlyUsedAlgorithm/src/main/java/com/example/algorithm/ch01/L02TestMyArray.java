package com.example.algorithm.ch01;

import com.example.algorithm.ch01.wrapper.MyArray;
import com.example.algorithm.ch01.wrapper.MyOrderArray;
import com.example.algorithm.utils.ArrayUtil;

/**
 * Created by example on 2018/3/6.
 */

public class L02TestMyArray {
    public static void main(String[] args) {
        // defArrayTest();

        orderArrayTest();
    }

    private static void orderArrayTest() {
        MyOrderArray array = new MyOrderArray();
        array.insert(37);
        array.insert(99);
        array.insert(15);
        array.insert(44);
        // array.display();

        System.out.println(array.binarySearch(100));
    }

    private static void defArrayTest() {
        MyArray myArray = new MyArray(10);
        myArray.insert(100);
        myArray.insert(22);
        myArray.insert(33);
        myArray.insert(109);
        myArray.insert(74);
        ArrayUtil.display(myArray.arrs, myArray.getSize());
        // myArray.display_1();

        int index = myArray.indexOf_1(199);
        if (index >= 0) {
            System.out.println("元素位置 index = " + index);
        } else {
            System.out.println("未找到索引");
        }

        long value = myArray.get(0);
        System.out.println("value = " + value);

//        myArray.remove(1);
//
//        myArray.display_1();
        myArray.insert(2, 99);
        ArrayUtil.display_1(myArray.arrs, myArray.getSize());
    }
}
