package com.example.fta;

import android.content.Context;
import android.net.DhcpInfo;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;

import com.zhiyangstudio.sdklibrary.common.utils.EmptyUtils;
import com.zhiyangstudio.sdklibrary.common.utils.InternalUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by zzg on 2018/4/7.
 */

public class WifiUtils {
    private static WifiManager sWifiManager;

    static {
        sWifiManager = (WifiManager) InternalUtils.getTargetService(Context.WIFI_SERVICE);
    }

    /**
     * 获取开启热点后自身热点Ip地址
     */
    public static String getLocalAddress() {

        DhcpInfo dhcpInfo = sWifiManager.getDhcpInfo();
        if (dhcpInfo != null) {
            int address = dhcpInfo.serverAddress;
            return (address & 0XFF)
                    + "." + ((address >> 8) & 0XFF)
                    + "." + ((address >> 16) & 0XFF)
                    + "." + ((address >> 24) & 0XFF);
        }
        return "";
    }

    /**
     * 关闭wifi
     */
    public static void closeWifi() {
        if (sWifiManager.isWifiEnabled()) {
            sWifiManager.setWifiEnabled(false);
        }
    }

    /**
     * 热点是否开启
     */
    public static boolean isApOn() {
        try {
            Method isWifiApEnabledMethod = sWifiManager.getClass().getDeclaredMethod("isWifiApEnabledMethod");
            isWifiApEnabledMethod.setAccessible(true);
            return (Boolean) isWifiApEnabledMethod.invoke(sWifiManager);
        } catch (NoSuchMethodException pE) {
            pE.printStackTrace();
        } catch (IllegalAccessException pE) {
            pE.printStackTrace();
        } catch (InvocationTargetException pE) {
            pE.printStackTrace();
        }
        return false;
    }

    /**
     * 关闭热点
     */
    public static void closeAp() {
        try {
            Method setWifiApEnabledMethod = sWifiManager.getClass().getMethod("setWifiApEnabled");
            setWifiApEnabledMethod.invoke(sWifiManager, null, false);
        } catch (NoSuchMethodException pE) {
            pE.printStackTrace();
        } catch (IllegalAccessException pE) {
            pE.printStackTrace();
        } catch (InvocationTargetException pE) {
            pE.printStackTrace();
        }
    }

    /**
     * 开启热点
     */
    public static boolean openAp(String ssidStr, String pwdStr) {
        if (EmptyUtils.isEmpty(ssidStr))
            return false;

        if (sWifiManager.isWifiEnabled()) {
            sWifiManager.setWifiEnabled(false);
        }

        WifiConfiguration wifiConfiguration = getApConfig(ssidStr, pwdStr);
        try {
            if (isApOn()) {
                sWifiManager.setWifiEnabled(false);
                closeAp();
            }

            // 使用反射开启wifi热点
            Method setWifiApEnabledMethod = sWifiManager.getClass().getMethod("setWifiApEnabled");
            setWifiApEnabledMethod.invoke(sWifiManager, wifiConfiguration, true);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 设置有密码的热点信息
     */
    private static WifiConfiguration getApConfig(String ssidStr, String pwdStr) {
        if (EmptyUtils.isEmpty(pwdStr))
            return null;
        WifiConfiguration config = new WifiConfiguration();
        config.SSID = ssidStr;
        config.preSharedKey = pwdStr;
        // config.hiddenSSID=true;
        config.status = WifiConfiguration.Status.ENABLED;
        // 组加密方式
        config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.TKIP);
        config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.CCMP);
        config.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.WPA_PSK);
        config.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.TKIP);
        config.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.CCMP);
        config.allowedProtocols.set(WifiConfiguration.Protocol.RSN);
        return config;
    }
}
