package com.example.sash.ui.activity;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.p2p.WifiP2pManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.Menu;
import android.view.MenuItem;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.sash.Const;
import com.example.sash.R;
import com.example.sash.WifiDirectBroadcastReceiver;
import com.example.sash.mvp.WifiContract;
import com.example.sash.mvp.presenter.WifiPresenter;
import com.example.sash.ui.widget.WifiConnectDialog;
import com.zhiyangstudio.commonlib.components.receiver.WifiBroadcastReceiver;
import com.zhiyangstudio.commonlib.refreshsupport.extsupport.BaseMVPToolbarSupportSRListActivity;
import com.zhiyangstudio.commonlib.utils.LoggerUtils;
import com.zhiyangstudio.commonlib.utils.ThreadUtils;
import com.zhiyangstudio.commonlib.utils.UiUtils;
import com.zhiyangstudio.commonlib.utils.WifiUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by example on 2018/5/23.
 */

public class SampleWifiActivity extends BaseMVPToolbarSupportSRListActivity<WifiPresenter,
        WifiContract.IWifiView, ScanResult> implements WifiContract.IWifiView {
    private WifiP2pManager mManager;
    private WifiP2pManager.Channel mChannel;
    private WifiDirectBroadcastReceiver mReceiver;
    private IntentFilter mIntentFilter;
    private WifiBroadcastReceiver mWifiBroadcastReceiver;

    @Override
    protected String getCurrentTitle() {
        return "Wifi管理";
    }

    @Override
    public void beforeCreate() {
        super.beforeCreate();

        /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{Manifest.permission.ACCESS_WIFI_STATE, Manifest
                    .permission
                    .CHANGE_WIFI_MULTICAST_STATE, Manifest.permission.CHANGE_WIFI_STATE}, Const
                    .PERMISSION_REQ_WIFI);
        }*/

        // TODO: 2018/5/24 6.0以上的手机，要想获取可用的wifi列表，必权限必须要加，要不然就获取不到，真他娘的坑
        if (ContextCompat.checkSelfPermission(this, Manifest.permission_group.LOCATION) !=
                PackageManager.PERMISSION_GRANTED) {
            // 获取wifi连接需要定位权限,没有获取权限
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_WIFI_STATE,
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
                // TODO: 2018/5/24 子线程中扫描，要不然界面会卡顿
                ThreadUtils.executeBySingleThread(new Runnable() {
                    @Override
                    public void run() {
//                        scanWifi();
                        WifiUtils.startScan();
                        UiUtils.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                List<ScanResult> wifiList = WifiUtils.getWifiList();
                                UiUtils.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        setData(wifiList);
                                    }
                                }, 150);
                            }
                        }, 80);
                    }
                });
                break;
            case R.id.action_stop_scan:
                break;
            case R.id.action_close_wifi:
                WifiUtils.closeWifi();
                break;
            case R.id.action_create_hot:
                break;
            case R.id.action_del_hot:
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
                LoggerUtils.loge("Wifi onWifiConnected");
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
    protected BaseQuickAdapter<ScanResult, BaseViewHolder> getListAdapter() {
        return new BaseQuickAdapter<ScanResult, BaseViewHolder>(R.layout.layout_item_list_wifi,
                mList) {
            @Override
            protected void convert(BaseViewHolder helper, ScanResult item) {
                if (item != null) {
                    // 如果判断当前是否是处于连接状态
                    helper.setText(R.id.tv_wifi_name, item.SSID);
                    helper.setOnClickListener(R.id.rl_root_item, v -> {
                        WifiConnectDialog.show(SampleWifiActivity.this, item.SSID, new
                                DialogInterface
                                        .OnCancelListener() {
                                    @Override
                                    public void onCancel(DialogInterface dialog) {

                                    }
                                });
                    });
                }
            }
        };
    }

    private void scanWifi() {
        WifiUtils.startScan();
        List<ScanResult> list = WifiUtils.getScanResults();
        if (list != null && list.size() > 0) {
            LoggerUtils.loge("Wifi onScanResultsAvaliable list = " + list.size());
            // 过滤，其实是对数据进行了排序
            // 获取可用的wifi,信号强度在80以上
            List<ScanResult> results = WifiUtils.getAvaliableWifiScanList(list);
            //得到扫描结果
            // 得到配置好的网络连接
            List<WifiConfiguration> mWifiConfiguration = WifiUtils
                    .getConfiguredNetworks();

            int wifiState = WifiUtils.getWifiState();
            if (results == null) {
                if (wifiState == 3) {
                    ToastUtils.showShort("当前区域没有无线网络");
                } else if (wifiState == 2) {
                    ToastUtils.showShort("wifi正在开启，请稍后扫描");
                } else {
                    ToastUtils.showShort("WiFi没有开启");
                }
            } else {
                List<ScanResult> mWifiList = new ArrayList();
                for (ScanResult result : results) {
                    if (result.SSID == null || result.SSID.length() == 0 ||
                            result.capabilities.contains("[IBSS]")) {
                        continue;
                    }
                    boolean found = false;
                    for (ScanResult item : mWifiList) {
                        if (item.SSID.equals(result.SSID) && item.capabilities
                                .equals(result.capabilities)) {
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        mWifiList.add(result);
                    }
                }
                UiUtils.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        setData(mWifiList);
                    }
                }, 300);
            }

        }
    }
}
