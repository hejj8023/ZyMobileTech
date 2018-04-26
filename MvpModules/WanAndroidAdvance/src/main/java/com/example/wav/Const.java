package com.example.wav;

import com.example.wav.bean.CustomerGroupInfo;
import com.example.wav.bean.CustomerInfo;
import com.example.wav.bean.DeviceInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhiyang on 2018/4/26.
 */

public class Const {

    public static class URL_CONFIG {
        public static String baseUrl = "http://www.baidu.com";
    }

    public static class TMP_DATA {
        public static List<DeviceInfo> DEV_LIST = new ArrayList<>();
        public static List<CustomerGroupInfo> CUSTOMERGROUP_LIST = new ArrayList<>();
        public static List<CustomerInfo> CUSTOMER_LIST = new ArrayList<>();
    }

    public class UI_ACTION {
        // 刷新 ui
        public static final int RESULT_REFRESH_UI = 1001;
        // 请求设备列表
        public static final int REQ_DEVICE_LIST = 1000;
    }
}
