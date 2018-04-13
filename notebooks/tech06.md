Android 监听apk安装替换卸载广播

首先是要获取应用的安装状态，通过广播的形式

以下是和应用程序相关的Broadcast Action

>ACTION_PACKAGE_ADDED 一个新应用包已经安装在设备上，数据包括包名（最新安装的包程序不能接收到这个广播）
ACTION_PACKAGE_REPLACED	一个新版本的应用安装到设备，替换之前已经存在的版本
ACTION_PACKAGE_CHANGED	一个已存在的应用程序包已经改变，包括包名
ACTION_PACKAGE_REMOVED	一个已存在的应用程序包已经从设备上移除，包括包名（正在被安装的包程序不能接收到这个广播）
ACTION_PACKAGE_RESTARTED	用户重新开始一个包，包的所有进程将被杀死，所有与其联系的运行时间状态应该被移除，包括包名（重新开始包程序不能接收到这个广播）
ACTION_PACKAGE_DATA_CLEARED	用户已经清楚一个包的数据，包括包名（清除包程序不能接收到这个广播）

代码实现 

在AndroidManifest.xml中定义广播

    <receiver android:name=".AppInstallReceiver"
        android:label="@string/app_name">
        <intent-filter>
            <action android:name="android.intent.action.PACKAGE_ADDED" /> 
            <action android:name="android.intent.action.PACKAGE_REPLACED" />
            <action android:name="android.intent.action.PACKAGE_REMOVED" />
            <data android:scheme="package" />
        </intent-filter>
    </receiver>

ACTION_PACKAGE_ADDED 一个新应用包已经安装在设备上，数据包括包名（最新安装的包程序不能接收到这个广播）
ACTION_PACKAGE_REPLACED	一个新版本的应用安装到设备，替换之前已经存在的版本
ACTION_PACKAGE_REMOVED	一个已存在的应用程序包已经从设备上移除，包括包名（正在被安装的包程序不能接收到这个广播）

再看AppInstallReceiver 

    public class AppInstallReceiver extends BroadcastReceiver {
    
        @Override
        public void onReceive(Context context, Intent intent) {
            PackageManager manager = context.getPackageManager();
            if (intent.getAction().equals(Intent.ACTION_PACKAGE_ADDED)) {
                String packageName = intent.getData().getSchemeSpecificPart();
                Toast.makeText(context, "安装成功"+packageName, Toast.LENGTH_LONG).show();
            }
            if (intent.getAction().equals(Intent.ACTION_PACKAGE_REMOVED)) {
                String packageName = intent.getData().getSchemeSpecificPart();
                Toast.makeText(context, "卸载成功"+packageName, Toast.LENGTH_LONG).show();
            }
            if (intent.getAction().equals(Intent.ACTION_PACKAGE_REPLACED)) {
                String packageName = intent.getData().getSchemeSpecificPart();
                Toast.makeText(context, "替换成功"+packageName, Toast.LENGTH_LONG).show();
            }
            
    
        }
    
    }
代码实现比较简单，根据接收到的Action来判断应用程序是安装 卸载还是被替换成其他版本