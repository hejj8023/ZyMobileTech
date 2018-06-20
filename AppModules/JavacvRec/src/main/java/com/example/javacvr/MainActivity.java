package com.example.javacvr;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.VideoView;

import java.util.Date;

/**
 * Main Activity that used to show all the actions.
 *
 * @author xiaodong
 */
public class MainActivity extends Activity {

    private static final int REQ_CAMERA = 0x110;

    private MySurfaceView videoSurface = null;
    private RecorderManager recorderManager = null;
    CameraManager cameraManager;
    private ProgressView progressView = null;
    private VideoView videoView = null;
    private boolean isPlaying = false;
    // private TextView sign;
    private Runnable progressRunnable = null;

    private View finishView = null;
    private Button finishButton = null;
    private Button swithButton = null;
    Handler handler = null;

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        videoSurface = (MySurfaceView) findViewById(R.id.cameraView);
        videoView = (VideoView) findViewById(R.id.mediaShow);
        swithButton = (Button) findViewById(R.id.switchCamera);
        // sign = (TextView) findViewById(R.id.press);
        progressView = (ProgressView) findViewById(R.id.progress);

        boolean hasPermiss = checkSelfPermission(Manifest.permission.CAMERA, REQ_CAMERA);
        if (hasPermiss) {
            doIniit();
        }
    }

    private void doIniit() {
        videoSurface.init(this);

        cameraManager = getCameraManager();

        recorderManager = getRecorderManager();
        //
        finishView = findViewById(R.id.finishLayOut);
        finishButton = (Button) findViewById(R.id.finishButton);
        //
        // recorderManager = new RecorderManager(getCameraManager(),
        // videoSurface,
        // this);
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.start();
                mp.setLooping(true);

            }
        });

        videoSurface.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View arg0, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {

                    swithButton.setVisibility(View.INVISIBLE);
                    // sign.setPressed(true);
                    recorderManager.startRecord();

                } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {

                    // sign.setPressed(false);
                    recorderManager.stopRecord();
                }
                return true;
            }
        });
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                int total = ((ViewGroup) progressView.getParent())
                        .getMeasuredWidth();
                if (msg.arg1 < 6000) {
                    // System.out.println("Clickable");
                    // finishButton.setClickable(true);
                    finishView.setVisibility(View.INVISIBLE);
                    // finishButton
                    // .setBackgroundResource(R.drawable.btn_capture_arrow);
                } else {
                    // System.out.println("UnClickable");
                    // finishButton.setClickable(false);
                    finishView.setVisibility(View.VISIBLE);
                    // finishButton
                    // .setBackgroundResource(R.drawable.btn_capture_arrow_pressed);
                }
                double length = msg.arg1 * 1.0 / 6000 * total;
                progressView.setWidth((int) length);
                progressView.invalidate();
                super.handleMessage(msg);
                // //
            }
        };
        //
        progressRunnable = new ProgressRunnable();
        handler.post(progressRunnable);
    }

    // @Override
    // protected void onPause() {
    // // TODO Auto-generated method stub
    // muteAll(false);
    // super.onPause();
    // }
    //
    // @Override
    // protected void onResume() {
    // muteAll(true);
    // super.onResume();
    // }

    protected boolean checkSelfPermission(String permission, int requestCode) {
        Log.e("Test", "checkSelfPermission " + permission + " " + requestCode);
        if (ContextCompat.checkSelfPermission(this, permission) != PackageManager
                .PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{permission},
                    requestCode);
            return false;
        }
        return true;
    }

    public CameraManager getCameraManager() {
        if (cameraManager == null) {
            cameraManager = new CameraManager();
        }
        return cameraManager;
    }

    public RecorderManager getRecorderManager() {
        if (recorderManager == null) {
            recorderManager = new RecorderManager(getCameraManager(),
                    videoSurface, this);
        }
        return recorderManager;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public void onBackPressed(View view) {
        if (!isPlaying) {
            swithButton.setVisibility(View.VISIBLE);
            stopPlay();
            recorderManager.reset();
            videoView.setVisibility(SurfaceView.GONE);
            videoSurface.setVisibility(SurfaceView.VISIBLE);
            isPlaying = false;
            recorderManager.reset();
        }
        // finish();

    }

    public void onFinishPressed(View view) {
        if (!isPlaying) {
            recorderManager.releaseRecord();
            startPlay();
            isPlaying = true;
        } else {
            recorderManager.reset();
            cameraManager.stopPreview();
            videoView.setVisibility(SurfaceView.GONE);
            videoSurface.setVisibility(SurfaceView.VISIBLE);
            isPlaying = false;
        }
    }

    public void onCameraSwitchPressed(View view) {
        if (!isPlaying) {
            // cameraManager.changeCamera(videoSurface.getHolder());
            // recorderManager.reset();
        }
    }

    public void startPlay() {
        // combineFiles();
        recorderManager.reset();
        videoSurface.setVisibility(SurfaceView.INVISIBLE);
        videoView.setVisibility(SurfaceView.VISIBLE);
        videoView.setVideoPath(getFinalVideoFileName());
        videoView.start();
    }

    public String getFinalVideoFileName() {
        return recorderManager.getVideoParentpath() + "/video.mp4";
    }

    private void stopPlay() {
        try {
            videoView.stopPlayback();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        recorderManager.reset();
        cameraManager.closeCamera();
        handler.removeCallbacks(progressRunnable);
    }

    private class ProgressRunnable implements Runnable {

        @Override
        public void run() {
            int time = 0;
            time = recorderManager.checkIfMax(new Date().getTime());
            Message message = new Message();
            message.arg1 = time;
            handler.sendMessage(message);
            // System.out.println(time);
            handler.postDelayed(this, 10);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults != null) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                doIniit();
            }
        }
    }
}
