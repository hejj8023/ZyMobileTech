package com.example.wav.bean;

import java.util.List;

/**
 * Created by example on 2018/4/27.
 */

public class AccountDeviceInfo {
    private List<DeviceDetailInfo> rows;
    private int total;

    public List<DeviceDetailInfo> getRows() {
        return rows;
    }

    public void setRows(List<DeviceDetailInfo> rows) {
        this.rows = rows;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public static class DeviceDetailInfo {
        private String Alarm;
        private String AlarmDate;
        private String AlarmText;
        private String AlarmWord;
        private String CRCID;
        private String CustomerID;
        private String CustomerName;
        private String GroupID;
        private String GroupName;
        private String ID;
        private String Location;
        private String Name;
        private String Online;
        private String OnlineText;
        private String UpdataDate;

        public String getAlarm() {
            return Alarm;
        }

        public void setAlarm(String alarm) {
            Alarm = alarm;
        }

        public String getAlarmDate() {
            return AlarmDate;
        }

        public void setAlarmDate(String alarmDate) {
            AlarmDate = alarmDate;
        }

        public String getAlarmText() {
            return AlarmText;
        }

        public void setAlarmText(String alarmText) {
            AlarmText = alarmText;
        }

        public String getAlarmWord() {
            return AlarmWord;
        }

        public void setAlarmWord(String alarmWord) {
            AlarmWord = alarmWord;
        }

        public String getCRCID() {
            return CRCID;
        }

        public void setCRCID(String CRCID) {
            this.CRCID = CRCID;
        }

        public String getCustomerID() {
            return CustomerID;
        }

        public void setCustomerID(String customerID) {
            CustomerID = customerID;
        }

        public String getCustomerName() {
            return CustomerName;
        }

        public void setCustomerName(String customerName) {
            CustomerName = customerName;
        }

        public String getGroupID() {
            return GroupID;
        }

        public void setGroupID(String groupID) {
            GroupID = groupID;
        }

        public String getGroupName() {
            return GroupName;
        }

        public void setGroupName(String groupName) {
            GroupName = groupName;
        }

        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }

        public String getLocation() {
            return Location;
        }

        public void setLocation(String location) {
            Location = location;
        }

        public String getName() {
            return Name;
        }

        public void setName(String name) {
            Name = name;
        }

        public String getOnline() {
            return Online;
        }

        public void setOnline(String online) {
            Online = online;
        }

        public String getOnlineText() {
            return OnlineText;
        }

        public void setOnlineText(String onlineText) {
            OnlineText = onlineText;
        }

        public String getUpdataDate() {
            return UpdataDate;
        }

        public void setUpdataDate(String updataDate) {
            UpdataDate = updataDate;
        }

        @Override
        public String toString() {
            return "DeviceDetailInfo{" +
                    "Alarm='" + Alarm + '\'' +
                    ", AlarmDate='" + AlarmDate + '\'' +
                    ", AlarmText='" + AlarmText + '\'' +
                    ", AlarmWord='" + AlarmWord + '\'' +
                    ", CRCID='" + CRCID + '\'' +
                    ", CustomerID='" + CustomerID + '\'' +
                    ", CustomerName='" + CustomerName + '\'' +
                    ", GroupID='" + GroupID + '\'' +
                    ", GroupName='" + GroupName + '\'' +
                    ", ID='" + ID + '\'' +
                    ", Location='" + Location + '\'' +
                    ", Name='" + Name + '\'' +
                    ", Online='" + Online + '\'' +
                    ", OnlineText='" + OnlineText + '\'' +
                    ", UpdataDate='" + UpdataDate + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "AccountDeviceInfo{" +
                "rows=" + rows +
                ", total=" + total +
                '}';
    }
}
