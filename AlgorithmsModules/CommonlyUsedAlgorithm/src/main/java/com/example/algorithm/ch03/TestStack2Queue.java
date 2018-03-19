package com.example.algorithm.ch03;

import com.example.algorithm.utils.ArrayUtil;

import java.util.Arrays;

/**
 * Created by example on 2018/3/7.
 */

public class TestStack2Queue {
    public static void main(String[] args) {
        // testStack();
        // testQueue();
        testCircleQueue();
//        testArrayCopy();
    }

    private static void testArrayCopy() {
        int[] s1 = {1, 3, 2, 5, 9};
        int[] s2 = new int[s1.length * 2];
        System.arraycopy(s1, 0, s2, 0, s1.length);
        ArrayUtil.printArr(s2);
        // 1	3	2	5	9	0	0	0	0	0

        int[] s4 = Arrays.copyOf(s1, s1.length * 2);
        ArrayUtil.printArr(s4);
        //         1	3	2	5	9	0	0	0	0	0
    }

    private static void testCircleQueue() {
        MyCircleQueue myQueue = new MyCircleQueue();
        myQueue.insert(15);
        myQueue.insert(3);
        myQueue.insert(12);
        myQueue.insert(11);
        myQueue.insert(4);
        myQueue.insert(7);
        myQueue.insert(14);
        myQueue.insert(22);
        myQueue.insert(31);
        myQueue.insert(8);
        myQueue.insert(9);
        myQueue.insert(27);
        myQueue.show();

        // 15	3	12	11	4	7	14	22	31	8
        // 9	27	0	0	0	0	0	0	0	0 数组扩容操作

        // 9	27	22	31	8 未对原数组进行扩容的时候显示的结果


        System.out.println("remove = " + myQueue.remove());
        System.out.println("remove = " + myQueue.remove());
        System.out.println("remove = " + myQueue.remove());
        System.out.println("remove = " + myQueue.remove());
        System.out.println("remove = " + myQueue.remove());
        System.out.println("remove = " + myQueue.remove());
        System.out.println("remove = " + myQueue.remove());
    }

    private static void testQueue() {
        // testNetQueue();

        testMyQueue();
    }

    private static void testMyQueue() {
        MyQueue myQueue = new MyQueue();
        myQueue.insert(15);
        myQueue.insert(3);
        myQueue.insert(12);
        myQueue.insert(11);
        myQueue.insert(4);
        myQueue.insert(7);
        myQueue.show();

        System.out.println("get = " + myQueue.get());

        System.out.println("remove = " + myQueue.remove());
        System.out.println("remove = " + myQueue.remove());
        System.out.println("remove = " + myQueue.remove());
        System.out.println("remove = " + myQueue.remove());
        System.out.println("remove = " + myQueue.remove());
        System.out.println("remove = " + myQueue.remove());
        System.out.println("remove = " + myQueue.remove());
    }

    private static void testNetQueue() {
        MyQueueByNet myQueue = new MyQueueByNet();
        myQueue.push(15);
        myQueue.push(3);
        myQueue.push(12);
        myQueue.push(11);
        myQueue.push(4);
        myQueue.push(7);
        myQueue.show();

        System.out.println("get = " + myQueue.peek());

        System.out.println("remove = " + myQueue.popup());
        System.out.println("remove = " + myQueue.popup());
        System.out.println("remove = " + myQueue.popup());
        System.out.println("remove = " + myQueue.popup());
        System.out.println("remove = " + myQueue.popup());
        System.out.println("remove = " + myQueue.popup());
        System.out.println("remove = " + myQueue.popup());
    }

    /**
     * 测试-栈
     */
    private static void testStack() {
        MyStack myStack = new MyStack();
        myStack.push(25);
        myStack.push(55);
        myStack.push(4);
        myStack.push(33);
        myStack.push(2);
        myStack.push(11);
        myStack.push(43);
        myStack.push(8);
        myStack.push(6);
        myStack.show();

        System.out.println("get = " + myStack.peek());
        myStack.show();

        System.out.println("get = " + myStack.peek());
        myStack.show();

        System.out.println("get = " + myStack.peek());
        myStack.show();

        StringBuilder sb = new StringBuilder();
        while (!myStack.isEmpty()) {
            sb.append(myStack.popup() + ",");
        }
        sb.deleteCharAt(sb.length() - 1);
        System.out.println("stack list = { " + sb.toString() + " } ");

        System.out.println("remove = " + myStack.popup());
        myStack.show();

        System.out.println("remove = " + myStack.popup());
        myStack.show();

        System.out.println("remove = " + myStack.popup());
        myStack.show();

        System.out.println("remove = " + myStack.popup());
        myStack.show();
    }
}

/**
 * // stack is full , 不能添加新的元素，栈中元素如下:
 * // 25	55	4
 * // --- --- ---
 * //
 * // stack is full , 不能添加新的元素，栈中元素如下:
 * // 25	55	4
 * // --- --- ---
 * //
 * // stack is full , 不能添加新的元素，栈中元素如下:
 * // 25	55	4
 * // --- --- ---
 * //
 * // stack is full , 不能添加新的元素，栈中元素如下:
 * // 25	55	4
 * // --- --- ---
 * //
 * // stack is full , 不能添加新的元素，栈中元素如下:
 * // 25	55	4
 * // --- --- ---
 * //
 * // stack is full , 不能添加新的元素，栈中元素如下:
 * // 25	55	4
 * // --- --- ---
 * //
 * // 25	55	4
 * // --- --- ---
 * //
 * // get = 4
 * // 25	55	4
 * // --- --- ---
 * //
 * // get = 4
 * // 25	55	4
 * // --- --- ---
 * //
 * // get = 4
 * // 25	55	4
 * // --- --- ---
 * //
 * // mTop = 2
 * // remove = 4
 * // 25	55
 * // --- --- ---
 * //
 * // mTop = 1
 * // remove = 55
 * // 25
 * // --- --- ---
 * //
 * // mTop = 0
 * // remove = 25
 * //
 * // --- --- ---
 * //
 * // 栈为空了，不能pop了
 * // remove = 0
 * //
 * // --- --- ---
 */
