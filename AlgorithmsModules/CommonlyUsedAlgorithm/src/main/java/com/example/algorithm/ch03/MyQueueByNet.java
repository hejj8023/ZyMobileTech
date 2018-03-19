package com.example.algorithm.ch03;

import com.example.algorithm.utils.ArrayUtil;

/**
 * Created by example on 2018/3/7.
 * 先进先出
 */

public class MyQueueByNet {

    private static final int DEFAULT_COUNT = 5;
    private int elements;
    private int font;
    private int end;

    private int[] mData;

    public MyQueueByNet() {
        mData = new int[DEFAULT_COUNT];
        elements = 0;
        font = 0;
        end = -1;
    }

    public MyQueueByNet(int size) {
        mData = new int[size];
        elements = 0;
        font = 0;
        end = -1;
    }

    /**
     * 在队尾插入元素
     *
     * @param value
     */
    public void push(int value) {
        if (isFull()) {
            System.out.println("队列满了,无法添加新数据,当前队列数据如下:");
            show();
            return;
        }

        // 循环队列的特点
        if (end == mData.length - 1) {
            end = -1;
        }
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

    public int popup() {
        if (isEmpty()) {
            System.out.println("队列为空了，无法移除元素");
            return 0;
        }

        int tmp = mData[font++];
        if (font == mData.length) {
            font = 0;
        }
        elements--;
        return tmp;
    }

    /**
     * 队头取
     *
     * @return
     */
    public int peek() {
        return mData[font];
    }
}
