package com.example.fta;

import android.content.Context;
import android.net.DhcpInfo;
import android.net.wifi.WifiManager;

import com.zhiyangstudio.sdklibrary.common.utils.InternalUtils;

/**
 * Created by zzg on 2018/4/6.
 */

public class ApManager {
    /**
     * 获取开启热点后自身热点Ip地址
     */
    public static String getHotspotLocalIpAddress(Context context) {
        WifiManager wifiManager = (WifiManager) InternalUtils.getTargetService(Context.WIFI_SERVICE);
        DhcpInfo dhcpInfo = wifiManager.getDhcpInfo();
        if (dhcpInfo != null) {
            int address = dhcpInfo.serverAddress;
            return (address & 0XFF)
                    + "." + ((address >> 8) & 0XFF)
                    + "." + ((address >> 16) & 0XFF)
                    + "." + ((address >> 24) & 0XFF);
        }
        return null;
    }
}
