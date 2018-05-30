 ## 日期选择控件的使用
     private void initTimePicker() {//Dialog 模式下，在底部弹出
    
            pvTime = new TimePickerBuilder(this, new OnTimeSelectListener() {
                @Override
                public void onTimeSelect(Date date, View v) {
                    Toast.makeText(MainActivity.this, getTime(date), Toast.LENGTH_SHORT).show();
                    Log.i("pvTime", "onTimeSelect");
    
                }
            })
                    .setTimeSelectChangeListener(new OnTimeSelectChangeListener() {
                        @Override
                        public void onTimeSelectChanged(Date date) {
                            Log.i("pvTime", "onTimeSelectChanged");
                        }
                    })
                    .setType(new boolean[]{true, true, true, true, true, true})
                    .isDialog(true)
                    .build();
    
            Dialog mDialog = pvTime.getDialog();
            if (mDialog != null) {
    
                FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        Gravity.BOTTOM);
    
                params.leftMargin = 0;
                params.rightMargin = 0;
                pvTime.getDialogContainerLayout().setLayoutParams(params);
    
                Window dialogWindow = mDialog.getWindow();
                if (dialogWindow != null) {
                    dialogWindow.setWindowAnimations(com.bigkoo.pickerview.R.style.picker_view_slide_anim);//修改动画样式
                    dialogWindow.setGravity(Gravity.BOTTOM);//改成Bottom,底部显示
                }
            }
        }
    
    
        private void initCustomTimePicker() {
    
            /**
             * @description
             *
             * 注意事项：
             * 1.自定义布局中，id为 optionspicker 或者 timepicker 的布局以及其子控件必须要有，否则会报空指针.
             * 具体可参考demo 里面的两个自定义layout布局。
             * 2.因为系统Calendar的月份是从0-11的,所以如果是调用Calendar的set方法来设置时间,月份的范围也要是从0-11
             * setRangDate方法控制起始终止时间(如果不设置范围，则使用默认时间1900-2100年，此段代码可注释)
             */
            Calendar selectedDate = Calendar.getInstance();//系统当前时间
            Calendar startDate = Calendar.getInstance();
            startDate.set(2014, 1, 23);
            Calendar endDate = Calendar.getInstance();
            endDate.set(2027, 2, 28);
            //时间选择器 ，自定义布局
            pvCustomTime = new TimePickerBuilder(this, new OnTimeSelectListener() {
                @Override
                public void onTimeSelect(Date date, View v) {//选中事件回调
                    btn_CustomTime.setText(getTime(date));
                }
            })
                    /*.setType(TimePickerView.Type.ALL)//default is all
                    .setCancelText("Cancel")
                    .setSubmitText("Sure")
                    .setContentTextSize(18)
                    .setTitleSize(20)
                    .setTitleText("Title")
                    .setTitleColor(Color.BLACK)
                   /*.setDividerColor(Color.WHITE)//设置分割线的颜色
                    .setTextColorCenter(Color.LTGRAY)//设置选中项的颜色
                    .setLineSpacingMultiplier(1.6f)//设置两横线之间的间隔倍数
                    .setTitleBgColor(Color.DKGRAY)//标题背景颜色 Night mode
                    .setBgColor(Color.BLACK)//滚轮背景颜色 Night mode
                    .setSubmitColor(Color.WHITE)
                    .setCancelColor(Color.WHITE)*/
                   /*.animGravity(Gravity.RIGHT)// default is center*/
                    .setDate(selectedDate)
                    .setRangDate(startDate, endDate)
                    .setLayoutRes(R.layout.pickerview_custom_time, new CustomListener() {
    
                        @Override
                        public void customLayout(View v) {
                            final TextView tvSubmit = (TextView) v.findViewById(R.id.tv_finish);
                            ImageView ivCancel = (ImageView) v.findViewById(R.id.iv_cancel);
                            tvSubmit.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    pvCustomTime.returnData();
                                    pvCustomTime.dismiss();
                                }
                            });
                            ivCancel.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    pvCustomTime.dismiss();
                                }
                            });
                        }
                    })
                    .setContentTextSize(18)
                    .setType(new boolean[]{false, false, false, true, true, true})
                    .setLabel("年", "月", "日", "时", "分", "秒")
                    .setLineSpacingMultiplier(1.2f)
                    .setTextXOffset(0, 0, 0, 40, 0, -40)
                    .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                    .setDividerColor(0xFF24AD9D)
                    .build();
    
        }
    
          private String getTime(Date date) {//可根据需要自行截取数据显示
                Log.d("getTime()", "choice date millis: " + date.getTime());
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                return format.format(date);
            }

