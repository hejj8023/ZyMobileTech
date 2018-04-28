package com.example.wav.bean;

/**
 * Created by example on 2018/4/27.
 */

public class AccountCustomerInfo {
    private int id;
    private String text;
    private boolean checked;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    @Override
    public String toString() {
        return "AccountCustomerInfo{" +
                "id=" + id +
                ", text='" + text + '\'' +
                '}';
    }
}
