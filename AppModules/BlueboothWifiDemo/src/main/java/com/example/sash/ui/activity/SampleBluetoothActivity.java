package com.example.sash.ui.activity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.ParcelUuid;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.sash.Const;
import com.example.sash.R;
import com.example.sash.adapter.BtAdapter;
import com.example.sash.bean.BluetoothBean;
import com.example.sash.mvp.MainContract;
import com.example.sash.mvp.presenter.MainPrsenter;
import com.zysdk.vulture.clib.CommonConst;
import com.zysdk.vulture.clib.refreshsupport.extsupport.BaseMVPToolbarSupportSRListActivity;
import com.zysdk.vulture.clib.utils.EmptyUtils;
import com.zysdk.vulture.clib.utils.LoggerUtils;
import com.zysdk.vulture.clib.utils.UiUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;


public class SampleBluetoothActivity extends BaseMVPToolbarSupportSRListActivity<MainPrsenter,
        MainContract.IMainView, BluetoothBean> implements MainContract.IMainView {

    public static int lastSelectIndex = -1;
    private List<BluetoothBean> mBluetoothBeans = new ArrayList<>();
    private TextView mTextView;
    private BluetoothAdapter mBluetoothAdapter;
    private ServerBtThread mServerBtThread;
    private boolean isServerModel;
    /**
     * 蓝牙扫描广播
     */
    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        boolean isPairAction = true;
        BluetoothDevice device = null;

        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            LoggerUtils.loge("action = " + action);
            String tipStr = "";
            // When discovery finds a device
            if (BluetoothAdapter.ACTION_DISCOVERY_STARTED.equals(action)) {
                tipStr = "蓝牙扫描过程开始...";

                ensureDiscoverable();
            } else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)) {
                tipStr = "蓝牙扫描过程结束...";
                setData(mBluetoothBeans);
            } else if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                // Get the BluetoothDevice object from the Intent
                device = intent.getParcelableExtra(BluetoothDevice
                        .EXTRA_DEVICE);
                tipStr = "发现设备" + device.getName() + "," + device.getAddress();

                String name = device.getName();

                // 获取不到name即为无效设备，列表上只展示有效设备，有效设备才可以配对和数据传递
                if (EmptyUtils.isNotEmpty(name)) {
                    BluetoothBean bluetoothBean = new BluetoothBean();
                    bluetoothBean.setName(name);
                    bluetoothBean.setAddress(device.getAddress());
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
                        bluetoothBean.setTupe(device.getType());
                    }
                    ParcelUuid[] uuids = device.getUuids();
                    bluetoothBean.setUuid(uuids);
                    if (!mBluetoothBeans.contains(bluetoothBean)) {
                        mBluetoothBeans.add(bluetoothBean);
                    }
                }
            } else if (BluetoothAdapter.ACTION_STATE_CHANGED.equals(action)) {
                int blueState = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, 0);
                switch (blueState) {
                    case BluetoothAdapter.STATE_TURNING_ON:
                        LoggerUtils.loge("TURNING_ON");
                        break;
                    case BluetoothAdapter.STATE_ON:
                        mTextView.append("蓝牙已打开");
                        LoggerUtils.loge("STATE_ON");

                        // TODO: 2018/5/24 可以在这里执行扫描的操作
                        isPairAction = true;
                        // 状态改变的广播
                        device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                        if (device != null) {
                            switch (device.getBondState()) {
                                case BluetoothDevice.BOND_BONDING://正在配对
                                    LoggerUtils.loge("正在配对......");
                                    break;
                                case BluetoothDevice.BOND_BONDED://配对结束
                                    LoggerUtils.loge("完成配对");
                                    // TODO: 2018/5/25 这里只是列出了一种更新列表状态的方式，有一种简单的
                                    // TODO: 2018/5/25 就是根据蓝牙的名称，从界面列表中进行匹配，然后修改对象的属性，adapter刷新
                                    // TODO: 2018/5/25 可参照wifi连接的刷新
                                    updateListItemBondState(0, "已配对");
                                    ToastUtils.showShort("完成配对");
                                    break;
                                case BluetoothDevice.BOND_NONE://取消配对/未配对
                                    LoggerUtils.loge("取消配对完成");
                                    updateListItemBondState(1, "未配对");
                                    ToastUtils.showShort("取消配对完成");
                                default:
                                    break;
                            }
                        }

                        if (isServerModel) {
                            if (mServerBtThread != null) {
                                mServerBtThread.cancel();
                                mServerBtThread.interrupt();
                            }

                            UiUtils.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    mServerBtThread = new ServerBtThread();
                                    mServerBtThread.start();
                                }
                            }, 30);
                        }
                        break;
                    case BluetoothAdapter.STATE_TURNING_OFF:
                        LoggerUtils.loge("STATE_TURNING_OFF");
                        break;
                    case BluetoothAdapter.STATE_OFF:
                        mTextView.append("蓝牙已关闭");
                        LoggerUtils.loge("STATE_OFF");
                        if (!isServerModel) {
                            resetServerThread();
                        }
                        break;
                }
            }
            LoggerUtils.loge(tipStr);
            mLoadingLayout.setTips(tipStr);
        }
    };

    private void updateListItemBondState(int state, String stateStr) {
        if (lastSelectIndex > -1) {
            RecyclerView.LayoutManager layoutManager = mRecyclerView
                    .getLayoutManager();
            View lastView = layoutManager.findViewByPosition(lastSelectIndex);
            if (lastView != null) {
                TextView textView = lastView.findViewById(R.id.tv_devtype);
                if (textView != null) {
                    textView.setText(stateStr);
                }
                View view = lastView.findViewById(R.id.tv_pair);
                View vd01 = lastView.findViewById(R.id.v_d_01);
                if (state == 1) {
                    if (view != null && view.getVisibility() != View.VISIBLE) {
                        view.setVisibility(View.VISIBLE);
                    }

                    if (vd01 != null && vd01.getVisibility() != View.VISIBLE)
                        vd01.setVisibility(View.VISIBLE);
                } else {
                    if (view != null && view.getVisibility() != View.GONE) {
                        view.setVisibility(View.GONE);
                    }
                    if (vd01 != null && vd01.getVisibility() != View.GONE)
                        vd01.setVisibility(View.GONE);
                }
            }

        }
    }

    @Override
    protected int getDividerHight() {
        return UiUtils.dp2px(3);
    }

    @Override
    protected int getDividerColor() {
        return UiUtils.getColor(R.color.white);
    }

    @Override
    protected boolean hasEnableRereshAndLoadMore() {
        return false;
    }

    @Override
    protected void initOtherProperty() {
        mExtRoot.setVisibility(View.VISIBLE);
        View headerView = UiUtils.inflateView(R.layout.layout_header_bt);
        mTextView = headerView.findViewById(R.id.tv_title);

        RadioGroup radioGroup = headerView.findViewById(R.id.rg_mode);
        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {
                case R.id.rb_bt_client:
                    isServerModel = false;
                    break;
                case R.id.rb_bt_server:
                    // 开启server服务
                    isServerModel = true;
                    break;
            }
        });

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
        int left = UiUtils.dp2px(5);
        layoutParams.setMargins(left, left, left, left);
        headerView.setLayoutParams(layoutParams);
        mExtRoot.addView(headerView);
    }

    @Override
    protected void loadRemoteData() {
        showEmpty();
    }

    @Override
    protected void initPageNumb() {

    }

    @Override
    protected BaseQuickAdapter<BluetoothBean, BaseViewHolder> getListAdapter() {
        return new BtAdapter(mList, mBluetoothAdapter, mRecyclerView);
    }

    @Override
    public void beforeSubContentInit() {
        super.beforeSubContentInit();

        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        // 用BroadcastReceiver来取得搜索结果
        IntentFilter intent = new IntentFilter();
        intent.addAction(BluetoothDevice.ACTION_FOUND);//搜索发现设备
        intent.addAction(BluetoothDevice.ACTION_BOND_STATE_CHANGED);//状态改变
        intent.addAction(BluetoothAdapter.ACTION_SCAN_MODE_CHANGED);//行动扫描模式改变了
        intent.addAction(BluetoothAdapter.ACTION_STATE_CHANGED);//动作状态发生了变化
        intent.addAction(BluetoothAdapter.ACTION_DISCOVERY_STARTED);
        intent.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        registerReceiver(mReceiver, intent);
    }

    @Override
    protected MainPrsenter createPresenter() {
        return new MainPrsenter();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // TODO: 2018/5/24 如果在扫描设备就取消设备的扫描
        if (mBluetoothAdapter.isDiscovering()) {
            mBluetoothAdapter.cancelDiscovery();
        }
        unregisterReceiver(mReceiver);
        if (isServerModel)
            resetServerThread();
        if (mBluetoothAdapter.isEnabled()) {
            mBluetoothAdapter.disable();
        }
    }

    private void resetServerThread() {
        if (mServerBtThread != null) {
            mServerBtThread.cancel();
            mServerBtThread.interrupt();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        checkBtStaus();
    }

    private void checkBtStaus() {
        if (mBluetoothAdapter == null) {
            mTextView.append("当前设备不支持蓝牙\r\n");
        } else {
            mTextView.setText("");
            mTextView.append("当前设备支持蓝牙\r\n");
            if (mBluetoothAdapter.isEnabled()) {
                mTextView.append("蓝牙已开启\r\n");
                // 如果蓝牙是开启的就直接扫描，将扫描结果展示在界面 上
            } else {
                mTextView.append("蓝牙未开启\r\n");
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        StringBuilder sb = new StringBuilder();
        if (requestCode == Const.PERMISSION_REQ_BT) {
            sb = checkPermissionStatus(permissions, grantResults);
            if (sb.length() > 0) {
                showPermissionDenyDialog(sb.toString() + " 权限");
            }
        }

        if (requestCode == Const.ACCESS_LOCATION) {
            sb = checkPermissionStatus(permissions, grantResults);
            if (sb.length() > 0) {
                showPermissionDenyDialog(sb.toString() + " 权限");
            }
        }
    }

    private StringBuilder checkPermissionStatus(@NonNull String[] permissions, @NonNull int[]
            grantResults) {
        StringBuilder sb = new StringBuilder();
        if (permissions.length == grantResults.length) {
            for (int i = 0; i < permissions.length; i++) {
                if (grantResults[i] == CommonConst.PERMISSION.RESULT_PERMISSION_DENIED) {
                    // 权限被拒绝
                    sb.append(",").append(permissions[i]);
                }
            }
            sb.deleteCharAt(0);
        }
        return sb;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_open_bluebooth:
                // TODO: 2018/5/22 判断当前设备是否支持蓝牙操作
                if (mBluetoothAdapter == null) {
                    // 设备不支持蓝牙
                    showTipsDialogWithTitle(
                            "警告",
                            "当前设备不支持蓝牙操作",
                            (dialog, whitch) -> {
                                dialog.dismiss();
                            },
                            (dialog, whitch) -> {
                                dialog.dismiss();
                            });
                    return true;
                }

                // TODO: 2018/5/22 打开蓝牙
                if (!mBluetoothAdapter.isEnabled()) {
                    // TODO: 2018/5/23 方式A
                    Intent enableBtIntent = new Intent();
                    enableBtIntent.setAction(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                    enableBtIntent.setAction(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
                    enableBtIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    // 设置蓝牙可见性，最多300秒
                    enableBtIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 30 *
                            1000);
                    startActivityForResult(enableBtIntent, Const.REQ_ENABLE_BT);


                    // TODO: 2018/5/23 方式B  后台打开
//                    mBluetoothAdapter.enable();
                }
                break;
            case R.id.action_start_scan:
                if (mBluetoothAdapter == null || !mBluetoothAdapter.isEnabled()) {
                    UiUtils.showToastSafe("当前设备不支持蓝牙或蓝牙未开启，请检查...");
                    return true;
                }

                if (mBluetoothAdapter.isDiscovering()) {
                    UiUtils.showToastSafe("正在搜索蓝牙设备,请不要重复执行此操作...");
                    return true;
                }
                mList.clear();
                mAdapter.notifyDataSetChanged();
                mLoadingLayout.showLoding();
                // TODO: 2018/5/22 是通过发广播获取的数据
                /**
                 *  寻找蓝牙设备，android会将查找到的设备以广播形式发出去
                 */
                mBluetoothAdapter.startDiscovery();
                break;
            case R.id.action_stop_scan:
                // TODO: 2018/5/22 不用广播
                if (mBluetoothAdapter.isDiscovering()) {
                    // TODO: 2018/5/22 取消扫描
                    mBluetoothAdapter.cancelDiscovery();
                }
                break;
            case R.id.action_close_bluebooth:
                // TODO: 2018/5/23 关闭蓝牙
                if (mBluetoothAdapter.isEnabled()) {
                    mBluetoothAdapter.disable();
                }
                break;
        }
        return true;
    }

    // 使本机蓝牙在300秒内可被搜索
    private void ensureDiscoverable() {
        if (mBluetoothAdapter.getScanMode() != BluetoothAdapter
                .SCAN_MODE_CONNECTABLE_DISCOVERABLE) {
            Intent discoverableIntent = new Intent(
                    BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
            discoverableIntent.putExtra(
                    BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300);//设置被发现时间
            startActivity(discoverableIntent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_bt_main, menu);
        return true;
    }

    @Override
    public BluetoothAdapter getBtAdapter() {
        return mBluetoothAdapter;
    }

    @Override
    protected String getCurrentTitle() {
        return "蓝牙管理";
    }


    private class ServerBtThread extends Thread {

        private BluetoothServerSocket mmServerSocket;

        public ServerBtThread() {
            BluetoothServerSocket tmp = null;

            try {
                tmp = mBluetoothAdapter
                        .listenUsingRfcommWithServiceRecord("com.example.sash", Const
                                .UUID_DATA);
            } catch (IOException e) {
                e.printStackTrace();
                LoggerUtils.loge("listenUsingRfcommWithServiceRecord err msg = " + e.getMessage());
            }
            mmServerSocket = tmp;
        }

        @Override
        public void run() {
            BluetoothSocket socket = null;
            while (true) {
                if (Thread.currentThread().isInterrupted())
                    return;

                try {
                    socket = mmServerSocket.accept();
                    LoggerUtils.loge("连接的设备的:" + socket.getRemoteDevice().getName());
                } catch (IOException e) {
                    LoggerUtils.loge("accept exception = " + e.getMessage());
                    break;
                }
                // If a connection was accepted
                if (socket != null) {
                    // Do work to manage the connection (in a separate thread)

                    try {
                        OutputStream outputStream = socket.getOutputStream();
                        outputStream.write("hello\r\n".getBytes());
                        outputStream.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    try {
                        InputStream inputStream = socket.getInputStream();
                        if (inputStream != null) {
                            BufferedReader br = new BufferedReader(new
                                    InputStreamReader(inputStream));
                            String line = "";
                            while ((line = br.readLine()) != null) {
                                String text = UiUtils.getStr(R.string.tip_rec_from_client_msg) +
                                        line;
                                LoggerUtils.loge(text);
                                ToastUtils.showShort(text);
                            }

                            if (EmptyUtils.isEmpty(line)) {
                                LoggerUtils.loge("与客户端判断了连接...");
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                    break;
                }

            }

        }

        public void cancel() {
            try {
                mmServerSocket.close();
            } catch (IOException e) {
            }
        }

    }
}
