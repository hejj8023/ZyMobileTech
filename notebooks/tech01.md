## home键监听处理(利用广播接收者)
    homeWatcherReceiver = new HomeWatcherReceiver();
    IntentFilter filter = new IntentFilter(Intent.ACTION_CLOSE_SYSTEM_DIALOGS);
    registerReceiver(homeWatcherReceiver, filter);
    
    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        int keyCode = event.getKeyCode();
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            if (keyCode == KeyEvent.KEYCODE_BACK) {
                this.finish();
                System.gc();
                return true;
            }
        }
        return super.dispatchKeyEvent(event);
    }
    
    private class HomeWatcherReceiver extends BroadcastReceiver {
    
        private static final String SYSTEM_DIALOG_REASON_KEY = "reason";
        private static final String SYSTEM_DIALOG_REASON_HOME_KEY = "homekey";
    
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent == null)
                return;
            String action = intent.getAction();
            if (TextUtils.isEmpty(action))
                return;
            if (getClass().getSimpleName().equalsIgnoreCase("com.example.MainActivity")) {
                LogUtils.loge(BaseActivity.this, "主界面不允许退出...");
                return;
            }
            LogUtils.loge(BaseActivity.this, "action = " + action);
            if (TextUtils.equals(action, Intent.ACTION_CLOSE_SYSTEM_DIALOGS)) {
                String reason = intent.getStringExtra(SYSTEM_DIALOG_REASON_KEY);
                LogUtils.loge(BaseActivity.this, "reason = " + reason);
                if (TextUtils.equals(SYSTEM_DIALOG_REASON_HOME_KEY, reason)) {
                    BaseActivity.this.finish();
                }
            }
        }
    }
    
## 使用正则校验字符串长度和是否包含非法字符

    public static final String KEY_SETTING_ITEM_MATCHS = "^[a-zA-Z0-9']{32,32}$";
    
    etLibai.setFilters(new InputFilter[]{new InputFilter.LengthFilter(32)});
    
    private void setViewFocusStatus(EditText editText, View view, View warnView) {
     editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
         @Override
         public void onFocusChange(View v, boolean hasFocus) {
             clearViewBgs();
             editText.setSelection(0);
             if (lastEditText != null && lastWarnView != null) {
                 String textStr = lastEditText.getText().toString();
                 int length = textStr.trim().length();
                 LogUtils.loge(SettingActivity.this, "data len = " + length);
                 if (length < 32) {
                     ToastUtils.showShort("字符长度不足，请补充至32位");
                     lastWarnView.setVisibility(View.VISIBLE);
                 } else {
                     LogUtils.loge(SettingActivity.this, "数据合法");
                     // TODO: 不管数据够不够32位都判断是否有非法字符
                     if (!textStr.matches(Const.KEY_SETTING_ITEM_MATCHS)) {
                         ToastUtils.showShort("包含非法字符请检查...");
                         lastWarnView.setVisibility(View.VISIBLE);
                     } else {
                         lastWarnView.setVisibility(View.INVISIBLE);
                     }
                 }
             }
    
             if (hasFocus) {
                 view.setBackgroundColor(R.drawable.btn_input2);
             }
             lastEditText = editText;
             lastWarnView = warnView;
         }
     });
    }
    
# 修改系统语言设置

    <uses-permission android:name="android.permission.WRITE_SETTINGS" />
    <uses-permission android:name="android.permission.CHANGE_CONFIGURATION" />
    
    try {
        Object objIActMag;
    
        Class clzIActMag = Class.forName("android.app.IActivityManager");
    
        Class clzActMagNative = Class.forName("android.app.ActivityManagerNative");
    
        Method mtdActMagNative$getDefault = clzActMagNative.getDeclaredMethod("getDefault");
        objIActMag = mtdActMagNative$getDefault.invoke(clzActMagNative);
        Method mtdIActMag$getConfiguration = clzIActMag.getDeclaredMethod("getConfiguration");
        Configuration config = (Configuration) mtdIActMag$getConfiguration.invoke(objIActMag);
        config.locale = Locale.CHINA;
        Class clzConfig = Class.forName("android.content.res.Configuration");
        java.lang.reflect.Field userSetLocale = clzConfig.getField("userSetLocale");
        userSetLocale.set(config, true);
        Class[] clzParams = {Configuration.class};
        Method mtdIActMag$updateConfiguration = clzIActMag
                .getDeclaredMethod("updateConfiguration", clzParams);
        mtdIActMag$updateConfiguration.invoke(objIActMag, config);
        BackupManager.dataChanged("com.android.providers.settings");
    } catch (Exception e) {
        e.printStackTrace();
    }
    
    注意权限的设置，没有权限会修改不成功 