package com.example.sash;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.p2p.WifiP2pDevice;
import android.net.wifi.p2p.WifiP2pDeviceList;
import android.net.wifi.p2p.WifiP2pManager;

import com.zhiyangstudio.commonlib.utils.LoggerUtils;

import java.util.Collection;

/**
 * Created by example on 2018/5/24.
 */

public class WifiDirectBroadcastReceiver extends BroadcastReceiver {

    private WifiP2pManager mManager;
    private WifiP2pManager.Channel mChannel;
    private Activity mActivity;


    public WifiDirectBroadcastReceiver(WifiP2pManager manager, WifiP2pManager.Channel channel,
                                       Activity activity) {
        super();
        this.mManager = manager;
        this.mChannel = channel;
        this.mActivity = activity;

    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();

        if (WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION.equals(action)) {
            LoggerUtils.loge("WIFI_P2P_STATE_CHANGED_ACTION");

            int state = intent.getIntExtra(WifiP2pManager.EXTRA_WIFI_STATE, -1);
            if (state == WifiP2pManager.WIFI_P2P_STATE_ENABLED) {
                LoggerUtils.loge("Wifi P2P is enabled");
            } else {
                LoggerUtils.loge("Wi-Fi P2P is not enabled");
            }

        } else if (WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION.equals(action)) {
            LoggerUtils.loge("WIFI_P2P_PEERS_CHANGED_ACTION");
            if (mManager != null) {
                mManager.requestPeers(mChannel, new WifiP2pManager.PeerListListener() {
                    @Override
                    public void onPeersAvailable(WifiP2pDeviceList peers) {
                        LoggerUtils.loge("onPeersAvailable");
                        if (peers != null) {
                            Collection<WifiP2pDevice> list = peers.getDeviceList();
                            if (list != null) {
                                LoggerUtils.loge("list . size = " + list.size());
                            }
                        }
                    }
                });
            }

        } else if (WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION.equals(action)) {
            LoggerUtils.loge("WIFI_P2P_CONNECTION_CHANGED_ACTION");
        } else if (WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION.equals(action)) {
            LoggerUtils.loge("WIFI_P2P_CONNECTION_CHANGED_ACTION");
        }

    }
}
