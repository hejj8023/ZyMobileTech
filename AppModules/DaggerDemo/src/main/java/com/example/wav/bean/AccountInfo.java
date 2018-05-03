package com.example.wav.bean;

/**
 * Created by example on 2018/4/27.
 */

public class AccountInfo {
    private int Flag;
    private String Name;

    public int getFlag() {
        return Flag;
    }

    public void setFlag(int flag) {
        Flag = flag;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    @Override
    public String toString() {
        return "AccountInfo{" +
                "Flag=" + Flag +
                ", Name='" + Name + '\'' +
                '}';
    }
}
