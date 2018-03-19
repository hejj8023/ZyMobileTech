package com.example.algorithm.ch03;

import com.example.algorithm.utils.ArrayUtil;

/**
 * Created by example on 2018/3/7.
 * 循环队列:
 * 解决添加元素时，队列不够使用的问题
 */

public class MyCircleQueue {

    private static final int DEFAULT_COUNT = 5;
    private int elements;
    private int font;
    private int end;

    private int[] mData;

    public MyCircleQueue() {
        mData = new int[DEFAULT_COUNT];
        elements = 0;
        font = 0;
        end = -1;
    }

    public MyCircleQueue(int size) {
        mData = new int[size];
        elements = 0;
        font = 0;
        end = -1;
    }

    /**
     * 在队尾插入元素
     * 插入元素的时候，如果队列满了。这里会报异常，所以解决这个问题
     *
     * @param value
     */
    public void insert(int value) {
        //在插入元素的时候，如果队列满了。这里会报异常，所以为了解决这个问题，后面使用循环队列来解决，见MyCircleQueue
        // TODO: 2018/3/8 通过增加数组的长度，实现队列，数据不会覆盖
        /*if (end == mData.length - 1) {
            // 数组扩容
            expandSpace();
            // end等于之前数组长度-1，要不然会影响新加的数据的位置
            end = elements - 1;
        }*/

        // TODO: 2018/3/8 这种方式只是简单的修改end为-1从头开始，会对原数据造成覆盖
        /**
         //         *   add(end++) 0 end==4 重新回到-1,添加的时候，会加+1为0
         //         *              1
         //         *              2
         //         *              3
         //         *   end        4
         */
        if (end >= mData.length - 1) {
            end = -1;
        }
        mData[++end] = value;
        elements++;
    }

    /**
     * 扩容:原数组容易扩充2倍，然后将原数组中的数据复制到新数组中
     */
    private void expandSpace() {
        int[] a = new int[mData.length * 2];
        System.arraycopy(mData, 0, a, 0, mData.length);
        mData = a;
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
     * //     *                         0
     * //     *  first<remove> font ->  1
     * //     *                         2
     * //     *                         3
     *
     * @return
     */
    public int remove() {
        int value = mData[font++];
        // TODO: 2018/3/8 解决移除时，移到后末尾出现异常的问题
        if (font == mData.length) {
            font = 0;
        }
        elements--;
        return value;
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
