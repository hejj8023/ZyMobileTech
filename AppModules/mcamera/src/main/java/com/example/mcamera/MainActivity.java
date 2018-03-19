package com.example.mcamera;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.media.AudioManager;
import android.os.Handler;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.common.corel.BaseActivity;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity implements SurfaceHolder.Callback {
    @BindView(R.id.surfaceview)
    public SurfaceView surfaceView;

    private SurfaceHolder holder;

    private Camera camera;

    private int cameraPosition;

    private PermissionListener mListener = new PermissionListener() {
        @Override
        public void onGrant(int code) {

        }

        @Override
        public void onDeny(int code) {

        }
    };

    //创建jpeg图片回调数据对象
    Camera.PictureCallback jpeg = new Camera.PictureCallback() {
        @Override
        public void onPictureTaken(byte[] data, Camera camera) {
            // TODO Auto-generated method stub
            try {
                Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
                //自定义文件保存路径  以拍摄时间区分命名
                String filepath = "/sdcard/Messages/MyPictures/" +
                        new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + ".jpg";
                File file = new File(filepath);
                BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);//将图片压缩的流里面
                bos.flush();// 刷新此缓冲区的输出流
                bos.close();// 关闭此输出流并释放与此流有关的所有系统资源
                camera.stopPreview();//关闭预览 处理数据
                camera.startPreview();//数据处理完后继续开始预览
                bitmap.recycle();//回收bitmap空间
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    };

    @Override
    protected void preprocess() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);//没有标题
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//设置全屏
        this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);//拍照过程屏幕一直处于高亮
        //设置手机屏幕朝向，一共有7种
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        holder = surfaceView.getHolder();//获得句柄
        holder.addCallback(this);//添加回调
        holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);//surfaceview不维护自己的缓冲区，等待屏幕渲染引擎将内容推送到用户面前

    }

    @Override
    protected void addListener() {

    }

    @Override
    protected void initData() {
    }

    @Override
    protected PermissionListener getPermissonCallBack() {
        return mListener;
    }

    @OnClick({R.id.btn_cancel, R.id.btn_comfirm, R.id.btn_take_photo, R.id.btn_switch_camera})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.btn_cancel:
                break;
            case R.id.btn_comfirm:
                break;
            case R.id.btn_take_photo:
                camera.autoFocus(new Camera.AutoFocusCallback() {//自动对焦
                    @Override
                    public void onAutoFocus(boolean success, Camera camera) {
                        // TODO Auto-generated method stub
                        if (success) {
                            //设置参数，并拍照
                            Camera.Parameters params = camera.getParameters();
                            params.setPictureFormat(PixelFormat.JPEG);//图片格式
                            params.setPreviewSize(800, 480);//图片大小
                            camera.setParameters(params);//将参数设置到我的camera

                            // 关闭拍照声音
                            AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
                            // 下面这句代码可以根据系统音量的状态来开关拍照声音
                            int currentVolume = audioManager.getStreamVolume(AudioManager.STREAM_NOTIFICATION);
                            if (currentVolume == 0) {
                                audioManager.setStreamMute(AudioManager.STREAM_SYSTEM, true);
                            }

                            camera.takePicture(null, null, jpeg);//将拍摄到的照片给自定义的对象

                            if (currentVolume == 0) { //0代表静音或者震动
                                final Handler soundHandler = new Handler();
                                Timer t = new Timer();
                                t.schedule(new TimerTask() {
                                    @Override
                                    public void run() {
                                        soundHandler.post(new Runnable() {
                                            @Override
                                            public void run() {
                                                AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
                                                audioManager.setStreamMute(AudioManager.STREAM_SYSTEM, false);
                                            }
                                        });

                                    }
                                }, 1000);
                            }
                        }
                    }
                });

                break;
            case R.id.btn_switch_camera:
                //切换前后摄像头
                int cameraCount = 0;
                Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
                cameraCount = Camera.getNumberOfCameras();//得到摄像头的个数

                for (int i = 0; i < cameraCount; i++) {
                    Camera.getCameraInfo(i, cameraInfo);//得到每一个摄像头的信息
                    if (cameraPosition == 1) {
                        //现在是后置，变更为前置
                        if (cameraInfo.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {//代表摄像头的方位，CAMERA_FACING_FRONT前置      CAMERA_FACING_BACK后置
                            camera.stopPreview();//停掉原来摄像头的预览
                            camera.release();//释放资源
                            camera = null;//取消原来摄像头
                            camera = Camera.open(i);//打开当前选中的摄像头
                            try {
                                camera.setPreviewDisplay(holder);//通过surfaceview显示取景画面
                            } catch (IOException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            camera.startPreview();//开始预览
                            cameraPosition = 0;
                            break;
                        }
                    } else {
                        //现在是前置， 变更为后置
                        if (cameraInfo.facing == Camera.CameraInfo.CAMERA_FACING_BACK) {//代表摄像头的方位，CAMERA_FACING_FRONT前置      CAMERA_FACING_BACK后置
                            camera.stopPreview();//停掉原来摄像头的预览
                            camera.release();//释放资源
                            camera = null;//取消原来摄像头
                            camera = Camera.open(i);//打开当前选中的摄像头
                            try {
                                camera.setPreviewDisplay(holder);//通过surfaceview显示取景画面
                            } catch (IOException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            camera.startPreview();//开始预览
                            cameraPosition = 1;
                            break;
                        }
                    }
                }
                break;
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        //当surfaceview创建时开启相机
        if (camera == null) {
            camera = Camera.open();
            try {
                camera.setPreviewDisplay(holder);//通过surfaceview显示取景画面
                camera.startPreview();//开始预览
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        //当surfaceview关闭时，关闭预览并释放资源
        camera.stopPreview();
        camera.release();
        camera = null;
        holder = null;
        surfaceView = null;
    }
}
