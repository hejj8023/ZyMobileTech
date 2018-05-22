package com.example.sash.bean;

import android.os.ParcelUuid;

/**
 * Created by example on 2018/5/22.
 */

public class BluetoothBean {
    private String name;
    private String address;
    private int tupe;
    private ParcelUuid[] uuid;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getTupe() {
        return tupe;
    }

    public void setTupe(int tupe) {
        this.tupe = tupe;
    }

    public ParcelUuid[] getUuid() {
        return uuid;
    }

    public void setUuid(ParcelUuid[] uuid) {
        this.uuid = uuid;
    }
}
