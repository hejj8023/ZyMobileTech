package com.example.algorithm.ch01.wrapper;

/**
 * Created by example on 2018/3/6.
 * // 有序数组
 */

public class MyOrderArray {
    public long[] arrs;
    // 数组的长度
    public int elements;

    public MyOrderArray() {
        arrs = new long[50];
    }

    public MyOrderArray(int maxSize) {
        arrs = new long[maxSize];
    }

    /**
     * 添加数据
     *
     * @param value
     */
    public void insert(long value) {
        int i;
        for (i = 0; i < elements; i++) {
            if (arrs[i] > value) {
                break;
            }
        }

        // 从后面往前面操作，因为所有的元素都要后移
        for (int j = elements; j > i; j--) {
            arrs[j] = arrs[j - 1];
        }
        arrs[i] = value;
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

    public int getSize() {
        return elements;
    }

    /**
     * 二分查找数据
     *
     * @param value
     * @return
     */
    public long binarySearch(long value) {
        int middle = 0;
        int min = 0;
        int max = elements;

        while (true) {
            middle = (max + min) / 2;
            System.out.println("middle = " + middle + " , min = " + min + " , max = " + max);
            if (arrs[middle] == value) {
                return middle;
            } else if (min > max) {
                return -1;
            } else {
                // 往左边走
                if (arrs[middle] > value) {
                    max = middle - 1;
                } else {
                    // 往右边走
                    min = middle + 1;
                }
            }
        }
    }

    public long binarySearch2(long value) {
        int max, min, mid;
        max = elements;
        min = 0;
        mid = (max + min) / 2;
        while (arrs[mid] != value) {
            if (arrs[mid] > value) {
                max = mid - 1;
            } else if (arrs[mid] < value) {
                min = mid + 1;
            }

            if (min > max) {
                return -1;
            }
            mid = (max + min) / 2;
        }
        return mid;
    }
}
