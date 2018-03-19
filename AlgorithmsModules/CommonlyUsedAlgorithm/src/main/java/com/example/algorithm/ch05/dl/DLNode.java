package com.example.algorithm.ch05.dl;

/**
 * Created by example on 2018/3/8.
 */

public class DLNode {
    // 数据
    public int data;
    // 下一个节点
    public DLNode next;

    // 前一个节点
    public DLNode privious;

    public DLNode(int data) {
        this.data = data;
    }

    public void display() {
        System.out.print("data = " + data + " , next = " + next);
    }

    @Override
    public String toString() {
        return " DL >> data = " + data;
    }
}
