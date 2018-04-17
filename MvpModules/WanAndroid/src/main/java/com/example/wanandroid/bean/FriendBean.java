package com.example.wanandroid.bean;

/**
 * Created by example on 2018/4/17.
 */

public class FriendBean {
    /**
     * {
     * "icon": "",
     * "id": 17,
     * "link": "http://www.wanandroid.com/article/list/0?cid=176",
     * "name": "国内大牛博客集合",
     * "order": 1,
     * "visible": 1
     * }
     */
    private String icon;
    private int id;
    private String link;
    private String name;
    private int order;
    private int visible;

    public FriendBean() {
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public int getVisible() {
        return visible;
    }

    public void setVisible(int visible) {
        this.visible = visible;
    }

    @Override
    public String toString() {
        return "FriendBean{" +
                "icon='" + icon + '\'' +
                ", id=" + id +
                ", link='" + link + '\'' +
                ", name='" + name + '\'' +
                ", order=" + order +
                ", visible=" + visible +
                '}';
    }
}
