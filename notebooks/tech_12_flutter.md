资料

https://www.jianshu.com/p/f7d9bdaa3a9a

# Flutter 配置在 Android Studio #

## Flutter与Dart SDK下载与环境配置 ##

- 打开"Git Bash" 输入
    
		git clone -b beta https://github.com/flutter/flutter.git ；


- 配置环境变量（根据你flutter存储的文件路径）。

![](https://upload-images.jianshu.io/upload_images/10018045-68d282a98b395a4e.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/394)


- 打开系统的“cmd” ,输入   flutter doctor ;  （ 注意：flutter 开发语言是Dart，所以在这里他会去监测你是否有dart sdk，如果没有他会自动帮你安装，并且捆绑在flutter文件夹里面。）             

![](https://upload-images.jianshu.io/upload_images/10018045-5d7c9d4afc63c00a.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/677)

## 第五步：配置Flutter和Dart插件  ##
使用Android Studio 开发Flutter项目，需要配置必要的两个插件Flutter和Dart。Dart用与代码的分析，Flutter用于工程的编译等。 
配置方法如下： 

- ①Android Studio欢迎页面:Configure>Settings>Plugins 
- ②Android Studio项目打开页面：File>Settings>Plugins。 

在搜索框分别搜索Flutter和Dart，并安装。 截图如下：

![](https://img-blog.csdn.net/20180322152306471?watermark/2/text/Ly9ibG9nLmNzZG4ubmV0L2hud3g3ODgw/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70)

![](https://img-blog.csdn.net/20180322152306471?watermark/2/text/Ly9ibG9nLmNzZG4ubmV0L2hud3g3ODgw/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70)

![](https://img-blog.csdn.net/20180325214407422?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2hud3g3ODgw/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70)

## Flutter导入Android Studio: ##

-  打开Android studio 的 Settings
![](https://upload-images.jianshu.io/upload_images/10018045-e7e1e161e8184b57.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/700)


- 设置Flutter路径完毕之后，点击” OK “，然后他会弹出一个窗口问你是否 ” 把Dart的路径也自动设置上 “，选择 “ yes ”就可以，然后就打开Settings看下Dart的路径是否已经被设置好。

![](https://upload-images.jianshu.io/upload_images/10018045-f1856d73ef0660e6.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/700)

- 然后就可以开始Flutter的学习之旅了。
![](https://upload-images.jianshu.io/upload_images/10018045-201d8e92fdc3b3f1.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/700)




## 问题解决方案收集 ##

1. 创建flutter项目失败:Got socket error trying to find package at http://pub.dartlang.org

配置本地环境方法 

	对于 Linux 和 MacOS 来说，添加如下两条环境变量即可： 
	export PUB_HOSTED_URL=https://pub.flutter-io.cn 
	export FLUTTER_STORAGE_BASE_URL=https://storage.flutter-io.cn

![](https://img-blog.csdn.net/20180318141504460?watermark/2/text/Ly9ibG9nLmNzZG4ubmV0L2hud3g3ODgw/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70)