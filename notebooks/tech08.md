# android自定义dialog
## sytle:

    <style name="my_dialog_style" parent="android:Theme.Dialog">
        <!-- 背景颜色及透明程度 -->
        <item name="android:windowBackground">@android:color/transparent</item>
        <!-- 是否半透明 -->
        <item name="android:windowIsTranslucent">true</item>
        <!-- 是否没有标题 -->
        <item name="android:windowNoTitle">true</item>
        <!-- 是否浮现在activity之上 -->
        <item name="android:windowIsFloating">true</item>
        <!-- 是否背景模糊 -->
        <item name="android:backgroundDimEnabled">true</item>
        <!-- 设置背景模糊的透明度-->
        <item name="android:backgroundDimAmount">0.5</item>
    
        <!-- 动画 -->
        <item name="android:windowAnimationStyle">@style/dialog_animation</item>
    
        <item name="android:windowFrame">@null</item>
        <item name="android:background">@color/trans</item>
        <item name="android:windowContentOverlay">@null</item>
    </style>
    <!-- 对话框显示和退出动画 -->
    <style name="dialog_animation">
        <item name="android:windowEnterAnimation">@anim/dialog_enter</item>
        <item name="android:windowExitAnimation">@anim/dialog_exit</item>
    </style>

    
## dialog类;

    public class PlayModeSelectDialog extends Dialog {
        public PlayModeSelectDialog(@NonNull Context context) {
            super(context, R.style.my_dialog_style);
    
            View dialogView = UiUtils.inflateView(R.layout.layout_select_play_mode_dialog);
            setContentView(dialogView);
    
            Window dialogWindow = getWindow();
            WindowManager.LayoutParams lp = dialogWindow.getAttributes();
            DisplayMetrics d = context.getResources().getDisplayMetrics(); // 获取屏幕宽、高用
            lp.width = (int) (d.widthPixels * 0.8); // 高度设置为屏幕的0.6
            lp.height = (int) (d.heightPixels * 0.4); // 高度设置为屏幕的0.6
            dialogWindow.setAttributes(lp);
        }
    }
    
## checkbox的坑，如果只想在选择的时候显示图片，未选择不显示，最好是用图片来实现不要使用checkbox
如果一定要用checkbox使用下面的方式修改

    <CheckBox
        android:id="@+id/cb_customer_group"
        android:layout_width="@dimen/dp_30"
        android:layout_height="@dimen/dp_30"
        android:layout_alignParentRight="true"
        android:layout_centerVertical="true"
        android:background="@drawable/selector_checkbox"
        android:button="@null"
        android:checked="false"
        android:clickable="false" />
        
    <?xml version="1.0" encoding="utf-8"?>
    <selector xmlns:android="http://schemas.android.com/apk/res/android">
        <item android:drawable="@drawable/ic_checked" android:state_checked="true" />
        <item android:drawable="@drawable/d_trans" android:state_checked="false" />
        <item android:drawable="@drawable/d_trans" />
    </selector>
    
## android简单实例-----------5种Notification的简单使用
###https://blog.csdn.net/sinat_31311947/article/details/50682921
####普通通知：

    //获取通知管理器
    NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);  
    //获取通知实例  
    NotificationCompat.Builder builder = new NotificationCompat.Builder(this);  
    //为实例设置属性  
    builder.setSmallIcon(R.drawable.ic_launcher);//设置小图标 (必须设置，若LargeIcon没设置，默认也是它，而且通知栏没下拉的时候显示也是它)  
    builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_8));//设置大图标  
    builder.setContentTitle("紧急通知");//设置标题内容  
    builder.setContentText("据说最近不太平，各地流窜着各种怪物，包括大黄蜂，哥斯拉，擎天柱.....请各位民众小心....");//设置正文内容  
    builder.setTicker("紧急通知!据说最近不太平，各地流窜着各种怪物，包括大黄蜂，哥斯拉，擎天柱.....请各位民众小心....");//设置滚动内容  
    builder.setAutoCancel(true);//点击后消失  
    builder.setOngoing(true);//是否一直显示（设置了这个方法后，不能通过左右滑动通知让通知栏消失，只有通过点击通知才能让通知消失）  
    builder.setPriority(NotificationCompat.PRIORITY_HIGH);//设置优先级，级别越高，显示的位置越高  
    //设置通知的点击事件（一般用来跳转到真正要显示的页面内）  
    builder.setContentIntent(PendingIntent.getActivity(this, 1, new Intent(this, InfoActivity.class), PendingIntent.FLAG_ONE_SHOT));  
      
    //发送通知  
    manager.notify(1, builder.build());  
    
