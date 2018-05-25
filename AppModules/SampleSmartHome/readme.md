智能家居设备相关连接方式预研

1. bluetooth
蓝牙广播内容：
    
    
       Action值                                                说明
    
          ACTION_STATE_CHANGED                    蓝牙状态值发生改变
          ACTION_SCAN_MODE_CHANGED         蓝牙扫描状态(SCAN_MODE)发生改变
          ACTION_DISCOVERY_STARTED             蓝牙扫描过程开始
          ACTION_DISCOVERY_FINISHED             蓝牙扫描过程结束
          ACTION_LOCAL_NAME_CHANGED        蓝牙设备Name发生改变
          ACTION_REQUEST_DISCOVERABLE       请求用户选择是否使该蓝牙能被扫描
                    PS：如果蓝牙没有开启，用户点击确定后，会首先开启蓝牙，继而设置蓝牙能被扫描。
          ACTION_REQUEST_ENABLE                  请求用户选择是否打开蓝牙
     
          ACTION_FOUND  (该常量字段位于BluetoothDevice类中，稍后讲到)
                    说明：蓝牙扫描时，扫描到任一远程蓝牙设备时，会发送此广播。
                
https://blog.csdn.net/lishaobo211985/article/details/52185448

https://blog.csdn.net/yehui928186846/article/details/52710112

我们先来看一下基本步骤：

    1，打开blt。 
    ——1）权限 
    ——2）监听设备是否打开blt 
    ——3）操作，打开或者关闭blt
    
    2，搜索附近设备&让附近设备搜索到 
    ——1）让自身设备可以被附近搜索到，时间最长300s；（随着api的逐渐升级可能不止300s，做了一定的优化处理） 
    ——2）搜索附近可连接的设备；过时的api在接口中回调搜索结果，新的api则要求用广播接收搜索结果。（我们这里使用广播接收结果）
    
    3，与目标设备配对 
    ——1）对于目标设备进行配对，android系统本身会完成配对动作，并且将已经成功配对的设备的mac地址保存起来，以供下次进行自动配对使用。 
    ——2）对进行配对过的设备自动配对。这个动作也是系统帮我们完成的，我们只需要根据系统给我们的状态来判断这个是否已经配对就行了。
    
    4，与成功配对的设备进行连接 
    ——1）如果要对成功配对的设备进行连接，则必须先建立服务器端。服务器端建立只有会线程阻塞等待客户端的连接。 
    ——2）确保建立了服务器端之后再建立客户端。客户端的连接过程也是线程阻塞的。知道连接成功后，服务器端收到消息，则表示配对设备已连接成功。
    
    5，注意事项： 
    ——1）配对成功过的设备无需再次配对，只需要从系统中拿到mac地址进行连接。这个根据系统返回的状态值去判断。 
    ——2）搜索附近设备的操作是一个消耗内存的动作，我们务必在连接设备‘之前‘停止搜索。 
    ——3）设备的配对成功不代表连接成功。配对和连接是两回事且配对在连接动作之前。 
    ——4）当我们的程序明确指出“不需要blt操作了”的时候，及时反注册blt广播，停止blt连接，注销蓝牙对象。而这个操作例如：程序退出、用户无操作超时、逻辑不需要blt的连接了。


https://blog.csdn.net/u012910985/article/details/50387551

2. 打开蓝牙


    2.1 发送蓝牙开启请求
    
    先判断BluetoothAdapter是不是为空，为空有可能是系统没有蓝牙模块，再判断蓝牙的状态是不是开启的，不是开启的就发送请求。
    
    BluetoothAdapter btAdapter = BluetoothAdapter.getDefaultAdapter();
    if (btAdapter != null && !btAdapter.isEnabled()) {
      Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
      startActivity(intent)
    }
    
    2.2 后台自动打开蓝牙
    
    if (btAdapter != null && !btAdapter.isEnabled()) {
      btAdapter.enable();
    }

