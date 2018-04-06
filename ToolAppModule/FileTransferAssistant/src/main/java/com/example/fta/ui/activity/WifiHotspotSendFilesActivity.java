package com.example.fta.ui.activity;

import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.view.View;
import android.view.ViewStub;
import android.widget.EditText;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.fta.ApManager;
import com.example.fta.Const;
import com.example.fta.FtaApp;
import com.example.fta.R;
import com.example.fta.bean.FileInfo;
import com.zhiyangstudio.sdklibrary.CommonConst;
import com.zhiyangstudio.sdklibrary.common.corel.BaseActivity;
import com.zhiyangstudio.sdklibrary.common.corel.BaseInternalHandler;
import com.zhiyangstudio.sdklibrary.common.utils.EmptyUtils;
import com.zhiyangstudio.sdklibrary.common.utils.GsonUtils;
import com.zhiyangstudio.sdklibrary.common.utils.InternalUtils;
import com.zhiyangstudio.sdklibrary.components.receiver.HotSpotBroadcastReceiver;
import com.zhiyangstudio.sdklibrary.utils.LoggerUtils;
import com.zhiyangstudio.sdklibrary.utils.UiUtils;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * Created by zzg on 2018/4/6.
 * 基于UDP服务
 */

public class WifiHotspotSendFilesActivity extends BaseActivity {

    /**
     * WiFi热点连接和创建权限请求码
     */
    protected static final int PERMISSION_REQ_CONNECT_WIFI = 3020;

    /**
     * 创建便携热点权限请求码
     */
    protected static final int PERMISSION_REQ_CREATE_HOTSPOT = 3021;

    /**
     * 更新进度条
     */
    public static final int MSG_UPDATE_PROGRESS = 0x661;

    /**
     * 更新列表适配器
     */
    public static final int MSG_UPDATE_ADAPTER = 0x662;

    /**
     * 接收端初始化成功
     */
    public static final int MSG_FILE_RECEIVER_INIT_SUCCESS = 0x663;

    /**
     * 设置当前状态
     */
    public static final int MSG_SET_STATUS = 0x664;

    @BindView(R.id.vs_send_files_open_hotspot)
    ViewStub vsOpenHotspot;

    private EditText etHotspotSsid;
    private EditText etHotspoPwd;

    // 获取创建热点权限成功
    private boolean mIsPermissionGranted;

    private HotSpotBroadcastReceiver mHotSpotBroadcastReceiver;

    /**
     * 是否初始化成功
     */
    private boolean mIsInitialized;

    @BindView(R.id.tv_send_files_status)
    TextView tvSendStatus;

    @BindView(R.id.rv_send_files)
    RecyclerView mRecyclerView;

    /**
     * Udp socket
     */
    private DatagramSocket mDatagramSocket;

    private BaseInternalHandler mH = new BaseInternalHandler(this) {
        @Override
        protected void processMessage(Message pMessage) {

            if (pMessage == null)
                return;
            switch (pMessage.what) {
                case MSG_FILE_RECEIVER_INIT_SUCCESS:
                    // 接收端初始化完毕
                    break;
                case MSG_UPDATE_PROGRESS:
                    // 更新文件发送进度
                    break;
                case MSG_UPDATE_ADAPTER:
                    // 更新列表适配器
                    break;
                case MSG_SET_STATUS:
                    //设置当前状态
                    setStatus("接收端初始化成功...");
                    // 显示发送文件视图
                    initSendFilesLayout();
                    break;
            }
        }
    };
    private FtaApp mAppInstance;

