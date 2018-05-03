package com.example.wav.bean;

/**
 * Created by zhiyang on 2018/4/26.
 */

public class CustomerInfo {
    private String name;
    private int id;
    private boolean checked;

    public CustomerInfo() {
    }

    public CustomerInfo(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    @Override
    public String toString() {
        return "CustomerInfo{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", checked=" + checked +
                '}';
    }
}
