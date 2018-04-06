package com.example.fta;

import com.example.fta.bean.FileInfo;
import com.zhiyangstudio.sdklibrary.common.corel.BaseApp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zzg on 2018/4/6.
 */

public class FtaApp extends BaseApp {
    /**
     * 待发送的文件数据
     */
    private Map<String, FileInfo> mSendFileInfoMap = new HashMap<>();

    /**
     * 获取待发送的文件map
     */
    public List<Map.Entry<String, FileInfo>> getSendFileInfoMap() {
        List<Map.Entry<String, FileInfo>> fileInfoMapList = new ArrayList<>(mSendFileInfoMap.entrySet());
        return fileInfoMapList;
    }
}
