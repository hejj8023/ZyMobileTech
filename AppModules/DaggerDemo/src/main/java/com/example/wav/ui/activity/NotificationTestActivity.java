package com.example.wav.ui.activity;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.widget.RemoteViews;

import com.example.wav.R;
import com.example.wav.base.BaseAdvActivity;
import com.example.wav.manager.EMNotificationManager;

import butterknife.OnClick;

/**
 * Created by example on 2018/5/15.
 */

public class NotificationTestActivity extends BaseAdvActivity {
    @Override
    protected boolean initToolBar() {
        setTitle("通知栏测试");
        return true;
    }


    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_notification_test;
    }

    @OnClick({
            R.id.btn_common_notify,
            R.id.btn_em_notify,
            R.id.btn_custom_notify,
            R.id.btn_large_list_notify,
            R.id.btn_large_pic_notify,
            R.id.btn_progress_notify})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.btn_common_notify:
                //获取通知管理器
                NotificationManager manager = (NotificationManager) getSystemService
                        (NOTIFICATION_SERVICE);
                //获取通知实例
                NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
                //为实例设置属性
                builder.setSmallIcon(R.drawable.android_bigicon);//设置小图标
                // (必须设置，若LargeIcon没设置，默认也是它，而且通知栏没下拉的时候显示也是它)
                builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable
                        .ic_notification_music_logo));
                //设置大图标
                builder.setContentTitle("紧急通知");//设置标题内容
                builder.setContentText("据说最近不太平，各地流窜着各种怪物，包括大黄蜂，哥斯拉，擎天柱.....请各位民众小心....");//设置正文内容
                builder.setTicker("紧急通知!据说最近不太平，各地流窜着各种怪物，包括大黄蜂，哥斯拉，擎天柱.....请各位民众小心....");//设置滚动内容
                builder.setAutoCancel(true);//点击后消失
                builder.setOngoing(true);//是否一直显示（设置了这个方法后，不能通过左右滑动通知让通知栏消失，只有通过点击通知才能让通知消失）
                builder.setPriority(NotificationCompat.PRIORITY_HIGH);//设置优先级，级别越高，显示的位置越高
                //设置通知的点击事件（一般用来跳转到真正要显示的页面内）
                builder.setContentIntent(PendingIntent.getActivity(this, 1, new Intent(this,
                        NotifyDetailActivt.class), PendingIntent.FLAG_ONE_SHOT));
                //发送通知
                manager.notify(1, builder.build());
                break;
            case R.id.btn_em_notify:
                EMNotificationManager emNotificationManager = EMNotificationManager.getInstance
                        (mContext);
                emNotificationManager.sendNotification
                        ("据说最近不太平，各地流窜着各种怪物，包括大黄蜂，哥斯拉，擎天柱.....请各位民众小心....");
                break;
            case R.id.btn_custom_notify:
                //获取通知管理器
                NotificationManager manager4 = (NotificationManager) getSystemService
                        (NOTIFICATION_SERVICE);
                //获取通知实例
                NotificationCompat.Builder builder4 = new NotificationCompat.Builder(this);

                //设置属性
                builder4.setSmallIcon(R.drawable.android_bigicon)
                        .setContentTitle("我是标题");

                //构建远程view（这是自定义Notification的重点）
                //第一个参数为包名，通过getPackageName()获得就行了，第二参数为自定义布局的id
                RemoteViews views = new RemoteViews(getPackageName(), R.layout.custom_notification);

                //设置自定义布局里面控件的内容
                views.setImageViewResource(R.id.iv_notifi, R.drawable.android_bigicon);
                //这是设置自定义布局里面ImageView的方法，android提供的封装很好，直接填入对应控件的id和内容就行了
                views.setTextViewText(R.id.tv_title, "我是标题");
                views.setTextViewText(R.id.tv_content, "我是内容");

                //填充自定义布局
                builder4.setContent(views);

                //发送通知
                manager4.notify(6, builder4.build());
                break;
            case R.id.btn_large_list_notify:
                //获取通知管理器
                NotificationManager manager2 = (NotificationManager) getSystemService
                        (NOTIFICATION_SERVICE);
                //获取通知实例
                NotificationCompat.Builder builder2 = new NotificationCompat.Builder(this);

                //设置属性
                builder2.setSmallIcon(R.drawable.android_bigicon)
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
                break;
            case R.id.btn_large_pic_notify:
                //获取通知管理器
                NotificationManager manager3 = (NotificationManager) getSystemService
                        (NOTIFICATION_SERVICE);
                //获取通知实例
                NotificationCompat.Builder builder3 = new NotificationCompat.Builder(this);
                //设置一些属性
                builder3.setSmallIcon(R.drawable.android_bigicon)
                        .setContentTitle("提示信息");

                //实例化大图片样式（重点）
                NotificationCompat.BigPictureStyle style2 = new NotificationCompat
                        .BigPictureStyle();
                //填充图片
                style2.bigPicture(BitmapFactory.decodeResource(getResources(), R.drawable
                        .bg_small_tree_min));

                //设置摘要
                style2.setSummaryText("我是摘要");

                //设置成大图片样式
                builder3.setStyle(style2);

                //发送通知
                manager3.notify(5, builder3.build());
                break;
            case R.id.btn_progress_notify:
                new Thread(new Runnable() {

                    @Override
                    public void run() {
                        //获取通知管理器
                        NotificationManager manager = (NotificationManager) getSystemService
                                (NOTIFICATION_SERVICE);
                        //获取通知实例
                        NotificationCompat.Builder builder = new NotificationCompat.Builder
                                (mContext);

                        //设置notification参数
                        builder.setSmallIcon(R.drawable.android_bigicon)
                                .setContentTitle("进度条通知")
                                .setContentText("这是内容....这是内容....这是内容....这是内容....这是内容....")
                                .setTicker("这是内容....这是内容....这是内容....这是内容....这是内容....");

                        //模拟进度
                        for (int i = 0; i <= 100; i += 10) {
                            //设置如下方法，则此notification为进度条样式的
                            builder.setProgress(100, i, false);//第一个参数为进度最大值，第二个参数为刻度，第三个参数为
                            // 是否是不确定性进度条（也就是转圈的那种....）写false为不是
                            manager.notify(2, builder.build());
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }

                        //当进度100完了的时候，显示另一个通知
                        builder = new NotificationCompat.Builder(mContext);
                        builder.setSmallIcon(R.drawable.android_bigicon)
                                .setContentTitle("下载完成")
                                .setContentText("歌曲《soldier》下载完毕，立即查看")
                                .setTicker("下载完成")
                                .setPriority(NotificationCompat.PRIORITY_HIGH)
                                .setAutoCancel(true);
                        //设置声音和震动   第一个为声音，第二个为震动，用|表示与嘛。。
                        builder.setDefaults(Notification.DEFAULT_SOUND | Notification
                                .DEFAULT_VIBRATE);
                        //移除进度条那个条通知
                        manager.cancel(2);
                        //发送通知
                        manager.notify(3, builder.build());
                    }
                }).start();
                break;
        }
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void refreshUi() {

    }

    @Override
    protected void initInject() {

    }
}
