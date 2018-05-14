package com.example.player.bean;

import java.io.Serializable;

/**
 * Created by example on 2018/5/11.
 */

public class VideoBean implements Serializable{
    private static final long serialVersionUID = 3011341751307897125L;
    private String fileName;
    private String filePath;
    private String length;
    private String duration;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "VideoBean{" +
                "fileName='" + fileName + '\'' +
                ", filePath='" + filePath + '\'' +
                ", length=" + length +
                '}';
    }
}
