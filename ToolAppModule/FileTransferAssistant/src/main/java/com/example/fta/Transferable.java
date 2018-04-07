package com.example.fta;

/**
 * Created by zzg on 2018/4/7.
 */

public interface Transferable {
    /**
     * 初始化
     */
    void init() throws Exception;

    /**
     * 文件发送/接收文件实体数据
     */
    void parseBody() throws Exception;

    /**
     * 发送/接收 完毕
     */
    void finishTransfer() throws Exception;
}
