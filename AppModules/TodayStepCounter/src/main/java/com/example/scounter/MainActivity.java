package com.example.scounter;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.telecom.ConnectionService;
import android.view.View;
import android.widget.TextView;

import com.example.algorithm.utils.LoggerUtils;
import com.example.common.corel.BaseActivity;
import com.example.scounter.listeners.UpdateUiCallback;
import com.example.scounter.service.StepServices;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    private static final int UPDATE_UI = 0x110;
    private boolean mIsRunning;
    public StepServices mService;

    @BindView(R.id.tv_result)
    TextView textView;

    private Handler mH = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == UPDATE_UI) {
                textView.setText(sp.getString("steps", ""));
            }
        }
    };

    private UpdateUiCallback mUiCallback = new UpdateUiCallback() {
        @Override
        public void updateUi() {
            LoggerUtils.loge(MainActivity.this, "updateUi");

            mH.sendEmptyMessage(UPDATE_UI);

        }
    };
    private ServiceConnection mConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            LoggerUtils.loge(MainActivity.this, "onServiceConnected");
            StepServices.StepBinder binder = (StepServices.StepBinder) service;
            mService = binder.getService();
            mService.registerCallback(mUiCallback);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            LoggerUtils.loge(MainActivity.this, "onServiceDisconnected");
        }
    };
    private SharedPreferences sp;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void addListener() {

    }

    @Override
    protected void initData() {

        sp = getSharedPreferences("my_stepcounter_relevant_data", Activity.MODE_PRIVATE);

        startStepService();
    }

    private void startStepService() {
        LoggerUtils.loge(this, "startStepService");
        this.mIsRunning = true;
        startService(new Intent(this, StepServices.class));
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (this.mIsRunning) {
            bindStepService();
        }

    }

    private void bindStepService() {
        LoggerUtils.loge(this, "bindStepService");
        bindService(new Intent(this, StepServices.class), this.mConnection, ConnectionService.BIND_AUTO_CREATE);
    }

    @Override
    protected void onPause() {
        unbindStepService();
        super.onPause();
    }

    private void unbindStepService() {
        LoggerUtils.loge(this, "unbindStepService");
        unbindService(this.mConnection);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected PermissionListener getPermissonCallBack() {
        return null;
    }

    @Override
    protected boolean hasCheckPermission() {
        return false;
    }

    @OnClick(R.id.btn_reset)
    public void onViewClick(View view) {
        mService.reset();
    }
}
