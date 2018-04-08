package com.example.hwa.ui.activity;

import android.net.wifi.ScanResult;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.hwa.R;
import com.zhiyangstudio.commonlib.components.receiver.WifiBroadcastReceiver;
import com.zhiyangstudio.commonlib.corel.BaseActivity;
import com.zhiyangstudio.commonlib.utils.LoggerUtils;
import com.zhiyangstudio.commonlib.utils.WifiUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by example on 2018/4/8.
 */

public class WifiManageActivity extends BaseActivity {

    @BindView(R.id.tv_status)
    TextView tvStatus;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    private String connectedSSID;
    WifiBroadcastReceiver wifiBroadcastReceiver = new WifiBroadcastReceiver() {

        @Override
        protected void onWifiEnabled() {
            LoggerUtils.loge(this, "onWifiEnabled");
            setWifiStatus("Wifi已启用...");
        }

        @Override
        protected void onWifiDisabled() {
            LoggerUtils.loge(this, "onWifiDisabled");
            setWifiStatus("Wifi已禁用...");
        }

        @Override
        protected void onScanResultsAvaliable(List<ScanResult> list) {
            LoggerUtils.loge(this, "onScanResultsAvaliable");
            setWifiStatus("扫描可用的Wifi列表...");
        }

        @Override
        protected void onWifiConnected(String s) {
            LoggerUtils.loge(this, "onWifiConnected");
            connectedSSID = WifiUtils.getConnectedSSID();
            setWifiStatus("连接到可用的Wifi,SSID = " + connectedSSID);
        }

        @Override
        protected void onWifiDisconnected() {
            LoggerUtils.loge(this, "onWifiDisconnected");
            setWifiStatus("和SSID = " + connectedSSID + ",断开连接");
        }
    };

    @Override
    protected int getContentViewId() {
        return R.layout.activity_manage_wifi;
    }

    @Override
    protected PermissionListener getPermissonCallBack() {
        return null;
    }

    @Override

    protected void initView() {
        if (!WifiUtils.isWifiEnabled()) {
            setWifiStatus("wifi未启用,请先启用wifi");
        }
    }

    private void setWifiStatus(String text) {
        tvStatus.append(text + "\n");
    }

    @Override
    protected void addListener() {

    }

    @Override
    protected void initData() {
        wifiBroadcastReceiver.register();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (wifiBroadcastReceiver != null) {
            wifiBroadcastReceiver.unRegister();
        }
    }

    @OnClick({R.id.btn_action_open_wifi, R.id.btn_action_close_wifi,
            R.id.btn_action_create_wifi_hotspot, R.id.btn_action_scan_av_wifi_list,
            R.id.btn_action_close_wifi_hotspot
    })
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.btn_action_open_wifi:
                WifiUtils.closeWifiHotspot();
                WifiUtils.openWifi();
                break;
            case R.id.btn_action_close_wifi:
                WifiUtils.closeWifi();
                break;
            case R.id.btn_action_create_wifi_hotspot:
                // 参考资料:https://blog.csdn.net/a1533588867/article/details/52459284
                WifiUtils.createHotspot("test", "12345678");
                break;
            case R.id.btn_action_close_wifi_hotspot:
                // 参考资料:https://blog.csdn.net/a1533588867/article/details/52459284
                WifiUtils.closeWifi();
                WifiUtils.closeWifiHotspot();
                break;
            case R.id.btn_action_scan_av_wifi_list:
                WifiUtils.startScan();
                List<ScanResult> resultList = WifiUtils.getScanResults();
                if (resultList != null && resultList.size() > 0) {
                    // https://blog.csdn.net/a1533588867/article/details/52459996
                    // 设置适配器，点击条目连接

                    // 项目地址 :https://github.com/AndroidKun/WiFihotspot
                }
                break;
        }
    }
}
