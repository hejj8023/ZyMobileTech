package com.example.scounter.service;

import android.app.Activity;
import android.app.Notification;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.os.PowerManager;
import android.support.annotation.Nullable;

import com.example.algorithm.utils.LogListener;
import com.example.algorithm.utils.LoggerUtils;
import com.example.scounter.corel.StepCount;
import com.example.scounter.corel.StepDetector;
import com.example.scounter.listeners.StepVluePassListener;
import com.example.scounter.listeners.UpdateUiCallback;

/**
 * Created by example on 2018/3/2.
 * <p>
 * 后台计步的service
 */

public class StepServices extends Service implements LogListener {

    private static final int GRAY_SERVICE_ID = 1001;
    private IBinder mBinder = new StepBinder();
    private PowerManager.WakeLock wakeLock;
    private StepDetector stepDetector;
    private SensorManager sensorManager;
    private Sensor sensor;
    private SharedPreferences.Editor mEdit;
    private UpdateUiCallback mCallback;

    private StepVluePassListener mValuePassListner = new StepVluePassListener() {

        @Override
        public void stepChanged(int count) {
            LoggerUtils.loge(StepServices.this, "stepChanged count = " + count);
            mEdit.putString("steps", count + "");
            mEdit.commit();
            if (mCallback != null) {
                mCallback.updateUi();
            }
        }
    };
    private StepCount stepCount;

    @Override
    public void onCreate() {
        super.onCreate();
        LoggerUtils.loge(this, "onCreate");

        // 获取屏幕锁
        wakeLock = ((PowerManager) getSystemService(Context.POWER_SERVICE)).newWakeLock(1, "StepService");
        wakeLock.acquire();

        stepDetector = new StepDetector();

        // 获取传感器
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        // 使用加速度传感器
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(stepDetector, sensor, SensorManager.SENSOR_DELAY_UI);

        stepCount = new StepCount();
        stepCount.regListener(mValuePassListner);
        stepDetector.regListener(stepCount);

        SharedPreferences sp = getSharedPreferences("my_stepcounter_relevant_data", Activity.MODE_PRIVATE);
        mEdit = sp.edit();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        LoggerUtils.loge(this, "onBind");
        return this.mBinder;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        LoggerUtils.loge(this, "onStartCommand");

        /**
         * 灰色保活，使服务成为无通知栏显示的前台服务
         */
        if (Build.VERSION.SDK_INT < 18) {
            startForeground(0, new Notification());
        } else {
            Intent innerIntent = new Intent(this, GrayInnerService.class);
            startService(innerIntent);
            startForeground(GRAY_SERVICE_ID, new Notification());
        }
        return START_STICKY;
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        LoggerUtils.loge(this, "onStart");
    }

    @Override
    public boolean onUnbind(Intent intent) {
        LoggerUtils.loge(this, "onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        LoggerUtils.loge(this, "onDestroy");
        this.wakeLock.release();
        mEdit.putString("steps", "0");
        mEdit.commit();
        super.onDestroy();
    }

    public void reset() {
        mEdit.putString("steps", "0");
        mEdit.commit();
        stepCount.setSteps(0);
    }

    public class StepBinder extends Binder {
        public StepServices getService() {
            return StepServices.this;
        }
    }

    public void registerCallback(UpdateUiCallback callback) {
        this.mCallback = callback;
    }

    public static class GrayInnerService extends Service implements LogListener {

        @Override
        public int onStartCommand(Intent intent, int flags, int startId) {
            LoggerUtils.loge(this, "onStartCommand");
            startForeground(GRAY_SERVICE_ID, new Notification());
            stopForeground(true);
            stopSelf();
            return super.onStartCommand(intent, flags, startId);
        }

        @Nullable
        @Override
        public IBinder onBind(Intent intent) {
            LoggerUtils.loge(this, "onBind");
            return null;
        }
    }
}