BluetoothDevice有如下方法：

    getBondState()  //获取设备绑定状态，BOND_NONE(未绑定), BOND_BONDING(正在绑定), BOND_BONDED(已绑定)
    getType()   //获取蓝牙类型，DEVICE_TYPE_CLASSIC(普通类型BR/EDR), DEVICE_TYPE_LE(低功耗类型), DEVICE_TYPE_DUAL(双类型BR/EDR/LE), DEVICE_TYPE_UNKNOWN(未知)
    getAddress()  //蓝牙地址
    getBluetoothClass()   //获取蓝牙类别(BluetoothClass)，如手机、电脑、耳机，注意与蓝牙类型的区别
    getName()   //蓝牙设备名字

监听蓝牙设备的变化

    IntentFilter filter = new IntentFilter();
    filter.addAction(BluetoothDevice.ACTION_FOUND);  //发现新设备
    filter.addAction(BluetoothDevice.ACTION_BOND_STATE_CHANGED);  //绑定状态改变
    filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_STARTED);  //开始扫描
    filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);  //结束扫描
    filter.addAction(BluetoothAdapter.ACTION_CONNECTION_STATE_CHANGED);  //连接状态改变
    filter.addAction(BluetoothAdapter.ACTION_STATE_CHANGED);  //蓝牙开关状态改变
    BluetoothReceiver receiver = new BluetoothReceiver();
    mActivity.registerReceiver(receiver, filter);
    
流程：

    （1）蓝牙的介绍，相关API使用说明，使用蓝牙的准备工作。
    
    （2）蓝牙的开启和关闭。
    
    （3）设置设备可被搜索。
    
    （4）搜索设备及广播接收器的注册。
    
    （5）蓝牙的配对。
    
    （6）蓝牙的连接服务端和客户端
    
    （7）蓝牙的多连接操作。
    
https://blog.csdn.net/qq_24531461/article/details/53466906    

    
    
