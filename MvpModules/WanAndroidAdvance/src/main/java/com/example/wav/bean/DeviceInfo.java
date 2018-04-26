package com.example.wav.bean;

/**
 * Created by zhiyang on 2018/4/26.
 */

public class DeviceInfo{
    private int devId;
    private String devName;
    private int groupId;
    private int customerId;
    // 0 running, 1 stop
    private int state;

    public DeviceInfo(int devId, String devName, int groupId, int customerId, int state) {
        this.devId = devId;
        this.devName = devName;
        this.groupId = groupId;
        this.customerId = customerId;
        this.state = state;
    }

    public int getDevId() {
        return devId;
    }

    public void setDevId(int devId) {
        this.devId = devId;
    }

    public String getDevName() {
        return devName;
    }

    public void setDevName(String devName) {
        this.devName = devName;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "DeviceInfo{" +
                "devId=" + devId +
                ", devName='" + devName + '\'' +
                ", groupId=" + groupId +
                ", customerId=" + customerId +
                ", state=" + state +
                '}';
    }
}
