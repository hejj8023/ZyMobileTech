package com.example.fta;

import com.example.fta.bean.FileInfo;
import com.zhiyangstudio.sdklibrary.common.corel.BaseApp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by zzg on 2018/4/6.
 */

public class FtaApp extends BaseApp {
    /**
     * 待发送的文件数据
     */
    private Map<String, FileInfo> mSendFileInfoMap = new HashMap<>();

    /**
     * 主线程池
     */
    public Executor MAIN_EXECUTOR = Executors.newFixedThreadPool(5);

    /**
     * 文件发送端单线程
     */
    public Executor FILE_SENDER_EXECUTOR = Executors.newSingleThreadExecutor();


    /**************************************************************************************
     ********************************************发送端************************************
     **************************************************************************************/

    /**
     * 获取待发送的文件map
     */
    public List<Map.Entry<String, FileInfo>> getSendFileInfoMap() {
        List<Map.Entry<String, FileInfo>> fileInfoMapList = new ArrayList<>(mSendFileInfoMap.entrySet());
        return fileInfoMapList;
    }

    /**
     * 添加fileinfo
     */
    public void addSendFileInfo(FileInfo fileInfo) {
        if (!mSendFileInfoMap.containsKey(fileInfo)) {
            mSendFileInfoMap.put(fileInfo.getFilePath(), fileInfo);
        }
    }

    /**
     * 更新FileInfo
     */
    public void updateSendFileInfo(FileInfo fileInfo) {
        mSendFileInfoMap.put(fileInfo.getFilePath(), fileInfo);
    }

    /**
     * 删除待发送的文件Map
     */
    public void clearSendFileInfoMap() {
        mSendFileInfoMap.clear();
    }

    /**
     * 获取待发送的文件总长度
     */
    public long getAllSendFileInfoSize() {
        long totalSize = 0;
        for (FileInfo fileInfo : mSendFileInfoMap.values()) {
            if (fileInfo != null) {
                totalSize += fileInfo.getSize();
            }
        }
        return totalSize;
    }

    /**************************************************************************************
     ********************************************接收端************************************
     **************************************************************************************/
}
