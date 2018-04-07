package com.example.fta.ui.activity;

import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Message;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.blankj.utilcode.util.NetworkUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.fta.BaseTransfer;
import com.example.fta.Const;
import com.example.fta.FileReceiver;
import com.example.fta.FtaApp;
import com.example.fta.R;
import com.example.fta.WifiBroadcastReceiver;
import com.example.fta.WifiUtils;
import com.example.fta.bean.FileInfo;
import com.example.fta.decoration.GridSpaceItemDecoration;
import com.example.fta.utils.FileUtils;
import com.zhiyangstudio.sdklibrary.CommonConst;
import com.zhiyangstudio.sdklibrary.common.corel.BaseActivity;
import com.zhiyangstudio.sdklibrary.common.corel.BaseInternalHandler;
import com.zhiyangstudio.sdklibrary.common.utils.EmptyUtils;
import com.zhiyangstudio.sdklibrary.common.utils.InternalUtils;
import com.zhiyangstudio.sdklibrary.common.utils.ThreadUtils;
import com.zhiyangstudio.sdklibrary.utils.LoggerUtils;
import com.zhiyangstudio.sdklibrary.utils.UiUtils;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by zzg on 2018/4/6.
 */

public class WifiHotspotReceiveFilesActivity extends BaseActivity {

    /**
     * 接收端初始化完毕
     */
    public static final int MSG_FILE_RECEIVER_INIT_SUCCESS = 0x661;

    /**
     * 更新适配器
     */
    public static final int MSG_UPDATE_ADAPTER = 0x662;

    /**
     * 发送选中要接收的文件列表
     */
    public static final int MSG_SEND_RECEIVE_FILE_LIST = 0x663;

    /**
     * 添加接收文件
     */
    public static final int MSG_ADD_FILEINFO = 0x664;

    /**
     * 更新进度条
     */
    public static final int MSG_UPDATE_PROGRESS = 0x665;

    /**
     * 设置当前状态
     */
    public static final int MSG_SET_STATUS = 0x666;

    private FtaApp mAppInstance;
    private boolean mIsPermissionGranted;
    /**
     * WiFi热点连接和创建权限请求码
     */
    protected static final int PERMISSION_REQ_CONNECT_WIFI = 3020;

    /**
     * 用来接收文件的Socket
     */
    private Socket mClientSocket;

    /**
     * UDP Socket
     */
    private DatagramSocket mDatagramSocket;

    /**
     * 选中待发送的文件列表
     */
    private List<FileInfo> mSendFileInfos = new ArrayList<>();

    /**
     * 接收文件线程列表数据
     */
    private List<FileReceiver> mFileReceiverList = new ArrayList<>();

    /**
     * 扫描到的可用WiFi列表
     */
    private List<ScanResult> mScanResults = new ArrayList<>();

    @BindView(R.id.tv_receive_files_status)
    TextView tvStatus;

    @BindView(R.id.rv_receive_files_choose_hotspot)
    RecyclerView rvChooseHotspot;

    @BindView(R.id.rv_receive_files)
    RecyclerView rvReceiveFiles;

    @BindView(R.id.btn_receive_files)
    Button btnSendFileList;
    /**
     * 当前所选wifi的SSID
     */
    private String mSelectedSSID;

    /**
     * 是否已发送初始化指令
     */
    private boolean mIsSendInitOrder;

    private BaseQuickAdapter<Map.Entry<String, FileInfo>, BaseViewHolder> mReceiveFilesAdapter;

    private ReceiveServerRunnable mReceiveServerRunnable;

    /**
     * handler
     */
    private BaseInternalHandler mH = new BaseInternalHandler(this) {
        @Override
        protected void processMessage(Message message) {
            switch (message.what) {
                case MSG_FILE_RECEIVER_INIT_SUCCESS:
                    // 告知发送端，接收端初始化完毕
                    sendInitSucessToFileSender();
                    break;
                case MSG_UPDATE_ADAPTER:
                    // 更新适配器
                    setupReceiveFilesAdapter();
                    break;
                case MSG_SEND_RECEIVE_FILE_LIST:
                    // 发送选中要接收的文件列表
                    sendFile50FileSender();
                    break;
            }
        }
    };

