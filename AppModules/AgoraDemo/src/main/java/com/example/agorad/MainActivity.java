package com.example.agorad;

import android.Manifest;
import android.view.View;
import android.widget.FrameLayout;

import com.zysdk.vulture.clib.corel.BaseToolbarSupportActivity;
import com.zysdk.vulture.clib.utils.LoggerUtils;
import com.zysdk.vulture.clib.utils.UiUtils;

import butterknife.BindView;
import io.agora.rtc.IRtcEngineEventHandler;
import io.agora.rtc.RtcEngine;

public class MainActivity extends BaseToolbarSupportActivity {

    private static final int PERMISSION_REQ_ID_RECORD_AUDIO = 22;
    private static final int PERMISSION_REQ_ID_CAMERA = PERMISSION_REQ_ID_RECORD_AUDIO + 1;


    @BindView(R.id.remote_video_view_container)
    FrameLayout mContainer;
    @BindView(R.id.quick_tips_when_use_agora_sdk)
    View tipMsg;

    private IRtcEngineEventHandler mRtcEventHandler = new IRtcEngineEventHandler() {
        @Override
        public void onVideoStopped() {
            super.onVideoStopped();
            LoggerUtils.loge("IRtcEngineEventHandler onVideoStopped");
        }

        @Override
        public void onUserOffline(int uid, int reason) {
            super.onUserOffline(uid, reason);
            LoggerUtils.loge("IRtcEngineEventHandler onUserOffline");
            UiUtils.post(new Runnable() {
                @Override
                public void run() {
                    onRemoteUserLeft();
                }
            });
        }

        @Override
        public void onUserMuteVideo(int uid, boolean muted) {
            super.onUserMuteVideo(uid, muted);
            LoggerUtils.loge("IRtcEngineEventHandler onUserMuteVideo");
            UiUtils.post(new Runnable() {
                @Override
                public void run() {
                    onRemoteUserVideoMuted(uid, muted);
                }
            });
        }

        @Override
        public void onRemoteVideoStats(RemoteVideoStats stats) {
            super.onRemoteVideoStats(stats);
            LoggerUtils.loge("IRtcEngineEventHandler onRemoteVideoStats");
        }

        @Override
        public void onLocalVideoStats(LocalVideoStats stats) {
            super.onLocalVideoStats(stats);
            LoggerUtils.loge("IRtcEngineEventHandler onLocalVideoStats");
        }

        @Override
        public void onFirstRemoteVideoFrame(int uid, int width, int height, int elapsed) {
            super.onFirstRemoteVideoFrame(uid, width, height, elapsed);
            LoggerUtils.loge("IRtcEngineEventHandler onFirstRemoteVideoFrame");
        }

        @Override
        public void onFirstLocalVideoFrame(int width, int height, int elapsed) {
            super.onFirstLocalVideoFrame(width, height, elapsed);
            LoggerUtils.loge("IRtcEngineEventHandler onFirstLocalVideoFrame");
        }

        @Override
        public void onFirstRemoteVideoDecoded(int uid, int width, int height, int elapsed) {
            super.onFirstRemoteVideoDecoded(uid, width, height, elapsed);
            LoggerUtils.loge("IRtcEngineEventHandler onFirstRemoteVideoDecoded");

            UiUtils.post(new Runnable() {
                @Override
                public void run() {
                    setupRemoteVideo(uid);
                }
            });
        }
    };

    private void setupRemoteVideo(int uid) {

    }

    private void onRemoteUserVideoMuted(int uid, boolean muted) {

    }

    private void onRemoteUserLeft() {
        mContainer.removeAllViews();
        tipMsg.setVisibility(View.VISIBLE);
    }

    @Override
    public void beforeSubContentInit() {
        if (checkSelfPermission(Manifest.permission.RECORD_AUDIO, PERMISSION_REQ_ID_RECORD_AUDIO) &&
                checkSelfPermission(Manifest.permission.CAMERA, PERMISSION_REQ_ID_CAMERA)) {
            initAgoraEngineAndJoinChannel();
        }
        super.beforeSubContentInit();
    }

    private void initAgoraEngineAndJoinChannel() {
        initializeAgoraEngine();     // Tutorial Step 1
        setupVideoProfile();         // Tutorial Step 2
        setupLocalVideo();           // Tutorial Step 3
        joinChannel();               // Tutorial Step 4
    }

    private void initializeAgoraEngine() {
        try {
            RtcEngine.create(mContext.getApplicationContext(), UiUtils.getStr(R.string.agora_app_id), mRtcEventHandler);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setupVideoProfile() {

    }

    private void setupLocalVideo() {

    }

    private void joinChannel() {

    }

    @Override
    protected boolean initToolBar() {
        return false;
    }

    @Override
    protected int getToolbarBgColor() {
        return R.color.sr_color_primary;
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_main;
    }
}