####进度条通知：
    new Thread(new Runnable() {  
          
        @Override  
        public void run() {  
            //获取通知管理器  
            NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);  
            //获取通知实例  
            NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this);  
              
            //设置notification参数  
            builder.setSmallIcon(R.drawable.ic_launcher)  
                    .setContentTitle("进度条通知")  
                    .setContentText("这是内容....这是内容....这是内容....这是内容....这是内容....")  
                    .setTicker("这是内容....这是内容....这是内容....这是内容....这是内容....");  
              
            //模拟进度  
            for(int i = 0;i<=100;i+=10){  
                //设置如下方法，则此notification为进度条样式的  
                builder.setProgress(100, i, false);//第一个参数为进度最大值，第二个参数为刻度，第三个参数为 是否是不确定性进度条（也就是转圈的那种....）写false为不是  
                manager.notify(2, builder.build());  
                try {  
                    Thread.sleep(1000);  
                } catch (InterruptedException e) {  
                    e.printStackTrace();  
                }  
            }  
              
            //当进度100完了的时候，显示另一个通知  
            builder = new NotificationCompat.Builder(MainActivity.this);  
            builder.setSmallIcon(R.drawable.ic_8)  
                    .setContentTitle("下载完成")  
                    .setContentText("歌曲《soldier》下载完毕，立即查看")  
                    .setTicker("下载完成")  
                    .setPriority(NotificationCompat.PRIORITY_HIGH)  
                    .setAutoCancel(true);  
            //设置声音和震动   第一个为声音，第二个为震动，用|表示与嘛。。  
            builder.setDefaults(Notification.DEFAULT_SOUND|Notification.DEFAULT_VIBRATE);  
            //移除进度条那个条通知  
            manager.cancel(2);  
            //发送通知  
            manager.notify(3, builder.build());  
        }  
    }).start();  
    
    
####  大视图通知：列表样式
  
    /获取通知管理器  
    NotificationManager manager2 = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);  
    //获取通知实例  
    NotificationCompat.Builder builder2 = new NotificationCompat.Builder(this);  
    
    //设置属性  
    builder2.setSmallIcon(R.drawable.ic_9)  
          .setContentTitle("对话内容");  
    
    //实例化列表（重点）  
    NotificationCompat.InboxStyle style = new NotificationCompat.InboxStyle();  
    //添加列表内容（一行一行的形式）  
    style.addLine("我是第一行");  
    style.addLine("我是第二行");  
    style.addLine("我是第三行");  
    
    //设置为列表样式  
    builder2.setStyle(style);  
    
    //发送通知  
    manager2.notify(4, builder2.build());  
    
#### 大视图通知：图片
    
    //获取通知管理器  
    NotificationManager manager3 = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);  
    //获取通知实例  
    NotificationCompat.Builder builder3 = new NotificationCompat.Builder(this);  
    //设置一些属性  
    builder3.setSmallIcon(R.drawable.ic_9)  
            .setContentTitle("提示信息");  
      
    //实例化大图片样式（重点）  
    NotificationCompat.BigPictureStyle style2 = new NotificationCompat.BigPictureStyle();  
    //填充图片  
    style2.bigPicture(BitmapFactory.decodeResource(getResources(), R.drawable.cachepage));  
      
    //设置摘要  
    style2.setSummaryText("我是摘要");  
      
    //设置成大图片样式  
    builder3.setStyle(style2);  
      
    //发送通知  
    manager3.notify(5, builder3.build());
    