#连接蓝牙
。注意：连接蓝牙的前提是我们已经建立好了蓝牙服务器端。所以，这里我们先建立蓝牙服务器 
## ——1）先建立蓝牙服务器端

    /**
     * 这个操作应该放在子线程中，因为存在线程阻塞的问题
     */
    public void run(Handler handler) {
        //服务器端的bltsocket需要传入uuid和一个独立存在的字符串，以便验证，通常使用包名的形式
        BluetoothServerSocket  bluetoothServerSocket = tmBluetoothAdapter.listenUsingRfcommWithServiceRecord("com.bluetooth.demo", BltContant.SPP_UUID);
        while (true) {
            try {
                //注意，当accept()返回BluetoothSocket时，socket已经连接了，因此不应该调用connect方法。
                //这里会线程阻塞，直到有蓝牙设备链接进来才会往下走
                socket = getBluetoothServerSocket().accept();
                if (socket != null) {
                    BltAppliaction.bluetoothSocket = socket;
                    //回调结果通知
                    Message message = new Message();
                    message.what = 3;
                    message.obj = socket.getRemoteDevice();
                    handler.sendMessage(message);
                    //如果你的蓝牙设备只是一对一的连接，则执行以下代码
                    getBluetoothServerSocket().close();
                    //如果你的蓝牙设备是一对多的，则应该调用break；跳出循环
                    //break;
                }
            } catch (IOException e) {
                try {
                    getBluetoothServerSocket().close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                break;
            }
        }
    }

##——2）在蓝牙服务器建立之后，再进行连接蓝牙的操作。

    /**
     * 尝试连接一个设备，子线程中完成，因为会线程阻塞
     *
     * @param btDev 蓝牙设备对象
     * @param handler 结果回调事件
     * @return
     */
    private void connect(BluetoothDevice btDev, Handler handler) {
        try {
            //通过和服务器协商的uuid来进行连接
            mBluetoothSocket = btDev.createRfcommSocketToServiceRecord(BltContant.SPP_UUID);
            if (mBluetoothSocket != null)
                //全局只有一个bluetooth，所以我们可以将这个socket对象保存在appliaction中
                BltAppliaction.bluetoothSocket = mBluetoothSocket;
            //通过反射得到bltSocket对象，与uuid进行连接得到的结果一样，但这里不提倡用反射的方法
            //mBluetoothSocket = (BluetoothSocket) btDev.getClass().getMethod("createRfcommSocket", new Class[]{int.class}).invoke(btDev, 1);
            Log.d("blueTooth", "开始连接...");
            //在建立之前调用
            if (getmBluetoothAdapter().isDiscovering())
                //停止搜索
                getmBluetoothAdapter().cancelDiscovery();
            //如果当前socket处于非连接状态则调用连接
            if (!getmBluetoothSocket().isConnected()) {
                //你应当确保在调用connect()时设备没有执行搜索设备的操作。
                // 如果搜索设备也在同时进行，那么将会显著地降低连接速率，并很大程度上会连接失败。
                getmBluetoothSocket().connect();
            }
            Log.d("blueTooth", "已经链接");
            if (handler == null) return;
            //结果回调
            Message message = new Message();
            message.what = 4;
            message.obj = btDev;
            handler.sendMessage(message);
        } catch (Exception e) {
            Log.e("blueTooth", "...链接失败");
            try {
                getmBluetoothSocket().close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
    }

#9，自动连接以往配对成功的设备。注意：连接的前提是服务器端已开启，若没开启则进行8.1的操作

    /**
     * 尝试配对和连接
     *
     * @param btDev
     */
    public void createBond(BluetoothDevice btDev, Handler handler) {
        if (btDev.getBondState() == BluetoothDevice.BOND_NONE) {
            //如果这个设备取消了配对，则尝试配对
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                btDev.createBond();
            }
        } else if (btDev.getBondState() == BluetoothDevice.BOND_BONDED) {
            //如果这个设备已经配对完成，则尝试连接
            connect(btDev, handler);
        }
    }
    
    /**
     * 获得系统保存的配对成功过的设备，并尝试连接
     */
    public void getBltList() {
        if (getmBluetoothAdapter() == null) return;
        //获得已配对的远程蓝牙设备的集合
        Set<BluetoothDevice> devices = getmBluetoothAdapter().getBondedDevices();
        if (devices.size() > 0) {
            for (Iterator<BluetoothDevice> it = devices.iterator(); it.hasNext(); ) {
                BluetoothDevice device = it.next();
                //自动连接已有蓝牙设备
                createBond(device, null);
            }
        }
    }
    
    注意：外界只需要调用getBltList();方法即可进行自动连接。

#10，输入mac地址自动连接设备。前提是系统原来连接过该地址。

    /**
     * 输入mac地址进行自动配对
     * 前提是系统保存了该地址的对象
     *
     * @param address
     */
    public void autoConnect(String address, Handler handler) {
        if (getmBluetoothAdapter().isDiscovering()) getmBluetoothAdapter().cancelDiscovery();
        BluetoothDevice btDev = getmBluetoothAdapter().getRemoteDevice(address);
        connect(btDev, handler);
    }
        
        
2. wifi

https://www.jianshu.com/p/c415691b282c


### 好的案例
http://www.demodashi.com/demo/10660.html
https://blog.csdn.net/VNanyesheshou/article/details/50771698


6.0以下连接成功


## ACTION_MANAGE_WRITE_SETTINGS权限申请的坑
    
    if (PackageManager.PERMISSION_GRANTED == ContextCompat.checkSelfPermission
        (SampleWifiActivity.this, Manifest.permission.CHANGE_NETWORK_STATE)) {
        LoggerUtils.loge("action_create_hot PERMISSION_GRANTED = CHANGE_NETWORK_STATE");
        if (PackageManager.PERMISSION_GRANTED == ContextCompat.checkSelfPermission
                (SampleWifiActivity.this, Manifest.permission.WRITE_SETTINGS)) {
            LoggerUtils.loge("action_create_hot PERMISSION_GRANTED = WRITE_SETTINGS " +
                    "，创建ap热点");
            WifiUtils.createHotAp("zzg", "123456");
        } else {
            LoggerUtils.loge("action_create_hot PERMISSION_GRANTED != WRITE_SETTINGS");
            if (ActivityCompat.shouldShowRequestPermissionRationale
                    (SampleWifiActivity.this,
                            Manifest.permission.WRITE_SETTINGS)) {
                LoggerUtils.loge("action_create_hot PERMISSION_GRANTED != " +
                        "WRITE_SETTINGS 以前拒绝过");
            } else {
                LoggerUtils.loge("action_create_hot PERMISSION_GRANTED != " +
                        "WRITE_SETTINGS 以前未拒绝过，申请权限");
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(SampleWifiActivity.this,
                        new String[]{Manifest.permission.WRITE_SETTINGS},
                        Const.PERMISSION_REQ_WIFI);
            }
        }
    } else {
        LoggerUtils.loge("action_create_hot PERMISSION_GRANTED != " +
                "CHANGE_NETWORK_STATE");
        //申请
        if (ActivityCompat.shouldShowRequestPermissionRationale(SampleWifiActivity.this,
                Manifest.permission.CHANGE_NETWORK_STATE)) {
            LoggerUtils.loge("action_create_hot PERMISSION_GRANTED != " +
                    "CHANGE_NETWORK_STATE 以前拒绝过");
        } else {
            LoggerUtils.loge("action_create_hot PERMISSION_GRANTED != " +
                    "CHANGE_NETWORK_STATE 以前未拒绝过，申请权限");
            // No explanation needed, we can request the permission.
            ActivityCompat.requestPermissions(SampleWifiActivity.this,
                    new String[]{Manifest.permission.CHANGE_NETWORK_STATE},
                    Const.PERMISSION_REQ_WIFI);
        }
    }
    
    // 动态申请权限不弹出对话框，太他娘的坑了
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        if (!Settings.System.canWrite(this)) {
            Intent intent = new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS);
            intent.setData(Uri.parse("package:" + getPackageName()));
            startActivityForResult(intent, REQUEST_CODE_WRITE_SETTINGS);
        }
    }
    
