package com.example.wav.bean;

/**
 * Created by example on 2018/4/27.
 */

public class AccountGroupInfo {
    private String id;
    private String text;
    private boolean checked;

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
        return "AccountGroupInfo{" +
                "id='" + id + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
