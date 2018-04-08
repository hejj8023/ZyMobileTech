package com.example.fta;

import com.example.fta.bean.FileInfo;
import com.example.fta.utils.FileUtils;
import com.zhiyangstudio.sdklibrary.utils.LoggerUtils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by zzg on 2018/4/7.
 */

public class FileReceiver extends BaseTransfer {
    /**
     * 用来控制线程暂停、恢复
     */
    private final Object LOCK = new Object();
    private Socket mSocket;
    private FileInfo mFileInfo;
    /**
     * 该线程是否执行完毕
     */
    private boolean mIsFinish;
    /**
     * 设置未执行线程的不执行标识
     */
    private boolean mIsStop;
    private OnReceiveListener mOnReceiveListener;
    private InputStream mInputStream;
    private boolean mIsPaused = false;

    public FileReceiver(Socket socket, FileInfo fileInfo) {
        this.mSocket = socket;
        this.mFileInfo = fileInfo;
    }

    /**
     * 设置接收监听事件
     */
    public void setOnReceiveListener(OnReceiveListener onReceiveListener) {
        mOnReceiveListener = onReceiveListener;
    }

    @Override
    public void run() {
        if (mIsStop) {
            return;
        }

        // 初始化
        try {
            if (mOnReceiveListener != null) {
                mOnReceiveListener.onStart();
            }
            init();
        } catch (Exception e) {
            e.printStackTrace();
            LoggerUtils.loge(this, "FileReceiver init() ------->>> occur expection");
            if (mOnReceiveListener != null) {
                mOnReceiveListener.onFailure(e, mFileInfo);
            }
        }
        //发送文件实体数据
        try {
            parseBody();
        } catch (Exception e) {
            e.printStackTrace();
            LoggerUtils.loge(this, "FileReceiver parseBody() ------->>> occur expection");
            if (mOnReceiveListener != null) {
                mOnReceiveListener.onFailure(e, mFileInfo);
            }
        }
        //文件传输完毕
        try {
            finishTransfer();
        } catch (Exception e) {
            e.printStackTrace();
            LoggerUtils.loge(this, "FileReceiver finishTransfer() ------->>> occur expection");
            if (mOnReceiveListener != null) {
                mOnReceiveListener.onFailure(e, mFileInfo);
            }
        }
    }

    @Override
    public void init() throws Exception {
        if (mSocket != null) {
            mInputStream = mSocket.getInputStream();
        }
    }

    @Override
    public void parseBody() throws Exception {
        if (mFileInfo == null) {
            return;
        }

        long fileSize = mFileInfo.getSize();
        OutputStream fos = new FileOutputStream(FileUtils.gerateLocalFile(mFileInfo.getFilePath()
                , "wifi_hotspot_rec"));

        byte[] bytes = new byte[BYTE_SIZE_DATA];
        long total = 0;
        int len = 0;

        long sTime = System.currentTimeMillis();
        long eTime = 0;
        while ((len = mInputStream.read(bytes)) != -1) {
            synchronized (LOCK) {
                if (mIsPaused) {
                    try {
                        LOCK.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                //写入文件
                fos.write(bytes, 0, len);
                total = total + len;

                //每隔200毫秒返回一次进度
                eTime = System.currentTimeMillis();
                if (eTime - sTime > 200) {
                    sTime = eTime;
                    if (mOnReceiveListener != null) {
                        mOnReceiveListener.onProgress(mFileInfo, total, fileSize);
                    }
                }
            }
        }

        //文件接收成功
        if (mOnReceiveListener != null) {
            mOnReceiveListener.onSuccess(mFileInfo);
        }
        mIsFinish = true;
    }

    @Override
    public void finishTransfer() throws Exception {
        if (mInputStream != null) {
            try {
                mInputStream.close();
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
     * 暂停接收线程
     */
    public void pause() {
        synchronized (LOCK) {
            mIsPaused = true;
            LOCK.notifyAll();
        }
    }

    /**
     * 恢复接收线程
     */
    public void resume() {
        synchronized (LOCK) {
            mIsPaused = false;
            LOCK.notifyAll();
        }
    }

    /**
     * 设置当前的接收任务不执行
     */
    public void stop() {
        mIsStop = true;
    }

    /**
     * 文件是否在接收中
     */
    public boolean isRunning() {
        return !mIsFinish;
    }

    /**
     * 文件接收监听事件
     */
    public interface OnReceiveListener {
        void onStart();

        void onProgress(FileInfo fileInfo, long progress, long total);

        void onSuccess(FileInfo fileInfo);

        void onFailure(Throwable throwable, FileInfo fileInfo);
    }
}