    /**
     * 发送选中的文件列表给发送端
     */
    private void sendFile50FileSender() {
        ThreadUtils.execute(new Runnable() {
            @Override
            public void run() {
                LoggerUtils.loge(WifiHotspotReceiveFilesActivity.this, "sendFile50FileSender");
                try {
                    // 确保wifi连接后获取正确ip地址
                    String serverIp = WifiUtils.getIpAddressFromHotSpot();
                    if (mDatagramSocket == null) {
                        //解决：java.net.BindException: bind failed: EADDRINUSE (Address already in
                        // use)
                        mDatagramSocket = new DatagramSocket(null);
                        mDatagramSocket.setReuseAddress(true);
                        mDatagramSocket.bind(new InetSocketAddress(Const.DEFAULT_SERVER_UDP_PORT));
                    }

                    // 发送选中的文件列表
                    InetAddress ipAddress = InetAddress.getByName(serverIp);
                    String jsonStr = FileInfo.toJsonStr(mSendFileInfos);
                    DatagramPacket sendPacket = new DatagramPacket(jsonStr.getBytes(),
                            jsonStr.getBytes().length, ipAddress,
                            Const.DEFAULT_SERVER_UDP_PORT);
                    mDatagramSocket.send(sendPacket);

                    LoggerUtils.loge(WifiHotspotReceiveFilesActivity.this, "Send Msg To " +
                            "FileSender ------->>>" + jsonStr);

                    // 发送开始发送文件指令
                    byte[] sendData = Const.MSG_START_SEND.getBytes(BaseTransfer.UTF_8);
                    DatagramPacket sendPacket2 = new DatagramPacket(sendData, sendData.length,
                            ipAddress, Const
                            .DEFAULT_SERVER_UDP_PORT);
                    mDatagramSocket.send(sendPacket2);
                    LoggerUtils.loge(WifiHotspotReceiveFilesActivity.this, "Send Msg To " +
                            "FileSender ------->>>" + sendData);
                } catch (SocketException e) {
                    e.printStackTrace();
                    LoggerUtils.loge(WifiHotspotReceiveFilesActivity.this, "SocketException err " +
                            "msg = " + e.getMessage());
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                    LoggerUtils.loge(WifiHotspotReceiveFilesActivity.this, "UnknownHostException " +
                            "err " +
                            "msg = " + e.getMessage());
                } catch (IOException e) {
                    e.printStackTrace();
                    LoggerUtils.loge(WifiHotspotReceiveFilesActivity.this, "IOException err " +
                            "msg = " + e.getMessage());
                }
            }
        });
    }

