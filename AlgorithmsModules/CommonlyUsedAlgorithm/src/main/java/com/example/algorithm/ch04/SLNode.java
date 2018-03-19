package com.example.algorithm.ch04;

/**
 * Created by example on 2018/3/8.
 * 单链表-节点
 */

public class SLNode {
    // 数据
    public int data;

    // next 节点的指针
    public SLNode next;

    public SLNode() {
    }

    public SLNode(int value, SLNode next) {
        this.data = value;
        this.next = next;
    }

    public SLNode(int value) {
        this.data = value;
    }

    public void display() {
        System.out.println("data = " + data + " , next = " + next);
    }

    @Override
    public String toString() {
        return " SL >> data = " + data;
    }
}