    /**
     * 显示发送文件视图
     */
    private void initSendFilesLayout() {
        vsOpenHotspot.setVisibility(View.GONE);
        mRecyclerView.setVisibility(View.VISIBLE);

        List<Map.Entry<String, FileInfo>> fileInfos = mAppInstance.getSendFileInfoMap();
        Collections.sort(fileInfos, Const.DEFAULT_COMPARATOR);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(mContext, LinearLayoutManager.VERTICAL));
        BaseQuickAdapter<FileInfo, BaseViewHolder> lQuickAdapter =
                new BaseQuickAdapter<FileInfo, BaseViewHolder>(R.layout.layout_item_wifi_hotspot_send_filelist, mAllFileInfos) {
                    @Override
                    protected void convert(BaseViewHolder helper, FileInfo item) {
                    }
                };
        mRecyclerView.setAdapter(lQuickAdapter);
    }

    /**
     * 发送端所有待发送的文件列表
     */
    private List<FileInfo> mAllFileInfos = new ArrayList<>();

    @Override
    protected int getContentViewId() {
        return R.layout.activity_wifi_hotspot_send_files;
    }

    @Override
    protected void initView() {
        setTitle("Wifi热点传输-发送端");

        View stubView = vsOpenHotspot.inflate();
        etHotspotSsid = stubView.findViewById(R.id.et_hotspot_ssid);
        etHotspoPwd = stubView.findViewById(R.id.et_hotspot_pwd);
        stubView.findViewById(R.id.btn_open_hotspot).setOnClickListener(v -> {
            openHotspot();
        });
    }

    /**
     * 开启热点
     */
    private void openHotspot() {
        Editable etSsid = etHotspotSsid.getText();
        Editable etPwd = etHotspoPwd.getText();
        if (etSsid == null || etPwd == null)
            return;

        String ssidStr = etSsid.toString().trim();
        String pwdStr = etSsid.toString().trim();
        if (EmptyUtils.isEmpty(pwdStr))
            return;

        if (EmptyUtils.isEmpty(ssidStr)) {
            ssidStr = Build.MODEL;
        }

        // 是否有权限
        if (mIsPermissionGranted) {

        } else {

        }
    }

    /**
     * 发送数据给客户端
     */
    private void sendFile() {

    }

    @Override
    protected void addListener() {

    }

    @Override
    protected void initData() {

        mAppInstance = (FtaApp) FtaApp.getAppInstance();

        //请求权限，开启热点
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{CommonConst.ACTION_HOTSPOT_STATE_CHANGED}, PERMISSION_REQ_CREATE_HOTSPOT);

        } else {
            // TODO: 2018/4/6 高版本需要权限，低版本不需要权限
            mIsPermissionGranted = true;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mIsPermissionGranted && mHotSpotBroadcastReceiver == null) {
            // TODO: 2018/4/6 注册热点广播
            registerHotSpotReceiver();
        }
    }

    private void registerHotSpotReceiver() {
        if (mHotSpotBroadcastReceiver == null) {
            mHotSpotBroadcastReceiver = new HotSpotBroadcastReceiver() {
                @Override
                protected void onHotSpotEnabled() {
                    if (!mIsInitialized) {
                        mIsInitialized = true;
                        setStatus("成功开启热点...");

                        tvSendStatus.postDelayed(() -> {
                            setStatus("正在等待连接....");

                            // TODO: 2018/4/6 等待接收端连接
                            Runnable runnable = receiveInitSuccessOrderRunnable();
                        }, 2000);
                    }
                }
            };
        }

        IntentFilter filter = new IntentFilter(CommonConst.ACTION_HOTSPOT_STATE_CHANGED);
        registerReceiver(mHotSpotBroadcastReceiver, filter);
    }

    /**
     * 等待接收端发送初始化完成指令线程
     */
    private Runnable receiveInitSuccessOrderRunnable() {
        return () -> {
            //开始接收接收端发来的指令
            try {
                receiveInitSuccessOrder(Const.DEFAULT_SERVER_UDP_PORT);
            } catch (IOException pE) {
                pE.printStackTrace();
                LoggerUtils.loge(WifiHotspotSendFilesActivity.this, "receiveInitSuccessOrderRunnable err msg = " + pE.getMessage());
            }
        };
    }

    /**
     * 等待接收端发送初始化完成指令，向其发送文件列表
     */
    private void receiveInitSuccessOrder(int serverPort) throws IOException {
        // 确保wifi连接后获取正确ip地址
        int tryCount = 0;
        String localIp = ApManager.getHotspotLocalIpAddress(UiUtils.getContext());
        LoggerUtils.loge(this, "localIp = " + localIp);

        // TODO: 2018/4/6 获取热点的ip地址，如果获取的是0.0.0.0并且未到达最大尝试次数，就尝试再次获取
        while (localIp.equals(Const.DEFAULT_UNKNOW_IP) && tryCount > Const.DEFAULT_TRY_COUNT) {
            InternalUtils.doSleep(1000);
            localIp = ApManager.getHotspotLocalIpAddress(UiUtils.getContext());
            tryCount++;
        }

        String finalLocalIp = localIp;
        tvSendStatus.post(() -> {
            tvSendStatus.append("当前热点:ip->" + finalLocalIp);
        });

        /*使用UDP发送和接收指令*/
        mDatagramSocket = new DatagramSocket(serverPort);
        while (true) {
            byte[] receiveDataBuff = new byte[1024];
            // 把接收到的数据存储到这个DatagramPacket中
            DatagramPacket receiveDp = new DatagramPacket(receiveDataBuff, 0, receiveDataBuff.length);
            // TODO: 2018/4/6 这里是阻塞式的，有数据来的时候会存储到 DatagramPacket中
            mDatagramSocket.receive(receiveDp);
            String response = new String(receiveDp.getData()).trim();
            InetAddress lAddress = receiveDp.getAddress();
            LoggerUtils.loge(this, "client ip = " + lAddress.getHostName() + ", response = " + response);
            if (EmptyUtils.isNotEmpty(response)) {
                // TODO: 2018/4/6 接收端初始化成功的指令
                if (response.equalsIgnoreCase(Const.MSG_FILE_RECEIVER_INIT_SUCCESS)) {
                    mH.sendEmptyMessage(MSG_FILE_RECEIVER_INIT_SUCCESS);
                    // 发送文件列表
                    int lPort = receiveDp.getPort();
                    // 通过UDP发送文件列表给接收端
                    sendFileInfoListToFileReceiverWithUdp(lAddress, lPort);
                } else if (response.equalsIgnoreCase(Const.MSG_START_SEND)) {
                    // 开始发送指令
                } else {
                    // 接收端发来的待发送的文件列表
                }
            }
        }
    }

    /**
     * 通过UDP发送文件列表给接收端
     */
    private void sendFileInfoListToFileReceiverWithUdp(InetAddress pAddress, int pPort) {
        if (EmptyUtils.isNotEmpty(mAllFileInfos)) {
            String json = GsonUtils.toJsonStr(mAllFileInfos);
            LoggerUtils.loge(this, "json = " + json);
            if (EmptyUtils.isNotEmpty(json)) {
                DatagramPacket sendFileInfoPacket = new DatagramPacket(json.getBytes(), json.getBytes().length, pAddress, pPort);
                try {
                    // TODO: 2018/4/6 发送文件列表给接收端
                    mDatagramSocket.send(sendFileInfoPacket);
                    LoggerUtils.loge(this, "发送消息----->>>> " + json + "=== Sucess!!");
                    mH.obtainMessage(MSG_SET_STATUS, "成功发送文件列表...").sendToTarget();
                } catch (IOException pE) {
                    pE.printStackTrace();
                    LoggerUtils.loge(this, "发送消息 --------->>>" + json + "=== 失败！");
                }
            }
        }
    }

    /**
     * 设置状态
     */
    private void setStatus(String status) {
        tvSendStatus.append(status + "\n");
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mHotSpotBroadcastReceiver != null) {
            // TODO: 2018/4/6 反注册热点广播
            unregisterHotSpotBroadcastReceiver();
        }
    }

    private void unregisterHotSpotBroadcastReceiver() {
        if (mHotSpotBroadcastReceiver != null) {
            unregisterReceiver(mHotSpotBroadcastReceiver);
            mHotSpotBroadcastReceiver = null;
        }
    }

    @Override
    protected PermissionListener getPermissonCallBack() {
        return null;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQ_CREATE_HOTSPOT:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    mIsPermissionGranted = true;
                } else {
                    mIsPermissionGranted = false;
                }
                break;
        }
    }
}
