package com.example.daw.bean;

import java.util.List;

/**
 * Created by example on 2018/5/4.
 */

public class UserBean {

    /**
     * {"data":{"collectIds":[2835],"email":"","icon":"","id":4642,"password":"12345678",
     * "type":0,"username":"xfgczzg"},"errorCode":0,"errorMsg":""}
     */
    private List<Integer> collectIds;
    private String email;
    private String icon;
    private String id;
    private String password;
    private int type;
    private String username;

    public List<Integer> getCollectIds() {
        return collectIds;
    }

    public void setCollectIds(List<Integer> collectIds) {
        this.collectIds = collectIds;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "UserBean{" +
                "collectIds=" + collectIds +
                ", email='" + email + '\'' +
                ", icon='" + icon + '\'' +
                ", id=" + id +
                ", password='" + password + '\'' +
                ", type=" + type +
                ", username='" + username + '\'' +
                '}';
    }
}
