package com.example.fta;

import com.example.common.corel.BaseActivity;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zzg on 2018/4/1.
 */

public abstract class BaseFtpActivity extends BaseActivity {
    protected FtpClientManager ftpClientManager;
    protected ExecutorService threadPool = Executors.newCachedThreadPool();
    protected FtpServerManager ftpServerManager;

    @Override
    protected void initData() {
        if (ftpServerManager == null)
            ftpServerManager = FtpServerManager.getInstance();

        if (ftpClientManager == null)
            ftpClientManager = FtpClientManager.getInstance();
    }
}
