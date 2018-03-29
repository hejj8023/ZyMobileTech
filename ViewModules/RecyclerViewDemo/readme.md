# constraintlayout的使用

#RecyclerView添加分割线的简便方法

##添加Android自带的分割线

    recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
    
    
## 设置自定义颜色

    <?xml version="1.0" encoding="utf-8"?>
    <shape xmlns:android="http://schemas.android.com/apk/res/android"
        android:shape="rectangle">
        <gradient
            android:centerColor="#0000ff"
            android:endColor="#00ff00"
            android:startColor="#ff0000"
            android:type="linear" />
        <size android:height="3dp" />
    </shape>
    
    defaultItemDecoration = new DividerItemDecoration(mContext, orientation);
    defaultItemDecoration.setDrawable(UiUtils.getDrawable(mContext, dividerResId));
    recyclerView.addItemDecoration(defaultItemDecoration);
    
## 万能分割线
    
    https://blog.csdn.net/zxwd2015/article/details/53115377
    
    
    
# RecyclerViewPager(Recyclerview实现ViewPager效果)

    参考资料https://github.com/lsjwzh/RecyclerViewPager
    
    repositories {
        ...
        maven { url "https://jitpack.io" }
        ...
    }
    
    dependencies {
        ...
        compile 'com.github.lsjwzh.RecyclerViewPager:lib:v1.1.2@aar'
        ...
    }
    
# 分类效果
   参考资料
    https://github.com/FJ917/FJMtSortButton
    
#  FJEditTextCount
    https://github.com/FJ917/FJEditTextCount
    
    
# 未研究的项目
   https://github.com/sfsheng0322/StickyHeaderListView
   https://github.com/Jerome-MJ/CategoryList
   
   仿照qq列表，分类悬浮
   https://github.com/SolveBugs/ExpandableListviewDemo
   
   
   多种下拉刷新效果、上拉加载更多、可配置自定义头部广告位
   https://github.com/bingoogolapple/BGARefreshLayout-Android
   
   在 AdapterView 和 RecyclerView 中通用的 Adapter 和 ViewHolder。RecyclerView 支持 DataBinding 、多种 Item 类型、添加 Header 和 Footer。RecyclerView 竖直方向通用分割线 BGADivider
   https://github.com/bingoogolapple/BGABaseAdapter-Android
    implementation 'com.android.support:recyclerview-v7:latestVersion'
       implementation 'cn.bingoogolapple:bga-baseadapter:latestVersion@aar'
       
   Android Activity 滑动返回。支持微信滑动返回样式、横屏滑动返回、全屏滑动返回    
   https://github.com/bingoogolapple/BGASwipeBackLayout-Android
   
   
   RxJava + Retrofit 下载新版 apk 文件，RxBus 监听下载进度
   https://github.com/bingoogolapple/BGAUpdate-Android
       
       
引导界面滑动导航 + 大于等于1页时无限轮播 + 各种切换动画轮播效果       
   https://github.com/bingoogolapple/BGABanner-Android   
       
 QRCode 扫描二维码、扫描条形码、相册获取图片后识别、生成带 Logo 二维码、支持微博微信 QQ 二维码扫描样式      
https://github.com/bingoogolapple/BGAQRCode-Android       

Android 图片选择、预览、九宫格图片控件、拖拽排序九宫格图片控件
https://github.com/bingoogolapple/BGAPhotoPicker-Android

带百分比数字的水平、圆形进度条
https://github.com/bingoogolapple/BGAProgressBar-Android

Android 流式布局，可配置是否将每一行的空白区域平均分配给子控件
https://github.com/bingoogolapple/BGAFlowLayout-Android