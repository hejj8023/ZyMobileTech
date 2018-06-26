# 1章 课程介绍 #
## 本课程首先探讨2个压力测试问题： 1、为什么要开展压力测试？ 2、什么时候开展压力测试？ 然后讲解7个压力测试必要基础知识、 最后实战6个压力测试小案例, 从而教会大家对任意一款Android App进行压力测试。最近学习 ##

1-1 Android App压力测试课程介绍 (05:40)

# 2章 2个背景问题 #

## 本章探讨2个压力测试问题： 1、为什么要开展压力测试？ 2、什么时候开展压力测试？ ##
2-1 【为什么要开展压力测试】与【什么时候开展压力测试】 (03:22)

> 为什么要开展压力测试

	- 提高产品的稳定性
	- 提高产品的留存率

> 什么时候开展压力测试

	首轮功能测试通过后
    下班后夜间进行


# 3章 7个基础知识 #

## 本章讲解7个压力测试必要基础知识： 1、手工测试场景 2、自动测试场景 3、Monkey工具 4、ADB工具 5、Monkey Script（下一章具体介绍） 6、MonkeyRunner（下一章具体介绍） 7、压力测试异常结果分析（CRASH，ANR） ##

-  3-1 【手工测试场景】与【自动测试场景】 (03:04)
	### 手工测试场景 ###
		查找-》添加好友-》聊天

	### 自动测试场景模拟事件流 ###
		查找 TouchInput -> KeyEvent
		 |					   |
		 V					   V
  		添加 TouchName  <- TouchSearch				
		 |		|
		 V		V
		聊天 TouchFriend  -> KeyEvent														

-  3-3 MonkeyScript测试命令集合介绍 (02:32)
### MonkeyScript ###
	MonkeyScript是一组可以被Monkey识别的命令集合
	MonkeyScript可以完成重复固定的操作

### MonkeyRunner ###
	MonkeyRunner提供了一系列的API
	MonkeyRunner可以完成模拟事件及截图操作

### 两者区别	 ###
	Monkey:在adb shell中，生成用户或系统的伪随机事件
	MonkeyRunner:通过API定义特定命令和事件控件设备

### MonkeyRunner APIS ###
	MonkeyRunner : 用来连接设备或模拟器
	MonkeyDevices:提供安装、卸载应用，发送模拟事件
	MonkeyImage： 完成图像保存，及对比的操作
-  3-4 异常测试结果分析 (02:16)

# 4章 6个实践小案例 #

本章实战6个压力测试小案例： 1、一个App的压力测试实战案例 2、Monkey高级参数的应用案例 3、CRASH结果析取案例 4、ANR结果析取案例 5、Monkey Script实战案例 6、MonkeyRunner实战案例

-  4-1 一次Android App的压测实践 (14:37)
-  4-2 Monkey高级参数-throttle实践 (02:28)
-  4-3 Monkey高级参数-seed实践 (04:36)
-  4-4 Monkey高级参数-触摸事件实践 (03:34)
-  4-5 Monkey高级参数-其他事件实践上 (04:10)
-  4-6 Monkey高级参数-其他事件实践下 (02:22)
-  4-7 CRASH异常测试结果析取 (09:38)
-  4-8 ANR异常测试结果析取 (06:36)
-  4-9 MonkeyScript常用命令介绍 (06:51)
-  4-10 MonkeyScript实践 (18:04)
-  4-11 MonkeyScript实践问题解决 (08:23)
-  4-12 MonkeyRunner常用API介绍上 (04:45)
-  4-13 MonkeyRunner常用API介绍下 (04:55)
-  4-14 MonkeyRunner实践 (06:30)
-  4-15 MonkeyRunner补充说明 (00:52)