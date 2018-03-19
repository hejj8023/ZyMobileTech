package com.example.algorithm.ch03;

import com.example.algorithm.utils.ArrayUtil;

import java.util.Arrays;

/**
 * Created by example on 2018/3/7.
 * 先进后出，后进先出
 */

public class MyStack {
    // 默认长度
    private static final int DEFAULT_NUMS = 10;
    private int mTop;

    // 底层实现为数组
    private int[] mData;

    public MyStack() {
        mData = new int[DEFAULT_NUMS];
        mTop = -1;
    }

    public MyStack(int size) {
        mData = new int[size];
        mTop = -1;
    }

    /**
     * 添加数据
     *
     * @param value
     */
    public void push(int value) {
        // System.out.println(" ++ mTop = " + (++mTop) + " , mtop++ = " + (mTop++));
        if (isFull()) {
            System.out.println("stack is full , 不能添加新的元素，栈中元素如下:");
            show();
            return;
        }
        mData[++mTop] = value;
    }


    /**
     * 查看栈顶的元素
     * 数据不会出栈
     *
     * @return
     */
    public long peek() {
        // 数据不出栈
        int data = mData[mTop];
        return data;
    }

    /**
     * 从栈顶出栈
     *
     * @return
     */
    public long popup() {
        if (isEmpty()) {
            System.out.println("栈为空了，不能pop了");
            return 0;
        }
//        System.out.println("mTop = " + mTop);
        int value = this.mData[mTop];
        // 数据会出栈,为了实现 效果第一个数据每次不复制进新的数组
        mData = Arrays.copyOfRange(mData, 0, mData.length - 1);
        mTop--;
        return value;
    }

    /**
     * 是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return mTop == -1;
    }

    /**
     * 是否满了
     *
     * @return
     */
    public boolean isFull() {
        return mTop == mData.length - 1;
    }

    /**
     * 显示数据
     */
    public void show() {
        ArrayUtil.display(mData);
    }

}