    /**
     * 设置接收文件列表适配器
     */
    private void setupReceiveFilesAdapter() {
        List<Map.Entry<String, FileInfo>> fileInfoList = mAppInstance
                .getReceiverFileInfoMap();
        Collections.sort(fileInfoList, Const.DEFAULT_COMPARATOR);
        // 设置适配器
        if (mReceiveFilesAdapter == null) {
            mReceiveFilesAdapter = new BaseQuickAdapter<Map.Entry<String, FileInfo>,
                    BaseViewHolder>(R.layout.item_files_selector, fileInfoList) {
                @Override
                protected void convert(BaseViewHolder holder, Map.Entry<String, FileInfo> item) {
                    final FileInfo fileInfo = item.getValue();
                    //文件路径
                    holder.setText(R.id.tv_item_files_selector_file_path, fileInfo.getFilePath());
                    //文件大小
                    holder.setText(R.id.tv_item_files_selector_size, FileUtils.formatFileSize
                            (fileInfo.getSize()));
                    //文件接收状态
                    if (fileInfo.getProgress() >= 100) {
                        holder.setText(R.id.tv_item_files_selector_status, "接收完毕");
                    } else if (fileInfo.getProgress() == 0) {
                        holder.setText(R.id.tv_item_files_selector_status, "准备接收");
                    } else if (fileInfo.getProgress() < 100) {
                        holder.setText(R.id.tv_item_files_selector_status, "正在接收");
                    } else {
                        holder.setText(R.id.tv_item_files_selector_status, "接收失败");
                    }
                    //文件接收进度
                    ProgressBar progressBar = holder.getView(R.id.pb_item_files_selector);
                    progressBar.setProgress(fileInfo.getProgress());

                    //选中文件
                    CheckBox checkBox = holder.getView(R.id.cb_item_files_selector);
                    checkBox.setOnCheckedChangeListener(new CompoundButton
                            .OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                            if (isChecked) {
                                mSendFileInfos.add(fileInfo);
                            } else {
                                mSendFileInfos.remove(fileInfo);
                            }
                            //选中的文件个数大于零才可点击底部按钮
                            btnSendFileList.setEnabled(mSendFileInfos.size() > 0);
                        }
                    });
                }
            };
            rvReceiveFiles.setAdapter(mReceiveFilesAdapter);
            rvReceiveFiles.setLayoutManager(new LinearLayoutManager(mContext));
            rvReceiveFiles.addItemDecoration(new DividerItemDecoration(mContext,
                    LinearLayoutManager.VERTICAL));
        } else {
            mReceiveFilesAdapter.notifyDataSetChanged();
        }
    }

    /**
     * 告知发送端初始化完毕
     */
    private void sendInitSucessToFileSender() {
        LoggerUtils.loge(this, "sendInitSucessToFileSender");
        ThreadUtils.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    // 确保wifi连接后获取正确ip地址
                    int tryCount = 0;
                    String serverIp = WifiUtils.getIpAddressFromHotSpot();
                    while (serverIp.equalsIgnoreCase(Const.DEFAULT_UNKNOW_IP) && tryCount < Const
                            .DEFAULT_TRY_COUNT) {
                        InternalUtils.doSleep(1000);
                        serverIp = WifiUtils.getIpAddressFromHotSpot();
                        tryCount++;
                    }

                    // 是否可以ping通指定ip地址
                    tryCount = 0;
                    while (!(NetworkUtils.isAvailableByPing(serverIp)) && tryCount < Const
                            .DEFAULT_TRY_COUNT) {
                        InternalUtils.doSleep(500);
                        LoggerUtils.loge(WifiHotspotReceiveFilesActivity.this, "Try to ping " +
                                "------" + serverIp + " - " + tryCount);
                        tryCount++;
                    }

                    // 创建UDP通信
                    if (mDatagramSocket == null) {
                        //解决：java.net.BindException: bind failed: EADDRINUSE (Address already in
                        // use)
                        mDatagramSocket = new DatagramSocket(null);
                        mDatagramSocket.setReuseAddress(true);
                        mDatagramSocket.bind(new InetSocketAddress(Const.DEFAULT_SERVER_UDP_PORT));
                    }

                    // 发送初始化完毕指令
                    InetAddress ipAddress = InetAddress.getByName(serverIp);
                    byte[] sendDataBuff = Const.MSG_FILE_RECEIVER_INIT_SUCCESS.getBytes
                            (BaseTransfer.UTF_8);
                    DatagramPacket sendPacket = new DatagramPacket(sendDataBuff, sendDataBuff
                            .length, ipAddress, Const.DEFAULT_SERVER_UDP_PORT);
                    mDatagramSocket.send(sendPacket);
                    LoggerUtils.loge(WifiHotspotReceiveFilesActivity.this, "发送消息 ------->>>" + Const
                            .MSG_FILE_RECEIVER_INIT_SUCCESS);

                    // 接收文件列表
                    while (true) {
                        byte[] receiveData = new byte[1024];
                        DatagramPacket receivePacket = new DatagramPacket(receiveData,
                                receiveData.length);
                        mDatagramSocket.receive(receivePacket);
                        String response = new String(receivePacket.getData()).trim();
                        if (EmptyUtils.isNotEmpty(response)) {
                            // 发送端发来的文件列表
                            LoggerUtils.loge(WifiHotspotReceiveFilesActivity.this, "接收到的文件列表 " +
                                    "-------->>>" + response);
                            parseFileInfoList(response);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * 将字符串解析成FileInfo列表
     */
    private void parseFileInfoList(String response) {
        if (EmptyUtils.isNotEmpty(response)) {
            List<FileInfo> fileInfos = FileInfo.toObjectList(response);
            if (EmptyUtils.isNotEmpty(fileInfos)) {
                for (FileInfo fileInfo : fileInfos) {
                    if (fileInfo != null && EmptyUtils.isNotEmpty(fileInfo.getFilePath())) {
                        mAppInstance.addReceiverFileInfo(fileInfo);
                    }
                }
                mH.sendEmptyMessage(MSG_UPDATE_ADAPTER);
            }
        }
    }

    /**
     * wifi状态变换广播
     */
    private WifiBroadcastReceiver mWifiReceiver = new WifiBroadcastReceiver() {
        @Override
        protected void onWifiDisconnected() {
            LoggerUtils.loge(this, "onWifiDisconnected");
        }

        @Override
        protected void onWifiConnected(String ssid) {
            LoggerUtils.loge(this, "onWifiConnected");

            // 判断指定wifi是否连接成功
            if (ssid.equalsIgnoreCase(mSelectedSSID) && !mIsSendInitOrder) {
                // 连接成功
                setStaus("wifi连接成功...");
                // 显示发送列表，隐藏wifi选择列表
                rvChooseHotspot.setVisibility(View.GONE);
                rvReceiveFiles.setVisibility(View.VISIBLE);

                // 告知发送端，接收端初始化完毕
                mH.sendEmptyMessage(MSG_FILE_RECEIVER_INIT_SUCCESS);
                mIsSendInitOrder = true;
            } else {

            }
        }

        @Override
        protected void onScanResultsAvaliable(List<ScanResult> list) {
            LoggerUtils.loge(this, "onScanResultsAvaliable");
            // 扫描周围可用wifi成功，设置可用wifi列表
            mScanResults.clear();
            mScanResults.addAll(list);
            setupWifiAdapter();
        }

        @Override
        protected void onWifiDisabled() {
            LoggerUtils.loge(this, "onWifiDisabled");
            // wifi已关闭，清除可用wifi列表
            mSelectedSSID = "";
            mScanResults.clear();
            setupWifiAdapter();
        }

        @Override
        protected void onWifiEnabled() {
            LoggerUtils.loge(this, "onWifiEnabled");
            // Wifi已开启，开始扫描可用wifi
            setStaus("正在扫描可用Wifi...");
            WifiUtils.startScan();
        }
    };

    private BaseQuickAdapter<ScanResult, BaseViewHolder> mChooseHotspotAdapter;

    /**
     * 设置Wifi列表适配器
     */
    private void setupWifiAdapter() {
        LoggerUtils.loge(this, "setupWifiAdapter");
        if (mChooseHotspotAdapter == null) {
            mChooseHotspotAdapter = new BaseQuickAdapter<ScanResult, BaseViewHolder>(R.layout
                    .item_choose_hotspot, mScanResults) {
                @Override
                protected void convert(BaseViewHolder helper, ScanResult item) {

                }
            };
            // 设置点击事件
            mChooseHotspotAdapter.setOnItemClickListener(((adapter, view, position) -> {

            }));
            // 设置适配器
            rvChooseHotspot.setAdapter(mChooseHotspotAdapter);
            // 设置间隔
            rvChooseHotspot.addItemDecoration(new GridSpaceItemDecoration(10));
            rvChooseHotspot.setVisibility(View.VISIBLE);
        } else {
            mChooseHotspotAdapter.notifyDataSetChanged();
        }
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_wifi_hotspot_receive_files;
    }

    @Override
    protected void initView() {
        setTitle("Wifi热点传输-接收端");
    }

    @Override
    protected void addListener() {

    }

    @Override
    protected void initData() {
        mAppInstance = (FtaApp) FtaApp.getAppInstance();

        //请求权限，开启热点
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(CommonConst.PERMISSION_CONNECT_WIFI, PERMISSION_REQ_CONNECT_WIFI);

        } else {
            // TODO: 2018/4/6 高版本需要权限，低版本不需要权限
            mIsPermissionGranted = true;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mIsPermissionGranted && mWifiReceiver == null) {
            registerWifiReceiver();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mWifiReceiver != null) {
            unRegisterWifiReceiver();
        }
    }

    private void unRegisterWifiReceiver() {
        if (mWifiReceiver != null) {
            unregisterReceiver(mWifiReceiver);
            mWifiReceiver = null;
        }
    }

    @Override
    protected PermissionListener getPermissonCallBack() {
        return null;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQ_CONNECT_WIFI) {
            if (verifyPermission(permissions, grantResults)) {
                // 权限请求成功
                mIsPermissionGranted = true;
                // 开启wifi,监听wifi广播
                registerWifiReceiver();

                if (WifiUtils.isWifiEnabled()) {
                    setStaus("正在扫描可用wifi...");
                    WifiUtils.startScan();
                } else {
                    WifiUtils.openWifi();
                }

            } else {

                // 权限请求失败
                showTipsDialogWithTitle("提示", "当前应用缺少必要权限,该功能暂时无法使用.若需使用,请点击【确定】按钮前往设置中心进行权限授权",
                        ((dialog, which) -> {
                            startAppSettings();
                        }), null);

                mIsPermissionGranted = false;
                showTipsDialog("Wifi权限获取失败", ((dialog, which) -> {
                    onBackPressed();
                }));
            }
        }
    }

    /**
     * 设置状态
     */
    private void setStaus(String msg) {
        tvStatus.append(msg + "\n");
    }

    /**
     * 注册监听WIFI操作的系统广播
     */
    private void registerWifiReceiver() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION);
        intentFilter.addAction(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION);
        intentFilter.addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION);

        registerReceiver(mWifiReceiver, intentFilter);
    }

    @Override
    public void onBackPressed() {
        if (hasFileReceiving()) {
            showTipsDialog("文件正在接收,是否退出?", "是", ((dialog, which) -> {
                finishActivity();
            }), "否", null);
        } else {
            finishActivity();
        }
    }

    /**
     * 是否还有文件在接收
     */
    private boolean hasFileReceiving() {
        for (FileReceiver fileReceiver : mFileReceiverList) {
            if (fileReceiver != null && fileReceiver.isRunning()) {
                return true;
            }
        }
        return false;
    }

    /**
     * 关闭此activity
     */
    private void finishActivity() {
        // 断开UDP Socket
        closeUDPSocket();

        //停止所有文件接收任务
        stopAllFileReceivingTask();

        //断开接收文件的Socket
        closeClientSocket();

        //清除WiFi网络
        WifiUtils.clearWifiConfig();

        //清空接收文件列表
        mAppInstance.clearReceiverFileInfoMap();

        finish();
    }

    /**
     * 断开接收文件的Socket
     */
    private void closeClientSocket() {
        if (mClientSocket != null) {
            try {
                mClientSocket.close();
                mClientSocket = null;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 停止所有文件发送任务
     */
    private void stopAllFileReceivingTask() {
        for (FileReceiver fileReceiver : mFileReceiverList) {
            if (fileReceiver != null) {
                fileReceiver.stop();
            }
        }
    }

    private void closeUDPSocket() {
        if (mDatagramSocket != null) {
            mDatagramSocket.disconnect();
            mDatagramSocket.close();
            mDatagramSocket = null;
        }
    }

    /**
     * 启动当前应用设置页面
     */
    private void startAppSettings() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse("package:" + getPackageName()));
        startActivity(intent);
    }

    /**
     * 权限是否都已授权
     */
    private boolean verifyPermission(String[] permissions, int[] grantResults) {
        boolean hasAllPermissionAllow = true;
        if (permissions.length == grantResults.length) {
            for (int i = 0; i < grantResults.length; i++) {
                int grantResult = grantResults[i];
                if (grantResult != PackageManager.PERMISSION_GRANTED) {
                    LoggerUtils.loge(this, permissions[i] + ",权限拒绝了");
                    // 只要有一个没有允许就是未全部授权
                    hasAllPermissionAllow = false;
                } else {
                    LoggerUtils.loge(this, permissions[i] + ",权限允许了");
                }
            }
        }
        return false;
    }

    @OnClick({R.id.btn_receive_files})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.btn_receive_files:
                // 将选择的文件列表发送给发送端，开始接收文件
                mAppInstance.clearReceiverFileInfoMap();

                for (FileInfo fileInfo : mSendFileInfos) {
                    fileInfo.setPosition(mSendFileInfos.indexOf(fileInfo));
                    mAppInstance.addReceiverFileInfo(fileInfo);
                }
                setupReceiveFilesAdapter();
                initReceiverServer();
                break;
        }
    }

    /**
     * 开启文件接收服务
     */
    private void initReceiverServer() {
        LoggerUtils.loge(this, "initReceiverServer");
        mReceiveServerRunnable = new ReceiveServerRunnable();
        ThreadUtils.execute(mReceiveServerRunnable);
    }

    private class ReceiveServerRunnable implements Runnable {
        @Override
        public void run() {
            try {
                // 发送选择接收的文件
                mH.sendEmptyMessage(MSG_SEND_RECEIVE_FILE_LIST);

                InternalUtils.doSleep(3000);

                // 开始接收文件
                String serverIP = WifiUtils.getIpAddressFromHotSpot();
                List<Map.Entry<String, FileInfo>> fileInfoMap = mAppInstance
                        .getReceiverFileInfoMap();
                Collections.sort(fileInfoMap, Const.DEFAULT_COMPARATOR);
                for (Map.Entry<String, FileInfo> entry : fileInfoMap) {
                    // 连接发送端,逐个文件进行接收
                    int position = fileInfoMap.indexOf(entry);
                    mClientSocket = new Socket(serverIP, Const.DEFAULT_FILE_RECEIVE_SERVER_PORT);
                    FileReceiver fileReceiver = new FileReceiver(mClientSocket, entry.getValue());
//                    fileReceiver
                    // 加入线程池执行
                    mFileReceiverList.add(fileReceiver);
                    mAppInstance.MAIN_EXECUTOR.execute(fileReceiver);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
