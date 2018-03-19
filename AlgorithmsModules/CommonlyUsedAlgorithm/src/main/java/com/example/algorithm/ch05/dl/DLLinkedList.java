package com.example.algorithm.ch05.dl;


/**
 * Created by example on 2018/3/8.
 * 双向链表
 */

public class DLLinkedList {
    // 头节点
    private DLNode head;
    // 尾节点
    private DLNode last;


    public DLLinkedList() {
    }

    /**
     * 在头节点后插入数据
     */
    public void insertFirst(int value) {
        DLNode node = new DLNode(value);
        if (isEmpty()) {
            last = node;
        } else {
            // 设置当前节点为原头节点的前一个节点
            head.privious = node;
        }
        // 将原头节点指定为当前节点的next
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
        DLNode node = new DLNode(value);
        if (isEmpty()) {
            head = node;
        } else {
            last.next = node;
            // 将last设置为当前节点的前一个节点
            node.privious = last;
        }
        // 将当前节点设置为last节点
        last = node;
    }

    public boolean isEmpty() {
        return head == null;
    }

    /**
     * 在头结节后删除:把头节点后面的第一个元素删除
     */
    public DLNode deleteFirst() {
        DLNode tmp = head;
        if (head.next == null) {
            // 已经是最后一个节点了，没有最后一个节点
            last = null;
        } else {
            // 设置头节点的下一个节点的前一个节点为null
            // 从头删除
            head.next.privious = null;
        }
        // 头节点=需要删除数据的next
        head = tmp.next;
        return tmp;
    }

    /**
     * 从尾部删除:
     * 找到前一个节点，将前一个节点将其next为null
     */
    public DLNode deleteLast() {
        if (head.next == null) {
            // 没有其它节点了头节点设置为null
            head = null;
        } else {
            // 尾节点前一个节点的next为null,设置尾节点为前一个节点
            last.privious.next = null;
        }
        last = last.privious;
        return last;
    }

    /**
     * 根据数据删除节点
     *
     * @param value
     * @return
     */
    public DLNode delete(int value) {
        DLNode current = head;
        while (current.data != value) {
            if (current.next == null) {
                return null;
            }
            // 上一次的节点
            current = current.next;
        }
        if (current == head) {
            System.out.println("current == head");
            // 当前的节点就是head指向的节点的next当前节点就删除了
            // DLNode tmp = head.next;
            head = head.next;
        } else {
            System.out.println("current != head");
            // 前一个节点next指向当前节点的next,删除当前节点
            current.privious.next = current.next;
        }
        return current;
    }

    /**
     * 显示节点
     */
    public void display() {
        DLNode current = head;
        StringBuilder sb = new StringBuilder();
        sb.append("[ ");
        while (current != null) {
            sb.append(current.data).append(",");
            current = current.next;
        }
        if (sb.toString().contains(",")) {
            sb.deleteCharAt(sb.length() - 1);
        }
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
    public DLNode find(int value) {
        DLNode current = head;
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
