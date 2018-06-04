package com.example.sash.ui.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.net.wifi.ScanResult;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.sash.Const;
import com.example.sash.R;
import com.example.sash.WifiDirectBroadcastReceiver;
import com.example.sash.bean.WifiInfo;
import com.example.sash.mvp.WifiContract;
import com.example.sash.mvp.presenter.WifiPresenter;
import com.example.sash.ui.widget.NetConnectDialog;
import com.zhiyangstudio.commonlib.components.receiver.WifiBroadcastReceiver;
import com.zhiyangstudio.commonlib.refreshsupport.extsupport.BaseMVPToolbarSupportSRListActivity;
import com.zhiyangstudio.commonlib.utils.LoggerUtils;
import com.zhiyangstudio.commonlib.utils.UiUtils;
import com.zhiyangstudio.commonlib.utils.WifiUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by example on 2018/5/23.
 */

public class SampleWifiActivity extends BaseMVPToolbarSupportSRListActivity<WifiPresenter,
        WifiContract.IWifiView, WifiInfo> implements WifiContract.IWifiView {
    private static final int REQUEST_CODE_WRITE_SETTINGS = 1;
    private WifiP2pManager mManager;
    private WifiP2pManager.Channel mChannel;
    private WifiDirectBroadcastReceiver mReceiver;
    private IntentFilter mIntentFilter;
    private WifiBroadcastReceiver mWifiBroadcastReceiver;

    @Override
    protected String getCurrentTitle() {
        return "Wifi&热点管理";
    }

    @Override
    public void beforeCreate() {
        super.beforeCreate();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            // TODO: 2018/5/24 6.0以上的手机，要想获取可用的wifi列表，必权限必须要加，要不然就获取不到，真他娘的坑
            // 获取wifi连接需要定位权限,没有获取权限
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_WIFI_STATE,
                    Manifest.permission.CHANGE_NETWORK_STATE,
                    Manifest.permission.WRITE_SETTINGS
            }, Const.PERMISSION_REQ_WIFI);
        }
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(mReceiver);
        mWifiBroadcastReceiver.unRegister();

        super.onDestroy();
    }

    /* register the broadcast receiver with the intent values to be matched */
    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(mReceiver, mIntentFilter);

        // 动态申请权限不弹出对话框，太他娘的坑了
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!Settings.System.canWrite(this)) {
                Intent intent = new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS);
                intent.setData(Uri.parse("package:" + getPackageName()));
                startActivityForResult(intent, REQUEST_CODE_WRITE_SETTINGS);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case Const.PERMISSION_REQ_WIFI:
                if (grantResults.length > 0 && grantResults[0] == PackageManager
                        .PERMISSION_GRANTED) {// 允许
                } else {
                }
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_open_wifi:
                WifiUtils.openWifi();
                break;
            case R.id.action_start_scan:
                showLoading();
                WifiUtils.startScan();
                // TODO: 2018/5/24 子线程中扫描，要不然界面会卡顿
                mH.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        List<ScanResult> wifiList = WifiUtils.getWifiList();
                        List<WifiInfo> tList = new ArrayList<>();
                        WifiInfo wifiInfo = null;
                        for (ScanResult scanResult : wifiList) {
                            wifiInfo = new WifiInfo();
                            wifiInfo.setSsid(scanResult.SSID);
                            wifiInfo.setType(WifiUtils.getType(scanResult));
                            tList.add(wifiInfo);
                        }
                        mH.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                setData(tList);
                            }
                        }, 150);
                    }
                }, 80);
                break;
            case R.id.action_clear_wifi_list:
                WifiUtils.clearWifiConfig();
                break;
            case R.id.action_close_wifi:
                WifiUtils.closeWifi();
                break;
            case R.id.action_create_hot:
                // TODO: 2018/5/25 需要先检查是否有系统设置写入的权限
                NetConnectDialog.showWifiApCreate(SampleWifiActivity.this);
                break;
            case R.id.action_connect_hot:
                break;
            case R.id.action_del_hot:
                WifiUtils.closeWifiHotspot();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void beforeSubContentInit() {
        super.beforeSubContentInit();
        mManager = (WifiP2pManager) getSystemService(Context.WIFI_P2P_SERVICE);
        mChannel = mManager.initialize(this, getMainLooper(), null);
        mReceiver = new WifiDirectBroadcastReceiver(mManager, mChannel, this);

        mIntentFilter = new IntentFilter();
        mIntentFilter.addAction(WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION);
        mIntentFilter.addAction(WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION);
        mIntentFilter.addAction(WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION);
        mIntentFilter.addAction(WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION);

        mWifiBroadcastReceiver = new WifiBroadcastReceiver() {
            @Override
            protected void onWifiEnabled() {
                LoggerUtils.loge("Wifi onWifiEnabled");
            }

            @Override
            protected void onWifiDisabled() {
                LoggerUtils.loge("Wifi onWifiEnabled");
            }

            @Override
            protected void onScanResultsAvaliable(List<ScanResult> list) {
                LoggerUtils.loge("Wifi onScanResultsAvaliable");
            }

            @Override
            protected void onWifiConnected(String ssid) {
                LoggerUtils.loge("Wifi onWifiConnected , SSID = " + ssid);
                // TODO: 2018/5/25 将界面上标识连接状态的条目更换为当前连接的ssid
                if (ssid.equals(Const.TMP_CONNECT_SSID)) {
                    int itemIndex = -1;
                    for (int i = 0; i < mList.size(); i++) {
                        WifiInfo wifiInfo = mList.get(i);
                        if (wifiInfo.getSsid().equals(ssid)) {
                            itemIndex = i;
                            wifiInfo.setChecked(true);
                        } else {
                            wifiInfo.setChecked(false);
                        }
                        mList.set(i, wifiInfo);
                    }
                    if (itemIndex != -1) {
                        // 找到了就更新了数据，就刷新列表
                        mAdapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            protected void onWifiDisconnected() {
                LoggerUtils.loge("Wifi onWifiDisconnected");
            }
        };
        mWifiBroadcastReceiver.register();
    }

    @Override
    protected WifiPresenter createPresenter() {
        return new WifiPresenter();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_wifi_main, menu);
        return true;
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
    protected void loadRemoteData() {
        showEmpty();
    }

    @Override
    protected void initPageNumb() {

    }

    @Override
    protected BaseQuickAdapter<WifiInfo, BaseViewHolder> getListAdapter() {
        return new BaseQuickAdapter<WifiInfo, BaseViewHolder>(R.layout.layout_item_list_wifi,
                mList) {
            @Override
            protected void convert(BaseViewHolder helper, WifiInfo item) {
                if (item != null) {
                    // 如果判断当前是否是处于连接状态
                    String ssid = item.getSsid();
                    helper.setText(R.id.tv_wifi_name, ssid);
                    CheckBox checkBox = helper.getView(R.id.cb_customer_group);
                    if (item.isChecked()) {
                        checkBox.setChecked(true);
                    } else {
                        checkBox.setChecked(false);
                    }
                    helper.setOnClickListener(R.id.rl_root_item, v -> {
                        if (item.isChecked()) {
                            ToastUtils.showShort(UiUtils.getStr(R.string
                                    .tip_no_repeit_connect_wifi));
                            return;
                        }

                        // TODO: 2018/5/25 指定的wifi连接成功之后，会重新刷新界面
                        NetConnectDialog.showWifiConnect(SampleWifiActivity.this, ssid, item
                                .getType());
                    });
                }
            }
        };
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_WRITE_SETTINGS) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (Settings.System.canWrite(this)) {
                    LoggerUtils.loge("onActivityResult write settings granted");
                }
            }
        }
    }
}
