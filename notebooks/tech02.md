# bind的service被杀监听

最近任务列表中清理了应用，会走service的unbind方法

# home和最近任务列表按键监听

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            LogUtils.loge(this, "监听到了back键");
            return true;//return true;拦截事件传递,从而屏蔽back键。
        }
        if (KeyEvent.KEYCODE_HOME == keyCode) {
            LogUtils.loge(this, "监听到了home键");
            return true;//同理
        }
        return super.onKeyDown(keyCode, event);
    }
    
    private class MyReceiver extends BroadcastReceiver {
    
        private final String SYSTEM_DIALOG_REASON_KEY = "reason";
        private final String SYSTEM_DIALOG_REASON_HOME_KEY = "homekey";
        private final String SYSTEM_DIALOG_REASON_RECENT_APPS = "recentapps";
    
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(Intent.ACTION_CLOSE_SYSTEM_DIALOGS)) {
                String reason = intent.getStringExtra(SYSTEM_DIALOG_REASON_KEY);
    
                if (reason == null)
                    return;
    
                // Home键
                if (reason.equals(SYSTEM_DIALOG_REASON_HOME_KEY)) {
                    Toast.makeText(getApplicationContext(), "按了Home键", Toast.LENGTH_SHORT).show();
                }
    
                // 最近任务列表键
                if (reason.equals(SYSTEM_DIALOG_REASON_RECENT_APPS)) {
                    Toast.makeText(getApplicationContext(), "按了最近任务列表", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
    
# gradle 中支持vector

    vectorDrawables.useSupportLibrary = true
    
    