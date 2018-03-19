package com.example.algorithm.ch03;

import com.example.algorithm.utils.ArrayUtil;

/**
 * Created by example on 2018/3/7.
 * 队列:先进先出
 */

public class MyQueue {

    private static final int DEFAULT_COUNT = 5;
    private int elements;
    private int font;
    private int end;

    private int[] mData;

    public MyQueue() {
        mData = new int[DEFAULT_COUNT];
        elements = 0;
        font = 0;
        end = -1;
    }

    public MyQueue(int size) {
        mData = new int[size];
        elements = 0;
        font = 0;
        end = -1;
    }

    /**
     * 在队尾插入元素
     * 插入元素的时候，如果队列满了。这里会报异常，所以为了解决这个问题，后面使用循环队列来解决，见MyCircleQueue
     *
     * @param value
     */
    public void insert(int value) {
        if (isFull()) {
            System.out.println("队列已满,无法添加新元素，队列数据如下:");
            show();
            return;
        }
        //在插入元素的时候，如果队列满了。这里会报异常，所以为了解决这个问题，后面使用循环队列来解决，见MyCircleQueue
        mData[++end] = value;
        elements++;
    }

    public void show() {
        ArrayUtil.display(mData);
    }

    public boolean isFull() {
        return elements == mData.length;
    }

    public boolean isEmpty() {
        return elements == 0;
    }

    /**
     * 从队头移除
     *
     * @return
     */
    public int remove() {
        int data = mData[font++];
        elements--;
        return data;
    }

    /**
     * 从队头获取
     *
     * @return
     */
    public int get() {
        return mData[font];
    }
}