## 启动白屏，黑屏问题处理
<style name="AppTheme.Splash" parent="Theme.AppCompat.Light.NoActionBar">
    <item name="colorPrimaryDark">@android:color/transparent</item>
    <item name="android:windowIsTranslucent">true</item>
    <item name="windowActionBar">false</item>
    <item name="windowNoTitle">true</item>
</style>

## 信鸽推送平台常见问题

    http://www.360doc.com/content/15/0206/19/21412_446755092.shtml
    
## android 8.0 通知兼容处理
### ：NotificationChannel 
####（1）创建NotificationChannel 
    如果你需要发送属于某个自定义渠道的通知，你需要在发送通知前创建自定义通知渠道，示例如下：
    
    //ChannelId为"1",ChannelName为"Channel1"
    NotificationChannel channel = new NotificationChannel("1",
                    "Channel1", NotificationManager.IMPORTANCE_DEFAULT);
    channel.enableLights(true); //是否在桌面icon右上角展示小红点
    channel.setLightColor(Color.GREEN); //小红点颜色
    channel.setShowBadge(true); //是否在久按桌面图标时显示此渠道的通知
    notificationManager.createNotificationChannel(channel);
 
####    （2）向NotificationChannel发送通知
    public static void showChannel1Notification(Context context) {
        int notificationId = 0x1234;
        Notification.Builder builder = new Notification.Builder(context,"1"); //与channelId对应
        //icon title text必须包含，不然影响桌面图标小红点的展示
        builder.setSmallIcon(android.R.drawable.stat_notify_chat)
                .setContentTitle("xxx")
                .setContentText("xxx")
                .setNumber(3); //久按桌面图标时允许的此条通知的数量
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(notificationId, builder.build());
    }
   
####    （3）删除NotificationChannel
    NotificationChannel mChannel = mNotificationManager.getNotificationChannel(id);
    mNotificationManager.deleteNotificationChannel(mChannel);
    
#### 监听应用安装和卸载

    <receiver android:name=".utils.AppInstallReceiver" >
        <intent-filter>
            <action android:name="android.intent.action.PACKAGE_ADDED" />
            <action android:name="android.intent.action.PACKAGE_REMOVED" />
    
            <data android:scheme="package" />
        </intent-filter>
    </receiver>
    
    public class AppInstallReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            PackageManager manager = context.getPackageManager();
            if (intent.getAction().equals(Intent.ACTION_PACKAGE_ADDED)) {
                String packageName = intent.getData().getSchemeSpecificPart();
                ToastUtils.showShort("安装成功" + packageName);
            }
            if (intent.getAction().equals(Intent.ACTION_PACKAGE_REMOVED)) {
                String packageName = intent.getData().getSchemeSpecificPart();
                ToastUtils.showShort("卸载成功" + packageName);
                // 取消绑定
                XGPushManager.registerPush(context, "*");
            }
            if (intent.getAction().equals(Intent.ACTION_PACKAGE_REPLACED)) {
                String packageName = intent.getData().getSchemeSpecificPart();
                ToastUtils.showShort("替换成功" + packageName);
                // 取消绑定
                XGPushManager.registerPush(context, "*");
            }
        }
    }