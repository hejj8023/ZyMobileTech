package com.example.algorithm.ch05;

import com.example.algorithm.ch05.dl.DLLinkedList;
import com.example.algorithm.ch05.ds.DSLinkedList;
import com.example.algorithm.ch05.ds.DSNode;

/**
 * Created by example on 2018/3/8.
 */

public class TestMyLinkedList {
    public static void main(String[] args) {
        // testDsLinkedlist();
        testDLLinkedlist();
    }

    private static void testDLLinkedlist() {
        DLLinkedList linkedList = new DLLinkedList();
        linkedList.insertLast(45);
        linkedList.insertLast(56);
        linkedList.insertLast(50);
        linkedList.display();

        while (!linkedList.isEmpty()) {
            linkedList.deleteLast();
            linkedList.display();
        }

        /**
         deleteFirst
         //         [ 45,56,50 ]
         //         ======
         //         [ 56,50 ]
         //         ======
         //         [ 50 ]
         //         ======
         //         [  ]
         //         ======


         deleteLast
         //         [ 45,56,50 ]
         //         ======
         //         [ 45,56 ]
         //         ======
         //         [ 45 ]
         //         ======
         //         [  ]
         */

    }

    private static void testDsLinkedlist() {
        DSLinkedList linkedList = new DSLinkedList();
        linkedList.insertFirst(10);
        linkedList.insertFirst(5);
        linkedList.insertFirst(7);
        linkedList.insertFirst(20);
        linkedList.insertFirst(17);
        linkedList.insertFirst(9);
        linkedList.insertFirst(6);
        linkedList.insertFirst(3);
        linkedList.insertFirst(15);

        linkedList.insertLast(100);
        linkedList.insertLast(107);
        linkedList.insertLast(109);
        linkedList.insertLast(120);


        // [15,3,6,9,17,20,7,5,10, 100 , 107,109,120]
        linkedList.display();

        System.out.println(" ====== ");
        System.out.println("删除头节点");
        linkedList.deleteFirst();
        linkedList.display();

        System.out.println(" ====== ");

        DSNode slNode = linkedList.find(23);
        if (slNode != null) {
            slNode.display();
        } else {
            System.out.println("数据未找到");
        }

        DSNode slNode1 = linkedList.delete(3);
        if (slNode1 != null) {
            System.out.println("找到了删除的节点");
            slNode1.display();
        } else {
            System.out.println("数据未找到");
        }

        System.out.println(" ++++++++++++ ");
        linkedList.display();
    }

    /**
     [ 15,3,6,9,17,20,7,5,10,100,107,109,120 ]
     ======
     ======
     删除头节点
     [ 3,6,9,17,20,7,5,10,100,107,109,120 ]
     ======
     ======
     数据未找到
     current == head
     找到了删除的节点
     data = 3 , next =  DL >> data = 6 ++++++++++++
     [ 6,9,17,20,7,5,10,100,107,109,120 ]
     ======
     */
}
