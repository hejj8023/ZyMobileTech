package com.example.fta.bean;

import java.io.Serializable;
import java.util.Calendar;

/**
 * Created by zzg on 2018/4/1.
 */

public class FtpFileBean implements Serializable{

    private static final long serialVersionUID = 7508731497801799638L;

    private long size = -1L;
    private String name = null;
    private Calendar date = null;

    public FtpFileBean() {
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "FtpFileBean{" +
                "size=" + size +
                ", name='" + name + '\'' +
                ", date=" + date +
                '}';
    }
}
