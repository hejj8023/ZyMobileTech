package com.example.fta;

import com.example.fta.bean.FileInfo;
import com.zhiyangstudio.commonlib.utils.LogListener;
import com.zhiyangstudio.commonlib.utils.LoggerUtils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by zzg on 2018/4/7.
 */

public class FileSender extends BaseTransfer {
    private final Socket mSocket;
    private final FileInfo mFileInfo;
    /**
     * 用来控制线程的暂停和恢复
     */
    private final Object LOCK = new Object();
    /**
     * 设置未执行线程的不执行标识
     */
    private boolean mIsStop;
    private OnSendListener mOnSendListener;
    private BufferedOutputStream mOutputStream;
    private boolean mIsPause;
    /**
     * 该线程是否执行完毕
     */
    private boolean mIsFinish;

    public FileSender(Socket socket, FileInfo fileInfo) {
        this.mSocket = socket;
        this.mFileInfo = fileInfo;
    }


    /**
     * 设置发送监听事件
     */
    public void setOnSendListener(OnSendListener listener) {
        this.mOnSendListener = listener;
    }

    @Override
    public void run() {
        if (mIsStop) {
            return;
        }

        // 初始化
        try {
            if (mOnSendListener != null) {
                mOnSendListener.onStart();
            }
            init();
        } catch (Exception e) {
            e.printStackTrace();
            LoggerUtils.loge(this, "FileSender init() ------->>> occur expection");
            if (mOnSendListener != null) {
                mOnSendListener.onFailure(e, mFileInfo);
            }
        }

        // 发送文件实体数据
        try {
            parseBody();
        } catch (Exception e) {
            e.printStackTrace();
            LoggerUtils.loge(this, "FileSender parseBody() ------->>> occur expection");
            if (mOnSendListener != null) {
                mOnSendListener.onFailure(e, mFileInfo);
            }
        }

        // 发送传输完毕
        try {
            finishTransfer();
        } catch (Exception e) {
            e.printStackTrace();
            LoggerUtils.loge(this, "FileSender finishTransfer() ------->>> occur expection");
            if (mOnSendListener != null) {
                mOnSendListener.onFailure(e, mFileInfo);
            }
        }
    }

    @Override
    public void init() throws Exception {
        mSocket.setSoTimeout(30 * 1000);
        OutputStream os = mSocket.getOutputStream();
        mOutputStream = new BufferedOutputStream(os);
    }

    @Override
    public void parseBody() throws Exception {
        long size = mFileInfo.getSize();
        File file = new File(mFileInfo.getFilePath());
        FileInputStream fis = new FileInputStream(file);

        int len = 0;
        long total = 0;
        byte[] butes = new byte[BYTE_SIZE_DATA];

        long sTime = System.currentTimeMillis();
        long eTime = 0;

        while ((len = fis.read(butes)) != -1) {
            synchronized (LOCK) {
                try {
                    // TODO: 2018/4/7 如果暂停就等待
                    if (mIsPause) {
                        LOCK.wait();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                // 写入文件
                mOutputStream.write(butes, 0, len);
                total += len;
                // 每隔200毫秒返回一次进度
                eTime = System.currentTimeMillis();
                if (eTime - size > 200) {
                    sTime = eTime;
                    if (mOnSendListener != null) {
                        mOnSendListener.onProgress(total, size);
                    }
                }
            }
        }

        // 关闭Socket输入输出流
        mOutputStream.flush();
        mOutputStream.close();
        // 文件发送成功
        if (mOnSendListener != null) {
            mOnSendListener.onSucess(mFileInfo);
        }
        mIsFinish = true;
    }

    @Override
    public void finishTransfer() throws Exception {
        if (mOutputStream != null) {
            try {
                mOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (mSocket != null && mSocket.isConnected()) {
            try {
                mSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 暂停发送线程
     */
    public void pause() {
        synchronized (LOCK) {
            mIsPause = true;
            LOCK.notifyAll();
        }
    }

    /**
     * 恢复发送线程
     */
    public void resume() {
        synchronized (LOCK) {
            mIsPause = false;
            LOCK.notifyAll();
        }
    }

    /**
     * 设置当前的发送任务不执行
     */
    public void stop() {
        mIsStop = true;
    }

    /**
     * 文件是否在发送中
     */
    public boolean isRunning() {
        return !mIsFinish;
    }

    public interface OnSendListener extends LogListener {
        void onStart();

        void onProgress(long progress, long total);

        void onSucess(FileInfo fileInfo);

        void onFailure(Throwable throwable, FileInfo fileInfo);
    }
}
