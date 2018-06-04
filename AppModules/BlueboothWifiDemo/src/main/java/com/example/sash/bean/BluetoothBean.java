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

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof BluetoothBean) {
            BluetoothBean tb = (BluetoothBean) obj;
            if (this.getName().equals(tb.getName()))
                return true;
            if (this.getAddress().equals(tb.getAddress()))
                return true;
        }
        return false;
    }

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
}
