package com.example.comicbook.bean;

public class HomeTitle extends Comic {
    // 标题名字
    private String itemTitle;

    // 标题种类
    private int titleType;

    public HomeTitle() {
    }

    public HomeTitle(String itemTitle) {
        this.itemTitle = itemTitle;
    }


    public String getItemTitle() {
        return itemTitle;
    }

    public void setItemTitle(String itemTitle) {
        this.itemTitle = itemTitle;
    }

    public int getTitleType() {
        return titleType;
    }

    public void setTitleType(int titleType) {
        this.titleType = titleType;
    }
}
