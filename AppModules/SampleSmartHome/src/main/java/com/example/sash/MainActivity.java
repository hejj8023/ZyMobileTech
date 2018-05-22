package com.example.sash;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.ParcelUuid;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.sash.bean.BluetoothBean;
import com.example.sash.mvp.MainContract;
import com.example.sash.mvp.presenter.MainPrsenter;
import com.example.sash.utils.ClsUtils;
import com.orhanobut.logger.Logger;
import com.zhiyangstudio.commonlib.CommonConst;
import com.zhiyangstudio.commonlib.refreshsupport.smartrefresh.BaseMVPSRRListActivity;
import com.zhiyangstudio.commonlib.utils.LoggerUtils;
import com.zhiyangstudio.commonlib.utils.ThreadUtils;
import com.zhiyangstudio.commonlib.utils.UiUtils;
import com.zhiyangstudio.commonlib.widget.dialog.LoadingDialog;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class MainActivity extends BaseMVPSRRListActivity<MainPrsenter, MainContract.IMainView,
        BluetoothBean> implements MainContract.IMainView {

    private List<BluetoothBean> mBluetoothBeans = new ArrayList<>();
    /**
     * 蓝牙扫描广播
     */
    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        boolean isPairAction = true;
        BluetoothDevice device = null;

        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            String tipStr = "";
            // When discovery finds a device
            if (BluetoothAdapter.ACTION_DISCOVERY_STARTED.equals(action)) {
                tipStr = "蓝牙扫描过程开始...";
            } else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)) {
                tipStr = "蓝牙扫描过程结束...";
                // TODO: 2018/5/22 展示扫描的结果。不要直接使用mList
                setData(mBluetoothBeans);
            } else if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                // Get the BluetoothDevice object from the Intent
                device = intent.getParcelableExtra(BluetoothDevice
                        .EXTRA_DEVICE);
                tipStr = "发现设备" + device.getName() + "," + device.getAddress();

                String name = device.getName();

                BluetoothBean bluetoothBean = new BluetoothBean();
                bluetoothBean.setName(name);
                bluetoothBean.setAddress(device.getAddress());
                bluetoothBean.setTupe(device.getType());
                ParcelUuid[] uuids = device.getUuids();
                bluetoothBean.setUuid(uuids);
                mBluetoothBeans.add(bluetoothBean);
            } else if (BluetoothDevice.ACTION_BOND_STATE_CHANGED.equals(action)) {
                isPairAction = true;
                // 状态改变的广播
                device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                switch (device.getBondState()) {
                    case BluetoothDevice.BOND_BONDING://正在配对
                        LoggerUtils.loge("正在配对......");
                        break;
                    case BluetoothDevice.BOND_BONDED://配对结束
                        LoggerUtils.loge("完成配对");
                        connect(device);
                        break;
                    case BluetoothDevice.BOND_NONE://取消配对/未配对
                        LoggerUtils.loge("取消配对");
                    default:
                        break;
                }
            }
            LoggerUtils.loge(tipStr);
            mLoadingLayout.setTips(tipStr);
        }
    };

    private TextView mTextView;
    private BluetoothAdapter mBluetoothAdapter;

    @Override
    protected boolean hasEnableRereshAndLoadMore() {
        return false;
    }

    @Override
    protected void initOtherProperty() {
        mExtRoot.setVisibility(View.VISIBLE);
        mTextView = new TextView(mContext);
        mTextView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16);
        mTextView.setTextColor(UiUtils.getColor(R.color.cadetblue));
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
        int left = UiUtils.dp2px(5);
        layoutParams.setMargins(left, left, left, left);
        mTextView.setLayoutParams(layoutParams);
        mExtRoot.addView(mTextView);
    }

    @Override
    protected void loadRemoteData() {
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
    protected void initPageNumb() {

    }

    @Override
    protected BaseQuickAdapter<BluetoothBean, BaseViewHolder> getListAdapter() {
        return new BaseQuickAdapter<BluetoothBean, BaseViewHolder>(R.layout.layout_item_list,
                mList) {
            @Override
            protected void convert(BaseViewHolder helper, BluetoothBean item) {
                String itemName = item.getName();
                helper.setText(R.id.tv_devname, "设备名称:" + itemName);
                String itemAddress = item.getAddress();
                helper.setText(R.id.tv_devmac, "Mac地址:" + itemAddress);
                ParcelUuid[] uuid = item.getUuid();
                if (uuid != null && uuid.length > 0) {
                    StringBuilder sb = new StringBuilder();
                    for (ParcelUuid parcelUuid : uuid) {
                        sb.append(",").append(parcelUuid.getUuid());
                    }
                    if (sb.length() > 0) {
                        sb.deleteCharAt(0);
                    }
                    helper.setText(R.id.tv_uuid, "UUIDs \r\n [ " + sb.toString() + " ]");
                }
                helper.setOnClickListener(R.id.root_item, v -> {
                    if (!BluetoothAdapter.checkBluetoothAddress(itemAddress)) {
                        ToastUtils.showShort("蓝牙设备地址无效");
                        return;
                    }

                    BluetoothDevice remoteDevice = mBluetoothAdapter.getRemoteDevice(itemAddress);
                    if (remoteDevice != null) {

                        int bondState = remoteDevice.getBondState();
                        switch (bondState) {
                            case BluetoothDevice.BOND_NONE:
                                // 未配对,设备配对
                                Boolean result = null;
                                try {
                                    result = ClsUtils.createBond(BluetoothDevice.class,
                                            remoteDevice);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                                LoggerUtils.loge("result = " + result);
                                if (result) {
                                    // 发起配对成功，并不代表配对成功,因为可能被拒绝
                                    return;
                                }
                                break;
                            case BluetoothDevice.BOND_BONDED:
                                // 已配对,进行连接
                                connect(remoteDevice);
                                break;
                        }

                    }
                });

            }
        };
    }

    /**
     * 蓝牙设备连接
     *
     * @param device
     */
    private void connect(BluetoothDevice device) {
        // 固定uuid
        String SPP_UUID = "00001101-0000-1000-8000-00805F9B34FB";
        UUID uuid = UUID.fromString(SPP_UUID);
        BluetoothSocket socket = null;
        try {
            socket = device.createRfcommSocketToServiceRecord(uuid);

            // 连接需要放到子线程中
            BluetoothSocket finalSocket = socket;
            ThreadUtils.executeBySingleThread(new Runnable() {
                @Override
                public void run() {
                    try {
                        finalSocket.connect();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showWatingDialog(String s) {
        LoadingDialog.show(this, s, true, dialog -> {

        });
    }

    @Override
    public void beforeSubContentInit() {
        super.beforeSubContentInit();

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH) ==
                PackageManager.PERMISSION_DENIED) {
            //TODO 提示权限已经被禁用 且不在提示
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission
                    .BLUETOOTH)) {
                Logger.e("拒绝过蓝牙的权限，提示用户");
                showPermissionDenyDialog("蓝牙");

            } else {
                Logger.e("申请权限");
                // 申请授权。
                ActivityCompat.requestPermissions(
                        this,
                        new String[]{
                                Manifest.permission.BLUETOOTH,
                                Manifest.permission.BLUETOOTH_ADMIN,
                                Manifest.permission.BLUETOOTH_PRIVILEGED},
                        Const.PERMISSION_REQ_BT);
            }
        }

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
            int permissionCheck = this.checkSelfPermission(Manifest.permission
                    .ACCESS_FINE_LOCATION);
            permissionCheck += this.checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION);
            if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
                //注册权限
                this.requestPermissions(
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                                Manifest.permission.ACCESS_COARSE_LOCATION},
                        Const.ACCESS_LOCATION); //Any number
            } else {//已获得过权限
                //进行蓝牙设备搜索操作
            }
        }
    }

    @Override
    protected MainPrsenter createPresenter() {
        return new MainPrsenter();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mReceiver);
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
                mBluetoothAdapter.startDiscovery();
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
                    Intent enableBtIntent = new Intent();
                    enableBtIntent.setAction(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                    enableBtIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    // 设置蓝牙可见性，最多300秒
                    enableBtIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 30 *
                            1000);
                    startActivityForResult(enableBtIntent, Const.REQ_ENABLE_BT);
                }
                break;
            case R.id.action_start_scan:
                if (mBluetoothAdapter == null || !mBluetoothAdapter.isEnabled()) {
                    UiUtils.showToastSafe("当前设备不支持蓝牙或蓝牙未开启，请检查...");
                    return true;
                }

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
                    unregisterReceiver(mReceiver);
                }

                break;
            case R.id.action_close_bluebooth:
                if (mBluetoothAdapter.isEnabled()) {
                    mBluetoothAdapter.disable();
                }
                break;
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Const.REQ_ENABLE_BT) {
            LoggerUtils.loge("蓝牙开启成功");
        }
    }

    @Override
    public BluetoothAdapter getBtAdapter() {
        return mBluetoothAdapter;
    }
}
