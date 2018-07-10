#### 资料地址
https://www.imooc.com/learn/879


# 1章 使用Proguard混淆android代码 #
通过Proguard使反编译之后的smali代码变得难懂
最近学习
##  1-1 使用Proguard混淆android代码 (10:09) ##

-  保留类名
-  保留方法名
-  保留类名和方法名

### 混淆为什么要保留类名和方法名了 ###

- 让C/C++程序可以通过jni使用对应的java方法
- 四大组件由于在AndroidManifest.xml里面注册了，所以需要保留
- R文件混淆会导致引用错误

# 2章 使用Proguard去除日志信息 #
通过Proguard去除log，防止敏感信息的泄露
 ## 2-1 使用Proguard去除日志信息 (05:35) ##
通过Proguard，将andorid.util.Log类的方法置为无效代码，可以去除apk中打印日志的代码

不输出log的两种方式
1. 封装一个log wrapper,修改控制变量
2. 直接删除打印log代码


注意事项:
打开优化开关

默认使用proguard-android-optimize.txt

# 3章 对抗反编译工具 #
最常用的反编译工具是apktool，有效的防止被apktool反编译可以达到保护代码的目的
##  3-1 对抗反编译工具 (04:18) ##

# 4章 对抗安卓模拟器 #
用户正常会运行在真机中，运行在模拟器中的应用多半处于被破解状态。一旦监测到app运行在模拟器状态，直接退出应用。
##  4-1 对抗安卓模拟器 (05:51) ##

# 5章 对抗apk重打包 #
如果app被重新打包，那么一定是被破解了，一旦监测到app被重新打包，直接退出应用。
##  5-1 对抗apk重打包 (03:49) ##

# 6章 如何使用IDA调试so #
如何使用IDA，IDA Native层调试so
##  6-1 如何使用IDA调试so(上） (15:32) ##
##  6-2 如何使用IDA调试so(下） (03:12) ##
##  6-3 如何对抗IDA Native层调试 (05:13) ##