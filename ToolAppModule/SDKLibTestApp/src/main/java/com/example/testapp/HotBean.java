package com.example.testapp;

/**
 * Created by example on 2018/4/18.
 */

public class HotBean {
    // 0: 文字 1,图片,2,文字+图片
    private int type;
    private String title;
    private String imgUrl;
    private String contentTxt;

    public HotBean() {
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getContentTxt() {
        return contentTxt;
    }

    public void setContentTxt(String contentTxt) {
        this.contentTxt = contentTxt;
    }

    @Override
    public String toString() {
        return "HotBean{" +
                "type=" + type +
                ", title='" + title + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", contentTxt='" + contentTxt + '\'' +
                '}';
    }
}
