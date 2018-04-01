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
    
    
# 锁屏监听

    public class ScreenListener {
        private Context mContext;
        private ScreenBroadcastReceiver mScreenReceiver;
        private ScreenStateListener mScreenStateListener;
    
        public ScreenListener(Context context) {
            mContext = context;
            mScreenReceiver = new ScreenBroadcastReceiver();
        }
    
        /**
         * screen状态广播接收者
         */
        private class ScreenBroadcastReceiver extends BroadcastReceiver {
            private String action = null;
    
            @Override
            public void onReceive(Context context, Intent intent) {
                action = intent.getAction();
                if (Intent.ACTION_SCREEN_ON.equals(action)) { // 开屏
                    mScreenStateListener.onScreenOn();
                } else if (Intent.ACTION_SCREEN_OFF.equals(action)) { // 锁屏
                    mScreenStateListener.onScreenOff();
                } else if (Intent.ACTION_USER_PRESENT.equals(action)) { // 解锁
                    mScreenStateListener.onUserPresent();
                }
            }
        }
    
        /**
         * 开始监听screen状态
         *
         * @param listener
         */
        public void begin(ScreenStateListener listener) {
            mScreenStateListener = listener;
            registerListener();
            getScreenState();
        }
    
        /**
         * 获取screen状态
         */
        private void getScreenState() {
            PowerManager manager = (PowerManager) mContext
                    .getSystemService(Context.POWER_SERVICE);
            if (manager.isScreenOn()) {
                if (mScreenStateListener != null) {
                    mScreenStateListener.onScreenOn();
                }
            } else {
                if (mScreenStateListener != null) {
                    mScreenStateListener.onScreenOff();
                }
            }
        }
    
        /**
         * 停止screen状态监听
         */
        public void unregisterListener() {
            mContext.unregisterReceiver(mScreenReceiver);
        }
    
        /**
         * 启动screen状态广播接收器
         */
        private void registerListener() {
            IntentFilter filter = new IntentFilter();
            filter.addAction(Intent.ACTION_SCREEN_ON);
            filter.addAction(Intent.ACTION_SCREEN_OFF);
            filter.addAction(Intent.ACTION_USER_PRESENT);
            mContext.registerReceiver(mScreenReceiver, filter);
        }
    
        public interface ScreenStateListener {// 返回给调用者屏幕状态信息
    
            public void onScreenOn();
    
            public void onScreenOff();
    
            public void onUserPresent();
        }
    }
    
    screenListener = new ScreenListener(this);
    screenListener.begin(new ScreenListener.ScreenStateListener() {
    
        @Override
        public void onUserPresent() {
        }
    
        @Override
        public void onScreenOn() {
        }
    
        @Override
        public void onScreenOff() {
            hasScreenLock = true;
        }
    });
    
#   android模拟器与PC的端口映射
  Android系统为实现通信将PC电脑IP设置为10.0.2.2，自身设置为127.0.0.1，而PC并没有为Android模拟器系统指定IP，所以PC电脑不能通过IP来直接访问Android模拟器，要实现PC机和Android模拟器之间的相互通信必须借助于端口重定向(redir)。
  telnet localhost 5554
  
 　3、在android console下执行命令
 
 　　redir add tcp：5000:6000
 
 　　然后执行redir list查看执行结果:
 
 　　
 
 　　这样就把PC端的5000端口号映射到android模拟器的6000端口，如果PC5000端口接收到数据，即转到模拟器的6000端口。
 
 　　4、删除端口映射
 
 　　使用命令redir del可以删除端口映射