package com.example.wav;

/**
 * Created by example on 2018/5/15.
 */

public class XGNotification {
    private Integer id;
    private long msg_id;
    private String title;
    private String content;
    private String activity;
    private int notificationActionType;
    private String update_time;

    public XGNotification() {

    }

    public XGNotification(Integer id, Long msg_id, String title,
                          String content, String activity, int notificationActionType, String
                                  update_time) {
        super();
        this.id = id;
        this.msg_id = msg_id;
        this.title = title;
        this.content = content;
        this.activity = activity;
        this.notificationActionType = notificationActionType;
        this.update_time = update_time;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public long getMsg_id() {
        return msg_id;
    }

    public void setMsg_id(long msg_id) {
        this.msg_id = msg_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public int getNotificationActionType() {
        return notificationActionType;
    }

    public void setNotificationActionType(int notificationActionType) {
        this.notificationActionType = notificationActionType;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }
}
