package com.example.algorithm.ch04;

/**
 * Created by example on 2018/3/8.
 */

public class TestMySLLinkedList {
    public static void main(String[] args) {
        MySLLinkedList linkedList = new MySLLinkedList();
        /**
         //         插入数据
         //            10      5     7
         //            HEAD
         //                  HEAD
         //                         HEAD
         //
         //        打印数据
         //            HEAD,HEAD,HEAD
         //              7   5   10
         //
         //        移除
         //            HEAD = 7
         */
        linkedList.insertFirst(10);
        linkedList.insertFirst(5);
        linkedList.insertFirst(7);
        linkedList.insertFirst(20);
        linkedList.insertFirst(17);
        linkedList.insertFirst(9);
        linkedList.insertFirst(6);
        linkedList.insertFirst(3);
        linkedList.insertFirst(15);
        linkedList.display();

        System.out.println(" ====== ");
        linkedList.deleteFirst();
        linkedList.display();

        /***
         data = 7 , next =  >> data = 5
         data = 5 , next =  >> data = 10
         data = 10 , next = null
         ======
         data = 7 , next =  >> data = 10
         data = 10 , next = null
         */
        SLNode slNode = linkedList.find(23);
        if (slNode != null) {
            slNode.display();
        } else {
            System.out.println("数据未找到");
        }

        /**
         *
         //         *      0                             1           2
         //         *      CURRENT/HEAD
         //         *
         //         *      HEAD = HEAD.NEXT
         //         *      HEAD = 1 ==>> 0 就被删除掉了
         *
         */
        SLNode slNode1 = linkedList.delete(3);
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
     [ 15,3,6,9,17,20,7,5,10 ]
     ======
     ======
     [ 3,6,9,17,20,7,5,10 ]
     ======
     数据未找到
     current == head
     找到了删除的节点
     data = 3 , next =  SL >> data = 6
     ++++++++++++
     [ 6,9,17,20,7,5,10 ]
     ======
     */
}
