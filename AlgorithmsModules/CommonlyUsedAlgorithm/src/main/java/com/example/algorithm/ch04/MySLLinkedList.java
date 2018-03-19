package com.example.algorithm.ch04;

/**
 * Created by example on 2018/3/8.
 * 单向链表
 */

public class MySLLinkedList {
    // 头节点,头结点没有next
    private SLNode head;

    public MySLLinkedList() {
    }

    /**
     * 在头节点后插入数据
     */
    public void insertFirst(int value) {
        SLNode node = new SLNode(value);
        node.next = head;
        // 设置头节点为新添加的节点
        head = node;
    }

    /**
     * 在头结节后删除:把头节点后面的第一个元素删除
     */
    public SLNode deleteFirst() {
        SLNode tmp = head;
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
    public SLNode delete(int value) {
        SLNode current = head;
        SLNode pre = head;
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
            // SLNode tmp = head.next;
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
     * 循环整个链表，进行显示，如果next没有了，就结束了,不再遍历
     */
    public void display() {
        SLNode current = head;
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
    public SLNode find(int value) {
        SLNode current = head;
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