# recyclerview 万能分割线
https://github.com/YuJunKui1995/UniversalItemDecoration

# Toolbar menu 分割线设置
https://blog.csdn.net/soslinken/article/details/53125935

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

    
    public class ArrayListRemove {
        public static void main(String[] args) {
            ArrayList<String> list = new ArrayList<String>();
            list.add("beijing");
            list.add("shanghai");
            list.add("shanghai");
            list.add("guangzhou");
            list.add("shenzhen");
            list.add("hangzhou");
            remove11(list, "shanghai");
    
        }
    
        private static void print(List<String> list){
            for (String item : list) {
                System.out.println("元素值：" + item);
            }
        }
        
## Java编程：删除 List 元素的三种正确方法   

    /*
     * 正确
     */
    public static void remove14(List<String> list, String target){
        for(int i = list.size() - 1; i >= 0; i--){
            String item = list.get(i);
            if(target.equals(item)){
                list.remove(item);
            }
        }
        print(list);
    }
    
    /*
     * 正确
     */
    public static void remove22(ArrayList<String> list, String target) {
        final CopyOnWriteArrayList<String> cowList = new CopyOnWriteArrayList<String>(list);
        for (String item : cowList) {
            if (item.equals(target)) {
                cowList.remove(item);
            }
        }
        print(cowList);
    }
    
    /*
     * 正确
     */
    public static void remove32(List<String> list, String target){
        Iterator<String> iter = list.iterator();
        while (iter.hasNext()) {
            String item = iter.next();
            if (item.equals(target)) {
                iter.remove();
            }
        }
        print(list);
    }
    
    }