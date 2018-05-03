package com.example.wav;

import com.example.wav.bean.AccountInfo;
import com.example.wav.bean.CustomerGroupInfo;
import com.example.wav.bean.CustomerInfo;
import com.example.wav.bean.DeviceInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhiyang on 2018/4/26.
 */

public class Const {

    public static final String PRE_KEY_DEFAULT_USER_ID = "default_user_id";
    public static final String PRE_KEY_DEFAULT_GROUP_ID = "default_group_id";


    public static class API_URL_CONFIG {
        public static final String BASEURL = "http://www.frigate-iot.com/";
        public static final String COMMON_PREFIX = "MonitoringCentre/";

        /*新设备注册*/
        public static final String API_DEVICE_REGISTER_URL = "API/Register.php";


        /*app登录账号检查*/
        public static final String API_APP_LOGIN_URL2 = "login/app/login_bind.php";

        /*获取客户列表*/
        public static final String API_CUSTOMER_LIST_URL = COMMON_PREFIX +
                "Data/SelectCustomerData.php";

        /*获取客户分组列表*/
        public static final String API_CUSTOMER_GROUP_LIST_URL = COMMON_PREFIX +
                "Data/Pop-LoadGroup.php";

        /*获取设备列表*/
        public static final String API_DEVICE_LIST_URL = COMMON_PREFIX + "DList/Data/DList-Data" +
                ".php";

        /*用户注册*/
        public static final String API_ACCOUNT_REGISTER_URL = "API/CreateUser.php";
    }

    public static class TMP_DATA {
        public static String FILTER_CUSTOMER_GROUP_ID = "";
        public static AccountInfo ACCOUNT_INFO = null;
        public static List<DeviceInfo> DEV_LIST = new ArrayList<>();
        public static List<CustomerGroupInfo> CUSTOMERGROUP_LIST = new ArrayList<>();
        public static List<CustomerInfo> CUSTOMER_LIST = new ArrayList<>();
        // 筛选使用的临时 变量
        public static String FILTER_CUSTOMER_ID = "";
    }

    public class UI_ACTION {
        // 刷新 ui
        public static final int RESULT_REFRESH_UI = 1001;
        // 请求设备列表
        public static final int REQ_DEVICE_LIST = 1000;
        public static final int REQ_DEVICE_LIST2 = 1002;
    }
}
