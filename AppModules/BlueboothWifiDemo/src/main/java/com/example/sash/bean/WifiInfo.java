package com.example.sash.bean;

import com.zysdk.vulture.clib.utils.WifiUtils;

/**
 * Created by example on 2018/5/25.
 */

public class WifiInfo {
    private String ssid;
    private boolean checked;

    private int type;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getSsid() {
        return ssid;
    }

    public void setSsid(String ssid) {
        this.ssid = ssid;
    }

    public boolean isChecked() {
        // 如果当前连接的wifi的ssid是当前对象的ssid一样，就选中
        return WifiUtils.getConnectedSSID().equals(ssid);
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
