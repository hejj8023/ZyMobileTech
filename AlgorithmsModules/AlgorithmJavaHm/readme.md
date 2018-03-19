# 算法

## 01_算法概述和关键指标

  计算的方法:在进行某种运算时所使用的方式方法
  计算机中的算法:在计算机中进行运算时为计算机提前设定的运算方式
  
  两个关键指标:
  
    A:运行时间
    B:内存消耗
    
    空间换时间,时间换空间
    
    
## 02_课程的预备知识
- A: 异或运算符
       ^ 同一个数两次，结果为同一个数
       
- B: System中获取ns值
   public static long nanotime();
     返回精确的可用系统计时器的当前值
   
   单位换算:
   
     1s = 1000ms
     1ms = 1000us
     1us = 1000ns
     
     1s = 10 000 000 000 ns
     
   使用场景
       一段程序的前后，得到再次的时间相减，就是该程序的运行所消耗的时间
       
- C: if...,if....else...
- D: 循环语句for...while
- E: 数组
- F: 递归
       
       
       
### 题目一 找数组中的重复元素

#### 方式一、思路:
    
- 使用嵌套循环，拿数组中的元素，依次和后面的元素进行比较，找到就结束

 >
    arr = {1,2,3,5,3}
    o:0-> arr.len -2 ( 少比较一次)
    i:oI+1 -> arr.len
     for(int i=0;i<=arr.len-2;i++){  // 优化的代码的处理
         for(int j=i+1;j<=arr.len-1;j++){
             if(arr[i] == arr[j]{
                 break;
             }
         }
     } 
     
>                 
    Sample1 sample01 = new Sample1();
    sample01.run(new int[]{15, 33, 22, 17, 9, 12, 15, 22, 27, 88, 100, 44, 9});
    
           
    public class Sample1 {
        public void run(int[] arr) {
            if (arr == null)
                return;
    
            if (arr.length == 0)
                return;
    
            Map<Integer, String> dupleList = new HashMap<>();
            for (int i = 0; i <= arr.length - 2; i++) {
                int def = arr[i];
                for (int j = i + 1; j <= arr.length - 1; j++) {
                    if (def == arr[j]) {
                        //  System.out.println("相同的元素: " + def);
                        dupleList.put(i, "index ( i = " + i + " & j = " + j + " ) , value = " +
                                arr[i]);
                        break;
                    }
                }
            }
            int dupleCount = dupleList.size();
            System.out.println("相同的元素个数: " + dupleCount);
            for (Map.Entry<Integer, String> entry : dupleList.entrySet()) {
                System.out.println(entry.getValue());
            }
    
        }
    }  
         
虽然可以实现功能，但是效率每低
         
#### 方式二、   思路:

- 1.把数组中所有元素的值累加起来
- 2.用这个和减去0-9的数据,最终的结果就是重复的元素(前提:0-9元素不重复)
 
>     
     private static void algor02(int[] arrs) {
         long sTime = System.nanoTime();
         int sum = 0;
         for (int i = 0; i < arrs.length; i++) {
             sum += arrs[i];
         }
 
         for (int i = 0; i < 100; i++) {
             sum -= arrs[i];
         }
 
         System.out.println(" sum = " + sum);
         long eTime = System.nanoTime();
         System.out.println(" algor02 costtime = " + (eTime - sTime));
     }
  
    数据元素比较多，数据值比较大，会造成数据的溢出
     
两种算法的对比:

  algor01 costtime = 127347
  
  algor02 costtime = 20850(缩短的5倍的时间)
  
#### 方法三、思路(最适合的方案，解决了方案二溢出的问题,算法是要选择最合适的算法)  
- 1.拿数组中0索引的元素和所有的元素^
- 2.使用^的结果和数组中的所有的元素再^
- 3.最终保留的结果就是重复元素


    private static void algor03(int[] arrs) {
      long sTime = System.nanoTime();
      for (int i = 1; i < arrs.length; i++) {
          arrs[0] ^= arrs[i];
      }
      System.out.println(" arrs[0] = " + arrs[0]);
    
      for (int i = 0; i < 100; i++) {
          arrs[0] ^= i;
      }
      // 以时间换空间的算法
      System.out.println(" algor03 重复的数据 = " + arrs[0]);
      long eTime = System.nanoTime();
      System.out.println(" algor03 costtime = " + (eTime - sTime));
    }
    
### 题目二:

      现有0到99，共计100个整数，各不相同，将这100个数据放入一个数组中随机排列。
      将其中任意一个整数替换成0到99另一个数(唯一重复的数字)
      问题:
        将这个重复的数字找出来
    
####  方案一、依次查找
  
      private void algor1(int[] arrs, ICostCalc iCostCalc) {
          iCostCalc.start("algor1");
          for (int i = 0; i < arrs.length - 1; i++) {
              for (int j = i + 1; j < arrs.length; j++) {
                  if (arrs[i] == arrs[j]) {
                      System.out.println("重复的元素为:" + arrs[i] + " s index = " + i + " , e index = " + j);
                  }
              }
          }
          iCostCalc.end();
      }
    
#### 方案二、以空间换时间

- 思路:创建一个新数组，遍历旧数组，拿旧数组中的元素作为新数组的索引，并把索引处的值+1

    
    private void algor2(int[] arrs, DefaultCostCalc costCalc) {
        costCalc.start("algor2");
        int[] nArrs = new int[100];
        for (int i = 0; i < arrs.length; i++) {
            nArrs[arrs[i]]++;
            if (nArrs[arrs[i]] > 1) {
                System.out.println(" 重复的元素为:" + arrs[i]);
                break;
            }
        }
        costCalc.end();
    }
    
### 以时间换空间

案例:交换两个数的位置

    public class Sample04 {
        public void run() {
            DefaultCostCalc costCalc = new DefaultCostCalc();
            for (int i = 0; i < 1000; i++) {
                int m = 10;
                int n = 20;
    
                // 空间换时间 : costtime = 2566 纳秒
                algorith01(m, n, costCalc);
    
                // 时间换空间 : costtime = 8661 纳秒
                algorith02(costCalc, m, n);
            }
        }
    
        private void algorith02(DefaultCostCalc costCalc, int i, int j) {
            costCalc.start("时间换空间 :");
            i = i ^ j; // i = i ^ j
            System.out.println("i = " + i + " , j = " + j);
            j = i ^ j; // j = (i ^ j) ^ j = i
            System.out.println("i = " + i + " , j = " + j);
            i = i ^ j; // i = i ^ j = (i ^ j) ^ i = j
            System.out.println("i = " + i + " , j = " + j);
            costCalc.end();
        }
    
        private void algorith01(int i, int j, DefaultCostCalc costCalc) {
            costCalc.start("空间换时间 :");
            int temp = i;
            i = j;
            j = temp;
            System.out.println("i = " + i + " , j = " + j);
            costCalc.end();
        }
    }    
    
结论:
  
  空间换时间 : costtime = 2566 纳秒
  
  时间换空间 : costtime = 8661 纳秒
    
- 以空间换时间(内存大可以采用)
    就是定义新的变量，以达到优化程序的效率
    空间增加了，但是效率却提高了
    
- 以时间换空间(内存小可以使用)
    就是减少变量的定义，以达到优化程序的效果(节省内存,空间减少了但是时间却变长了)
    
    
##  裴波那契数列
  1,1,2,3,5,8,13,21,34
  
 求第30个位置的元素的数据

 规律：

 从第3个开始，每一个数据都是前两个数据的和
 第1个数据和第2个数据的值是1    

 - 递归实现
 
 
        private int getNumber(int m) {
             count++;
             // 指的是位置
             if (m == 1 || m == 2) {
                 return 1;
             }
             int number = getNumber(m - 1);
             int number2 = getNumber(m - 2);
             int result = number + number2;
             System.out.println("m = " + m + " , n1 = " + number + " , n2 = " + number2);
             return result;
         }
        
- 备忘录算法实现:使用缓存，减少同参数的函数的调用次数


    private static HashMap<Integer, Integer> cache = new HashMap<>();
    
    private int getNumber2(int m) {
        count++;
        // 指的是位置
        if (m == 1 || m == 2) {
            return 1;
        }
    
        if (cache.containsKey(m)) {
            return cache.get(m);
        } else {
            int result = getNumber2(m - 1) + getNumber2(m - 2);
            cache.put(m, result);
            return result;
        }
    }
  
- 动态规划:
     使用了自下往上的递归方式，实现了时间和空间的最优化
     
     
    private int getNumber3(int m) {
        count++;
        int f1 = 1, f2 = 1;
        int temp = 0;
        for (int i = 3; i <= m; i++) {
         System.out.println(" s -> i = " + i + " , f1 = " + f1 + " , f2 = " + f2 + " , temp = " +
                 "" + temp);
         temp = f1 + f2;
         f1 = f2;
         f2 = temp;
         System.out.println(" e -> i = " + i + " , f1 = " + f1 + " , f2 = " + f2 + " , temp = " +
                 "" + temp);
        }
        return temp;
    }
    
    
##### 两种算法的对比:
    
    递归实现: costtime = 4409374518 纳秒
    调用次数:1664079
    
    备忘录实现 costtime = 120612 纳秒
    调用次数:57
        
    动态规划实现 costtime = 7378 纳秒
    调用次数:1
            

## 其它扩展:
###
    题设：某门户网站，具有如下业务功能
    客户输入个人信息时，当输入年龄，会根据输入的年龄值
    显示其所属年龄段
    0 ～  9 儿童
    10 ～ 19 少年
    20 ～ 29 青年
    30 ～ 39 青壮年
    40 ～ 49 壮年
    50 ～ 59 中年
    60 ～ 69 中老年
    70 ～ 79 老年
    80 ～ 89 老老年
    90 ～ 99 老老老年
    问题：上述业务日均访问量超百万次，设计完成上述功能的程序
    百万次访问次数多，要求速度最快，一定是速度优先，时间尽量少
    
    private void al03(int num, DefaultCostCalc costCalc) {
        costCalc.start("al03");
        String[] sexs = {
                "儿童", "少年", "青年", "青壮年", "壮年", "中年",
                "中老年", "老年", "老老年", "老老老年"
        };
        costCalc.end();
        System.out.println(sexs[num / 10]);
    }

    private void al01(int age, DefaultCostCalc costCalc) {
        costCalc.start("al01");
        String type = "";
        if (age < 0 && age < 10) {
            type = "儿童";
        } else if (age <= 10 && age <= 19) {
            type = "少年";
        } else if (age <= 20 && age <= 29) {
            type = "青年";
        } else if (age <= 30 && age <= 39) {
            type = "青壮年";
        } else if (age <= 40 && age <= 49) {
            type = "壮年";
        } else if (age <= 50 && age <= 59) {
            type = "中年";
        } else if (age <= 60 && age <= 69) {
            type = "中老年";
        } else if (age <= 70 && age <= 79) {
            type = "老年";
        } else if (age <= 80 && age <= 89) {
            type = "老老年";
        } else if (age <= 90 && age <= 99) {
            type = "老老老年";
        }
        System.out.println(" type = " + type);
        costCalc.end();
    }

    private void al02(int age, DefaultCostCalc costCalc) {
        costCalc.start("al02");
        String type = "";
        switch (age / 10) {
            case 0:
                type = "儿童";
                break;

            case 1:
                type = "少年";
                break;
            case 2:
                type = "青年";
                break;
            case 3:
                type = "青壮年";
                break;
            case 4:
                type = "壮年";
                break;
            case 5:
                type = "中年";
                break;
            case 6:
                type = "中老年";
                break;
            case 7:
                type = "老年";
                break;
            case 8:
                type = "老老年";
                break;
            case 9:
                type = "老老老年";
                break;
        }
        System.out.println(" type = " + type);
        costCalc.end();
    }
  
### 算法性能对比    
    type = 青壮年
    al01 costtime = 264319 纳秒
    
    type = 青壮年
    al02 costtime = 18925 纳秒
    
    al03 costtime = 6416 纳秒
    青壮年
    
###  求1000阶乘的结果末尾有多少个0
    
解题思路
两个素数2、5，相乘即可得到10，我们可以认为，有多少组2、5，结尾就有多少个0
操作方法：操作1到1000中所有的数，看每个数能被2和5整除几次，并分别统计，
假设被2整除8次，被5整除12次，那我们可以认为有8组（2，5），即8个0    


    
### 爬楼梯
有一座高度是10级台阶的楼梯，从下往上走，每跨一步只能向上1级或者2级台阶。要求用程序来求出一共有多少种走法。
动态规划的思想：dynamic programming，一种分阶段求解决策问题的数学思想。即大事化小，小事化了。

分两部分：问题建模和求解问题。

（1）问题建模
上述问题假设只差一步走到第10级，则F(10)=F(9)+F(8)。（最优子结构）


我们归纳出的公式如下：

F(1)=1;

F(2)=1;（边界）

.........
F(n)=F(n-1)+F(n-2);（状态转移公式）

动态规划中包含三个重要的概念：最优子结构、边界和状态转移公式。

（2）求解问题

方法1：递归算法
    
    private int getClimbingWays3(int n) {
        if (n < 0) {
            return 0;
        }
    
        if (n == 1) {
            return 1;
        }
    
        if (n == 2) {
            return 2;
        }
    
        int a = 1;
        int b = 2;
        int temp = 0;
        for (int i = 0; i <= n; i++) {
            temp = a + b;
            a = b;
            b = temp;
        }
    
        return temp;
    }
    
    HashMap<Integer, Integer> cache = new HashMap<Integer, Integer>();
    
    private int getClimbingWays2(int n) {
        if (n < 0) {
            return 0;
        }
    
        if (n == 1) {
            return 1;
        }
    
        if (n == 2) {
            return 2;
        }
    
        if (cache.containsKey(n)) {
            return cache.get(n);
        } else {
            int result = getClimbingWays2(n - 1) + getClimbingWays2(n - 2);
            cache.put(n, result);
            return result;
        }
    }
    
    private int getClimbingWays(int n) {
        if (n < 0) {
            return 0;
        }
    
        if (n == 1) {
            return 1;
        }
    
        if (n == 2) {
            return 2;
        }
        return getClimbingWays(n - 1) + getClimbingWays(n - 2);
    }