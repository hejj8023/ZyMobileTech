package com.example.wanandroid.bean;

/**
 * Created by example on 2018/4/13.
 */

public class SearchBean {
    private String name;

    public SearchBean() {
    }

    public SearchBean(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "SearchBean{" +
                "name='" + name + '\'' +
                '}';
    }
}
