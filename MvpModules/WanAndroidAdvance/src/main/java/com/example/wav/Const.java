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

    public static class API_URL_CONFIG {
        public static final String BASEURL = "http://www.frigate-iot.com/";
        public static final String COMMON_PREFIX = "MonitoringCentre/";

        public static final String API_DEVICE_REGISTER_URL = "API/Register.php";
        public static final String API_APP_LOGIN_URL = "data/login_chk.php";
        public static final String API_APP_LOGIN_URL2 = "login/app/login_bind.php";
        public static final String API_CUSTOMER_LIST_URL = COMMON_PREFIX + "Data/SelectCustomerData.php";
        public static final String API_CUSTOMER_GROUP_LIST_URL = COMMON_PREFIX + "Data/Pop-LoadGroup.php";
        public static final String API_DEVICE_LIST_URL = COMMON_PREFIX + "DList/Data/DList-Data.php";
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
