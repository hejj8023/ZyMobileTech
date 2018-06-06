package com.example.fta;


import com.zysdk.vulture.clib.utils.LogListener;

/**
 * Created by zzg on 2018/4/7.
 */

public abstract class BaseTransfer implements Transferable, LogListener, Runnable {
    /**
     * 字节数组长度
     */
    public static final int BYTE_SIZE_HEADER = 1024 * 10;

    public static final int BYTE_SIZE_DATA = 1024 * 4;

    /**
     * 传输字节类型
     */
    public static final String UTF_8 = "UTF-8";
}
