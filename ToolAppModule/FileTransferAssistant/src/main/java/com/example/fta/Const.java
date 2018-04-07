package com.example.fta;

import android.os.Environment;

import com.example.fta.bean.FileInfo;

import java.util.Comparator;
import java.util.Map;

/**
 * Created by zzg on 2018/4/1.
 */

public class Const {
    public static final int TYPE_SERVER = 0;
    public static final int TYPE_CLIENT = 1;

    public static final int MODE_APACHE_FTP = 0;
    public static final int MODE_SWI_FTP = 1;

    /**
     * UDP通信服务端默认端口号
     */
    public static final int DEFAULT_SERVER_UDP_PORT = 8204;

    /**
     * 文件接收端监听默认端口号->TCP
     */
    public static final int DEFAULT_FILE_RECEIVE_SERVER_PORT = 8284;

    /**
     * Wifi连接成功时未分配的默认Ip地址
     */
    public static final String DEFAULT_UNKNOW_IP = "0.0.0.0";

    /**
     * 最大尝试次数
     */
    public static final int DEFAULT_TRY_COUNT = 10;

    /**
     * UDP通知：接收端初始化完毕
     */
    public static final String MSG_FILE_RECEIVER_INIT_SUCCESS = "MSG_FILE_RECEIVER_INIT_SUCCESS";

    /**
     * UDP通知：开始发送文件
     */
    public static final String MSG_START_SEND = "MSG_START_SEND";

    /**
     * 文件传输结果:成功
     */
    public static final int FLAG_STATE_FAILURE_FILE = -1;

    /**
     * 文件传输结果:失败
     */
    public static final int FLAG_STATE_SUCESS_FILE = 1;

    /**
     * 文件排序
     */
    public static final Comparator<Map.Entry<String, FileInfo>> DEFAULT_COMPARATOR = new Comparator<Map.Entry<String, FileInfo>>() {
        @Override
        public int compare(Map.Entry<String, FileInfo> o1, Map.Entry<String, FileInfo> o2) {
            FileInfo o1Value = o1.getValue();
            FileInfo o2Value = o2.getValue();
            // 大的在后面小的排在前面:-1,0,1
            if (o1Value.getPosition() > o2Value.getPosition()) {
                return 1;
            } else if (o1Value.getPosition() < o2Value.getPosition()) {
                return -1;
            } else {
                return 0;
            }
        }
    };

    /**
     * SD卡路径
     */
    public static final String SDCARD_PATH = Environment.getExternalStorageDirectory().getAbsolutePath();


}
