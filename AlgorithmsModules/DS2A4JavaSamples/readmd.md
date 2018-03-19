# 数据结构和算法 
  不会涉及到代码，只是分析java中的类，暂放

# 线性表

## 以计算机对弈（下五子棋）为例
> - 算法: 对弈的规则和策略
> - 模型: 棋盘棋子的表示
> - 数据结构: 数据之间相互存在的一种或多种特定的关系的元素的集合
 
## 数据结构分类
### 按照数据元素及其相互关系的表现形式划分:

#### 1. 逻辑结构
数据结构的逻辑层面

逻辑结构按数据元素之间相互关系的特征来分  
           
-  1.集合结构
-  2.线性结构:
       元素之间为一对一关系,呈线性结构，可以连接成一条直线
       
-  3.树形结构
       元素之间为一对多关系
-  4.图形结构
         元素之间为多对多关系 

#### 线性结构和非线性结构
- 线性结构：线性表、栈、队列
- 非线性结构:树、图 
             
#### 2.存储结构(物理结构)
   存在于计算机世界的物理层面
   包括数据元素本身的存储以及数据元素之间的关系表示
   数据元素之间的关系在计算机中主要的表示方法:
     顺序映像和非顺序映像
     
-  顺序存储结构(数组 array,list,set)
   数据元素的存储对应于一块连续的存储空间，数据元素之间的前驱
   和后续关系通过数据元素在存储器中的相对位置来反映
   
   元素增加时当前元素后面所有的元素要往后移，删除元素时，当前元素后面的元素要往前移

-  链式存储结构(链表,linkedlist)
     数据元素的存储对应的是不连续的存储空间，每个存储节点对应一个
     需要存储的数据元素。元素之间的逻辑关系通过存储节点的链式关系反映

 
## 线性表List
### 定义   
   一对一的关系
    
    a1->a2 ... ai-1->ai,ai+1...an
    a1是a2的前驱,ai+1是ai的后继,a1没有前驱,an没有后继

### 存储方式

- 顺序存储
  存储位置连续，可以很方便的计算各个元素的地址
  在做增删操作的时候比较复杂，指针要往前移或后移

#### java中的顺序存储 
     数组:
     
     ArrayList:java.util.ArrayList
        arraylist就其于数组实现的
        public ArrayList(int initialCapacity) {
           super();
           this.elementData = new Object[initialCapacity];
        }
     
     add(E e)添加数据
         D:/programs/as/as3x/jre/jre/lib/rt.jar!/java/util/ArrayList.class:97
         private void grow(int var1) {
             int var2 = this.elementData.length;
             int var3 = var2 + (var2 >> 1);
             if(var3 - var1 < 0) {
                 var3 = var1;
             }
     
             // 之前的数组是空的所以这里使用的是arrays.copyof不会影响到原来的数组
             this.elementData = Arrays.copyOf(this.elementData, var3);
         }
         
         java.util.AbstractList.modCount 计量器
         java.util.ArrayList.size 集合的长度 
         
     1. 判断数组的容量
     2. 数据中的元素赋值
     
- 链式存储
  LinkedList
  
### Arrays.copyOf

    public static <T,U> T[] copyOf(U[] original, int newLength, Class<? extends T[]> newType) {
      T[] copy = ((Object)newType == (Object)Object[].class)
          ? (T[]) new Object[newLength]
          : (T[]) Array.newInstance(newType.getComponentType(), newLength);
      System.arraycopy(original, 0, copy, 0,
                       Math.min(original.length, newLength));
      return copy;
    }
    
## 链式存储-线性表
用一组任意的存储单元存储线性表的数据元素，这组存储单元可以是连续的也可以是不连续的 

 插入和删除效果高，查询效率低
 
 数据结构
 [{数据}{下一个数据的地址}]
 
 LinkedList 是双向链表
 
    private static class Node<E> {
         E item; // 数据
         Node<E> next; // 下一个元素
         Node<E> prev; // 前一个元素
    }

## Lru算法：使用的是就是LinkedHashMap(双向链表)

## 双向循环链表
单身循环链表的每个结点中，再设置一个指向其前驱结点的指针域
数据在前一半使用正向查找，在后一半使用反射查找