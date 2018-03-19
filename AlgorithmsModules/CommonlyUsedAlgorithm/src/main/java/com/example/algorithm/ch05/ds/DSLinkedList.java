package com.example.algorithm.ch05.ds;

/**
 * Created by example on 2018/3/8.
 * 双端链表
 */

public class DSLinkedList {
    // 头节点
    private DSNode head;
    // 尾节点
    private DSNode last;


    public DSLinkedList() {
    }

    /**
     * 在头节点后插入数据
     */
    public void insertFirst(int value) {
        DSNode node = new DSNode(value);
        if (isEmpty()) {
            last = node;
        }
        node.next = head;
        // 设置头节点为新添加的节点
        head = node;
    }

    /**
     * 从尾节点进行插入
     *
     * @param value
     */
    public void insertLast(int value) {
        DSNode node = new DSNode(value);
        if (isEmpty()) {
            head = node;
        } else {
            last.next = node;
        }
        last = node;
    }

    public boolean isEmpty() {
        return head == null;
    }

    /**
     * 在头结节后删除:把头节点后面的第一个元素删除
     */
    public DSNode deleteFirst() {
        DSNode tmp = head;
        if (tmp.next == null) {
            // 已经是最后一个节点了，没有最后一个节点
            last = null;
        }
        // 头节点=需要删除数据的next
        head = tmp.next;
        return tmp;
    }

    /**
     * 根据数据删除节点
     *
     * @param value
     * @return
     */
    public DSNode delete(int value) {
        DSNode current = head;
        DSNode pre = head;
        while (current.data != value) {
            if (current.next == null) {
                return null;
            }
            // 上一次的节点
            pre = current;
            current = current.next;
        }
        if (current == head) {
            System.out.println("current == head");
            // 当前的节点就是head指向的节点的next当前节点就删除了
            // DSNode tmp = head.next;
            head = head.next;
        } else {
            System.out.println("current != head");
            // 前一个节点next指向当前节点的next,删除当前节点
            pre.next = current.next;
        }
        return current;
    }

    /**
     * 显示节点
     */
    public void display() {
        DSNode current = head;
        StringBuilder sb = new StringBuilder();
        sb.append("[ ");
        while (current != null) {
            sb.append(current.data).append(",");
            current = current.next;
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append(" ]");
        System.out.println(sb.toString());
        System.out.println(" ====== ");
    }

    /**
     * 根据数据查找节点
     *
     * @param value
     * @return
     */
    public DSNode find(int value) {
        DSNode current = head;
        while (current.data != value) {
            // next为null表示查找到了最后一个元素，代表查找不到了
            if (current.next == null) {
                return null;
            }
            current = current.next;
        }
        return current;
    }
}
