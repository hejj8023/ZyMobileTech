package com.example.algorithm.ch05.ds;

/**
 * Created by example on 2018/3/8.
 */

public class DSNode {
    public int data;
    public DSNode next;

    public DSNode(int data) {
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
