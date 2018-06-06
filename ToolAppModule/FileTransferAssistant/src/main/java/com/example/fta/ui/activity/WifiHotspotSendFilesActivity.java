package com.example.fta.ui.activity;

import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.Uri;
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
import android.widget.ProgressBar;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.fta.ApManager;
import com.example.fta.Const;
import com.example.fta.FileSender;
import com.example.fta.FtaApp;
import com.example.fta.R;
import com.example.fta.bean.FileInfo;
import com.example.fta.utils.FileUtils;
import com.zysdk.vulture.clib.CommonConst;
import com.zysdk.vulture.clib.components.receiver.HotSpotBroadcastReceiver;
import com.zysdk.vulture.clib.corel.BaseActivity;
import com.zysdk.vulture.clib.corel.BaseInternalHandler;
import com.zysdk.vulture.clib.utils.CommonUtils;
import com.zysdk.vulture.clib.utils.EmptyUtils;
import com.zysdk.vulture.clib.utils.GsonUtils;
import com.zysdk.vulture.clib.utils.LogListener;
import com.zysdk.vulture.clib.utils.LoggerUtils;
import com.zysdk.vulture.clib.utils.ThreadUtils;
import com.zysdk.vulture.clib.utils.WifiUtils;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
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
    /**
     * 创建便携热点权限请求码
     */
    protected static final int PERMISSION_REQ_CREATE_HOTSPOT = 3021;
    @BindView(R.id.vs_send_files_open_hotspot)
    ViewStub vsOpenHotspot;
    @BindView(R.id.tv_send_files_status)
    TextView tvSendStatus;
    @BindView(R.id.rv_send_files)
    RecyclerView mRecyclerView;
    private EditText etHotspotSsid;
    private EditText etHotspoPwd;
    // 获取创建热点权限成功
    private boolean mIsPermissionGranted;
    private HotSpotBroadcastReceiver mHotSpotBroadcastReceiver;
    /**
     * 是否初始化成功
     */
    private boolean mIsInitialized;
    /**
     * Udp socket
     */
    private DatagramSocket mDatagramSocket;

    private FtaApp mAppInstance;

    /**
     * 文件发送线程
     */
    private SenderServerTask mSenderServerTask;

    /**
     * 发送文件线程列表数据
     */
    private List<FileSender> mFileSenderList = new ArrayList<>();

    private BaseQuickAdapter<Map.Entry<String, FileInfo>, BaseViewHolder> sendFileAdapter;

    private BaseInternalHandler mH = new BaseInternalHandler(this) {
        @Override
        protected void processMessage(Message message) {

            if (message == null)
                return;
            switch (message.what) {
                case MSG_FILE_RECEIVER_INIT_SUCCESS:
                    // 接收端初始化完毕
                    setStatus("接收端初始化成功...");
                    // 显示发送文件视图
                    initSendFilesLayout();
                    break;
                case MSG_UPDATE_PROGRESS:
                    // 更新文件发送进度
                    int position = message.arg1;
                    int progress = message.arg2;
                    if (position >= 0 && position < sendFileAdapter.getItemCount()) {
                        updateProgress(position, progress);
                    }
                    break;
                case MSG_UPDATE_ADAPTER:
                    // 更新列表适配器
                    initSendFilesLayout();
                    break;
                case MSG_SET_STATUS:
                    //设置当前状态
                    setStatus(message.obj.toString());
                    break;
            }
        }
    };
    /**
     * 发送端所有待发送的文件列表
     */
    private List<FileInfo> mAllFileInfos = new ArrayList<>();

    /**
     * 更新进度条
     */
    private void updateProgress(int position, int progress) {
        if (position < 0 || position >= sendFileAdapter.getItemCount()) {
            return;
        }

        FileInfo fileInfo = sendFileAdapter.getData().get(position).getValue();
        fileInfo.setProgress(progress);
        if (position == mAppInstance.getSendFileInfoMap().size() - 1 && progress == 100) {
            LoggerUtils.loge(this, "所有的文件都已发送完毕");
            ToastUtils.showShort("所有文件发送完毕");
        }
    }

    /**
     * 显示发送文件视图
     */
    private void initSendFilesLayout() {
        vsOpenHotspot.setVisibility(View.GONE);
        mRecyclerView.setVisibility(View.VISIBLE);

        List<Map.Entry<String, FileInfo>> fileInfos = mAppInstance.getSendFileInfoMap();
        Collections.sort(fileInfos, Const.DEFAULT_COMPARATOR);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(mContext, LinearLayoutManager
                .VERTICAL));
        //文件路径
        //文件大小
        //文件发送状态
        //文件发送进度
        sendFileAdapter = new BaseQuickAdapter<Map.Entry<String, FileInfo>, BaseViewHolder>(
                R.layout.layout_item_wifi_hotspot_send_filelist, fileInfos) {

            @Override
            protected void convert(BaseViewHolder helper, Map.Entry<String, FileInfo> item) {
                FileInfo fileInfo = item.getValue();
                //文件路径
                helper.setText(R.id.tv_item_file_transfer_file_path, fileInfo.getFilePath());
                //文件大小
                helper.setText(R.id.tv_item_file_transfer_size, FileUtils.formatFileSize(fileInfo
                        .getSize()));
                //文件发送状态
                if (fileInfo.getProgress() >= 100) {
                    helper.setText(R.id.tv_item_file_transfer_status, "发送完毕");
                } else if (fileInfo.getProgress() == 0) {
                    helper.setText(R.id.tv_item_file_transfer_status, "准备发送");
                } else if (fileInfo.getProgress() < 100) {
                    helper.setText(R.id.tv_item_file_transfer_status, "正在发送");
                } else {
                    helper.setText(R.id.tv_item_file_transfer_status, "发送失败");
                }
                //文件发送进度
                ProgressBar progressBar = helper.getView(R.id.pb_item_file_transfer);
                progressBar.setProgress(fileInfo.getProgress());
            }
        };
        mRecyclerView.setAdapter(sendFileAdapter);
    }

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

        // ssid
        String ssidStr = etSsid.toString().trim();
        String pwdStr = etSsid.toString().trim();
        if (EmptyUtils.isEmpty(pwdStr))
            return;

        if (EmptyUtils.isEmpty(ssidStr)) {
            ssidStr = Build.MODEL;
        }

        // 是否有权限
        if (mIsPermissionGranted) {
            // 开启热点前，先关闭wifi,如果其它热点开启，先关闭
            WifiUtils.closeWifi();
            if (ApManager.isApOn()) {
                ApManager.closeAp();
            }

            // 注册热点状态接收器
            registerHotSpotReceiver();

            // 以手机型号为SSID,开启热点
            boolean isSucess = ApManager.openAp(ssidStr, pwdStr);
            if (!isSucess) {
                setStatus("创建热点失败");
            }
        } else {
            showTipsDialog("获取权限失败,开启热点", ((dialog, which) -> {
                Intent intent = new Intent("android.settings.action.MANAGE_WRITE_SETTINGS");
                intent.setData(Uri.parse("package:" + mContext.getPackageName()));
                if (intent.resolveActivity(mContext.getPackageManager()) != null) {
                    startActivity(intent);
                }
            }));
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
                            mAppInstance.MAIN_EXECUTOR.execute(runnable);
                        }, 2000);
                    }
                }
            };
        }

        IntentFilter filter = new IntentFilter(CommonConst.ACTION_HOTSPOT_STATE_CHANGED);
        registerReceiver(mHotSpotBroadcastReceiver, filter);
    }

    /**
     * 设置状态
     */
    private void setStatus(String status) {
        tvSendStatus.append(status + "\n");
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
                LoggerUtils.loge(WifiHotspotSendFilesActivity.this,
                        "receiveInitSuccessOrderRunnable err msg = " + pE.getMessage());
            }
        };
    }

    /**
     * 等待接收端发送初始化完成指令，向其发送文件列表
     */
    private void receiveInitSuccessOrder(int serverPort) throws IOException {
        // 确保wifi连接后获取正确ip地址
        int tryCount = 0;
        String localIp = ApManager.getHotspotLocalIpAddress();
        LoggerUtils.loge(this, "localIp = " + localIp);

        // TODO: 2018/4/6 获取热点的ip地址，如果获取的是0.0.0.0并且未到达最大尝试次数，就尝试再次获取
        while (localIp.equals(Const.DEFAULT_UNKNOW_IP) && tryCount > Const.DEFAULT_TRY_COUNT) {
            ThreadUtils.doSleep(1000);
            localIp = ApManager.getHotspotLocalIpAddress();
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
            DatagramPacket receiveDp = new DatagramPacket(receiveDataBuff, 0, receiveDataBuff
                    .length);
            // TODO: 2018/4/6 这里是阻塞式的，有数据来的时候会存储到 DatagramPacket中
            mDatagramSocket.receive(receiveDp);
            String response = new String(receiveDp.getData()).trim();
            InetAddress lAddress = receiveDp.getAddress();
            LoggerUtils.loge(this, "client ip = " + lAddress.getHostName() + ", response = " +
                    response);
            if (EmptyUtils.isNotEmpty(response)) {
                // TODO: 2018/4/6 接收端初始化成功的指令
                if (response.equalsIgnoreCase(Const.MSG_FILE_RECEIVER_INIT_SUCCESS)) {
                    mH.sendEmptyMessage(MSG_FILE_RECEIVER_INIT_SUCCESS);
                    // 发送文件列表
                    LoggerUtils.loge(this, "发送文件列表");
                    int lPort = receiveDp.getPort();
                    // 通过UDP发送文件列表给接收端
                    sendFileInfoListToFileReceiverWithUdp(lAddress, lPort);
                } else if (response.equalsIgnoreCase(Const.MSG_START_SEND)) {
                    // 开始发送指令
                    LoggerUtils.loge(this, "开始发送指令");
                    initSenderServer();
                } else {
                    // 接收端发来的待发送的文件列表
                    parseFileInfo(response);
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
                DatagramPacket sendFileInfoPacket = new DatagramPacket(json.getBytes(), json
                        .getBytes().length, pAddress, pPort);
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
     * 初始化发送端服务，开始发送文件
     */
    private void initSenderServer() {
        mSenderServerTask = new SenderServerTask();
        ThreadUtils.executeBySingleThread(mSenderServerTask);
    }

    /**
     * 将字符串解析成FileInfo
     */
    private void parseFileInfo(String response) {
        if (EmptyUtils.isNotEmpty(response)) {
            List<FileInfo> fileInfos = FileInfo.toObjectList(response);
            if (EmptyUtils.isNotEmpty(fileInfos)) {
                for (FileInfo fileInfo : fileInfos) {
                    if (fileInfo != null && EmptyUtils.isNotEmpty(fileInfo.getFilePath())) {
                        fileInfo.setPosition(fileInfos.indexOf(fileInfo));
                        mAppInstance.addSendFileInfo(fileInfo);
                        mH.sendEmptyMessage(MSG_UPDATE_ADAPTER);
                    }
                }
            }
        }

    }

    @Override
    protected void addListener() {

    }

    @Override
    protected void initData() {
        mAppInstance = (FtaApp) FtaApp.getAppInstance();

        //请求权限，开启热点
        if (CommonUtils.canWriteOsSetting(this)) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{CommonConst.PERMISSION_CREATE_HOTSPOT},
                        PERMISSION_REQ_CREATE_HOTSPOT);
            } else {
                // TODO: 2018/4/6 高版本需要权限，低版本不需要权限
                mIsPermissionGranted = true;
            }
        }
    }

    @Override
    protected PermissionListener getPermissonCallBack() {
        return null;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQ_CREATE_HOTSPOT:
                if (grantResults.length > 0 && grantResults[0] == PackageManager
                        .PERMISSION_GRANTED) {
                    mIsPermissionGranted = true;
                } else {
                    mIsPermissionGranted = false;
                }
                break;
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
    public void onBackPressed() {
        if (hasFileSending()) {
            showTipsDialog("文件正在发送,是否退出?", "是", ((dialog, which) -> {
                finishActivity();
            }), "否", null);
        } else {
            finishActivity();
        }
    }

    /**
     * 是否还有文件在发送中
     */
    private boolean hasFileSending() {
        for (FileSender fileSender : mFileSenderList) {
            if (fileSender != null && fileSender.isRunning()) {
                return true;
            }
        }
        return false;
    }

    /**
     * 关闭此Activity
     */
    private void finishActivity() {
        // 关闭UDP socket连接
        closeUdpSocket();

        // 停止所有文件发送任务
        stopAllFileSendingTask();

        // 关闭发送端socket
        if (mSenderServerTask != null) {
            mSenderServerTask.closeServerSocket();
            mSenderServerTask = null;
        }

        // 关闭热点
        ApManager.closeAp();

        // 清除等发送的文件列表
        mAppInstance.clearSendFileInfoMap();
        finish();
    }

    /**
     * 关闭UDP socket
     */
    private void closeUdpSocket() {
        if (mDatagramSocket != null) {
            mDatagramSocket.disconnect();
            mDatagramSocket.close();
            mDatagramSocket = null;
        }
    }

    /**
     * 停止所有文件发送任务
     */
    private void stopAllFileSendingTask() {
        for (FileSender fileSender : mFileSenderList) {
            if (fileSender != null) {
                fileSender.stop();
            }
        }
    }

    private class SenderServerTask implements Runnable, LogListener {
        private ServerSocket mServerSocket;

        @Override
        public void run() {
            try {
                // 获取待发送文件列表数据，按position索引排序
                List<Map.Entry<String, FileInfo>> fileInfoList = mAppInstance.getSendFileInfoMap();
                Collections.sort(fileInfoList, Const.DEFAULT_COMPARATOR);
                mServerSocket = new ServerSocket(Const.DEFAULT_FILE_RECEIVE_SERVER_PORT);
                // 逐个进行文件发送
                for (Map.Entry<String, FileInfo> entry : fileInfoList) {
                    FileInfo fileInfo = entry.getValue();
                    LoggerUtils.loge(this, "等待接收端连接");
                    // TODO: 2018/4/7 阻塞式方法
                    Socket socket = mServerSocket.accept();
                    FileSender fileSender = new FileSender(socket, fileInfo);
                    fileSender.setOnSendListener(new FileSender.OnSendListener() {
                        @Override
                        public void onStart() {
                            LoggerUtils.loge(this, "onStart");
                            mH.obtainMessage(MSG_SET_STATUS, "开始发送" + FileUtils.getFileName
                                    (fileInfo.getFilePath())).sendToTarget();
                        }

                        @Override
                        public void onProgress(long progress, long total) {
                            LoggerUtils.loge(this, "onProgress");
                            int i_progress = (int) (progress * 100 / total);
                            LoggerUtils.loge(this, "正在发送：" + fileInfo.getFilePath() + "\n当前进度：" +
                                    i_progress);

                            Message message = Message.obtain();
                            message.what = MSG_UPDATE_PROGRESS;
                            message.arg1 = fileInfo.getPosition();
                            message.arg2 = i_progress;
                            mH.sendMessage(message);
                        }

                        @Override
                        public void onSucess(FileInfo fileInfo) {
                            LoggerUtils.loge(this, "onSucess");

                            mH.obtainMessage(MSG_SET_STATUS, "文件：" + FileUtils.getFileName
                                    (fileInfo.getFilePath()) + "发送成功").sendToTarget();
                            fileInfo.setResult(Const.FLAG_STATE_SUCESS_FILE);
                            mAppInstance.updateSendFileInfo(fileInfo);

                            Message message = Message.obtain();
                            message.what = MSG_UPDATE_PROGRESS;
                            message.arg1 = fileInfo.getPosition();
                            message.arg2 = 100;
                            mH.sendMessage(message);
                        }

                        @Override
                        public void onFailure(Throwable throwable, FileInfo fileInfo) {
                            LoggerUtils.loge(this, "onFailure");

                            mH.obtainMessage(MSG_SET_STATUS, "文件：" + FileUtils.getFileName
                                    (fileInfo.getFilePath()) + "发送失败").sendToTarget();
                            fileInfo.setResult(Const.FLAG_STATE_FAILURE_FILE);
                            mAppInstance.updateSendFileInfo(fileInfo);

                            Message message = Message.obtain();
                            message.what = MSG_UPDATE_PROGRESS;
                            message.arg1 = fileInfo.getPosition();
                            message.arg2 = -1;
                            mH.sendMessage(message);
                        }
                    });

                    // 添加到线程池执行
                    mFileSenderList.add(fileSender);
                    mAppInstance.FILE_SENDER_EXECUTOR.execute(fileSender);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        /**
         * 关闭Socket连接
         */
        public void closeServerSocket() {
            if (mServerSocket != null) {
                try {
                    mServerSocket.close();
                    mServerSocket = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
