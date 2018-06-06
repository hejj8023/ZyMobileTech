package com.example.fta.bean;

import com.google.gson.reflect.TypeToken;
import com.zysdk.vulture.clib.utils.GsonUtils;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zzg on 2018/4/6.
 */

public class FileInfo implements Serializable {
    private static final long serialVersionUID = -231317076253207849L;

    /**
     * 文件路径
     */
    private String filePath;

    /**
     * 文件类型
     */
    private int fileType;

    /**
     * 文件大小
     */
    private long size;

    /**
     * 文件名
     */
    private String fileName;

    /**
     * 文件传送结果
     */
    private int result;

    /**
     * 传递进度
     */
    private int progress;

    /**
     * 位置
     */
    private int position;

    public FileInfo() {
    }

    public FileInfo(String pFilePath, int pFileType, long pSize, String pFileName, int pResult, int pProgress, int pPosition) {
        filePath = pFilePath;
        fileType = pFileType;
        size = pSize;
        fileName = pFileName;
        result = pResult;
        progress = pProgress;
        position = pPosition;
    }

    public static String toJsonStr(FileInfo fileInfo) {
        return GsonUtils.toJsonStr(fileInfo);
    }

    public static String toJsonStr(List<FileInfo> fileInfoList) {
        return GsonUtils.toJsonStr(fileInfoList);
    }

    public static FileInfo toObject(String jsonStr) {
        return GsonUtils.toObject(jsonStr, FileInfo.class);
    }

    public static List<FileInfo> toObjectList(String jsonStr) {
        return GsonUtils.toObjectList(jsonStr, new TypeToken<List<FileInfo>>() {
        });
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String pFilePath) {
        filePath = pFilePath;
    }

    public int getFileType() {
        return fileType;
    }

    public void setFileType(int pFileType) {
        fileType = pFileType;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long pSize) {
        size = pSize;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String pFileName) {
        fileName = pFileName;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int pResult) {
        result = pResult;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int pProgress) {
        progress = pProgress;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int pPosition) {
        position = pPosition;
    }

    @Override
    public String toString() {
        return "FileInfo:{" +
                "filePath='" + filePath + '\'' +
                ", fileType=" + fileType +
                ", size=" + size +
                ", position=" + position +
                '}';
    }
}