####   自定义通知
              
    <RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"  
      android:layout_width="match_parent"  
      android:layout_height="match_parent" >  
        
      <ImageView   
          android:id="@+id/iv_notifi"  
          android:layout_width="wrap_content"  
          android:layout_height="wrap_content"  
          android:src="@drawable/ic_launcher"/>  
      <TextView   
          android:id="@+id/tv_title"  
          android:layout_width="wrap_content"  
          android:layout_height="wrap_content"  
          android:layout_toRightOf="@id/iv_notifi"  
          android:text="我是题目"/>  
      <TextView   
          android:id="@+id/tv_content"  
          android:layout_width="wrap_content"  
          android:layout_height="wrap_content"  
          android:text="我是内容"  
          android:layout_below="@id/tv_title"  
          android:layout_alignLeft="@id/tv_title"/>  
    </RelativeLayout>  
    
    //获取通知管理器  
    NotificationManager manager4 = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);  
    //获取通知实例  
    NotificationCompat.Builder builder4 = new NotificationCompat.Builder(this);  
      
    //设置属性  
    builder4.setSmallIcon(R.drawable.ic_8)  
            .setContentTitle("我是标题");  
      
    //构建远程view（这是自定义Notification的重点）  
    //第一个参数为包名，通过getPackageName()获得就行了，第二参数为自定义布局的id  
    RemoteViews views = new RemoteViews(getPackageName(), R.layout.custom_notification);  
      
    //设置自定义布局里面控件的内容  
    views.setImageViewResource(R.id.iv_notifi, R.drawable.ic_9);//这是设置自定义布局里面ImageView的方法，android提供的封装很好，直接填入对应控件的id和内容就行了  
    views.setTextViewText(R.id.tv_title, "我是标题");  
    views.setTextViewText(R.id.tv_content, "我是内容");  
      
    //填充自定义布局  
    builder4.setContent(views);  
      
    //发送通知  
    manager4.notify(6, builder4.build());
         
## 腾讯信鸽自定义推送通知
   https://blog.csdn.net/u010844304/article/details/51986035
   
## gradle生成jar包

   task createJar(type: Copy){
       from('build/intermediates/bundles/release/')
       into('libs/')
       include('classes.jar')
       rename('classes.jar','library.jar')
   }
   createJar.dependsOn(deleteJar, build)
   
   libs目录需要创建
   
## 代码中通过设置toolbar popuptheme设置men分割线
   
       toolbar.setPopupTheme(R.style.AppToolbarPopupTheme);
       
       <style name="AppToolbarPopupTheme" parent="Widget.AppCompat.PopupMenu.Overflow">
       <item name="android:dropDownListViewStyle">@style/AppDropDownListViewStyle</item>
       </style>
       
       <style name="AppDropDownListViewStyle" parent="Widget.AppCompat.ListView.DropDown">
       <item name="android:showDividers">middle</item>
       <item name="android:divider">@color/gray</item>
       <item name="android:dividerHeight">1px</item>
       </style>   
       
## Edittext下划线颜色修改
    <style name="MyEditText" parent="Theme.AppCompat.Light">
        <item name="colorControlNormal">@android:color/darker_gray</item>
        <item name="colorControlActivated">@android:color/holo_orange_dark</item>
    </style>
    
    <EditText
        android:id="@+id/et_ap_pwd"
        android:layout_width="match_parent"
        android:layout_height="@dimen/dp_45"
        android:layout_marginLeft="@dimen/dp_10"
        android:layout_marginRight="@dimen/dp_10"
        android:layout_marginTop="@dimen/dp_10"
        android:hint="@string/tip_input_ap_pwd"
        android:textColor="@color/black"
        android:textSize="@dimen/dp_16"
        android:theme="@style/MyEditText" />
        
## tablayout使用

    <android.support.design.widget.TabLayout
        android:id="@+id/tl_main"
        android:layout_width="match_parent"
        android:layout_height="?attr/actionBarSize"
        android:background="@drawable/selector_list_item"
        app:tabIndicatorColor="@color/orange"
        app:tabSelectedTextColor="@color/orange"
        app:tabTextColor="@color/white" />
        
    mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
    mTabLayout.setupWithViewPager(mViewPager);
    
    
## bugly的正确使用
    app.build.gradle:
        apply plugin: 'bugly'
        bugly {
            appId = 'fd0f6d1eae'
            appKey = '416fd455-7cc4-4e6b-9d61-3471adcddda4'
        }
        
    app:
    /* Bugly SDK初始化
    * 参数1：上下文对象
    * 参数2：APPID，平台注册时得到,注意替换成你的appId
    * 参数3：是否开启调试模式，调试模式下会输出'CrashReport'tag的日志
    */
    CrashReport.initCrashReport(getApplicationContext(), "900029763", true);