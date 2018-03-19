# java常用算法
 
##  数组
### 数组巩固
 创建数组
 访问数组数据项
 数组的初始化
 
### 面向对象的编程方式
使用自定义类封装数组
添加类方法来实现数据操作

### 示例
#### 插入数据到数组中的指定位置
    public void insert(int index, int value) {
     if (index >= elements || index < 0) {
         throw new ArrayIndexOutOfBoundsException();
     } else {
         // 从后面往前面操作，因为所有的元素都要后移
         for (int i = elements; i > index; i--) {
             arrs[i] = arrs[i - 1];
         }
         arrs[index] = value;
         elements++;
     }
    }
    
### 有序数组
数组中的数据是有序的

> - 有序数组简介以及其优点
> - 构建有序数组

#### 示例: 数据插入

    public void insert(long value) {
        int i;
        for (i = 0; i < elements; i++) {
            if (arrs[i] > value) {
                break;
            }
        }
    
        // 从后面往前面操作，因为所有的元素都要后移
        for (int j = elements; j > i; j--) {
            arrs[j] = arrs[j - 1];
        }
        arrs[i] = value;
        elements++;
    }


### 查找算法
> - 线性查找

    从头到尾，就是线性查找

> - 二分查找
    
    前提条件，数组必须是有序的数组
    
#### 示例  二分查找
 
    public long binarySearch(long value) {
     int middle = 0;
     int min = 0;
     int max = elements;
    
     while (true) {
         middle = (max + min) / 2;
         System.out.println("middle = " + middle + " , min = " + min + " , max = " + max);
         if (arrs[middle] == value) {
             return middle;
         } else if (min > max) {
             return -1;
         } else {
             // 往左边走
             if (arrs[middle] > value) {
                 max = middle - 1;
             } else {
                 // 往右边走
                 min = middle + 1;
             }
         }
     }
    }


 