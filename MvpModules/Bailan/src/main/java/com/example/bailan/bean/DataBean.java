package com.example.bailan.bean;

/**
 * class describe： 数据
 */
public class DataBean {
    public DataBean(int icon, String text) {
        this.icon = icon;
        this.text = text;
    }

    private int icon;
    private String text;

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
