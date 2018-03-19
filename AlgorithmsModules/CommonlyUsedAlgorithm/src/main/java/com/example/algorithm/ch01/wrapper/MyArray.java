package com.example.algorithm.ch01.wrapper;

/**
 * Created by example on 2018/3/6.
 * // 使用自定义类封装数组
 * // 添加类方法来实现数据操作
 */

public class MyArray {
    public long[] arrs;
    // 数组的长度
    public int elements;

    public MyArray() {
        arrs = new long[50];
    }

    public MyArray(int maxSize) {
        arrs = new long[maxSize];
    }

    /**
     * 添加数据
     *
     * @param value
     */
    public void insert(long value) {
        arrs[elements] = value;
        elements++;
    }

    /**
     * 查找元素所在的位置
     *
     * @param value
     */
    public int indexOf(int value) {
        int index = -1;
        for (int i = 0; i < elements; i++) {
            if (arrs[i] == value) {
                index = i;
                break;
            }
        }
        return index;
    }

    /**
     * 查找元素所在的位置
     *
     * @param value
     */
    public int indexOf_1(int value) {
        int i;
        for (i = 0; i < elements; i++) {
            if (arrs[i] == value) {
                break;
            }
        }
        // 找到结尾没有找到就返回-1
        if (i == elements) {
            return -1;
        } else {
            return i;
        }
    }

    /**
     * 查找数据
     *
     * @param index
     * @return
     */
    public long get(int index) {
        if (index >= elements || index < 0) {
            throw new ArrayIndexOutOfBoundsException();
        } else {
            return arrs[index];
        }
    }

    /**
     * 删除元素
     * //     *  思路:
     * //     *          [ 1,2,3,4]
     * //     *   ----->>> delete 3
     * //     *          [1,2,4]
     * //     *          后面的元素往前面移动
     *
     * @param index
     * @return
     */
    public void remove(int index) {
        if (index >= elements || index < 0) {
            throw new ArrayIndexOutOfBoundsException();
        } else {
            for (int i = index; i < elements; i++) {
                arrs[index] = arrs[index + 1];
            }
            elements--;
        }
    }

    /**
     * 在指定位置插入元素
     * 所有的元素往后移动
     *
     * @param index
     * @param value
     */
    public void insert(int index, int value) {
        if (index >= elements || index < 0) {
            throw new ArrayIndexOutOfBoundsException();
        } else {
            // 从后面往前面操作，因为所有的元素都要后移
            for (int i = elements; i > index; i--) {
                arrs[i] = arrs[i - 1];
            }
            arrs[index] = value;
            elements++;
        }
    }

    public int getSize() {
        return elements;
    }
}
