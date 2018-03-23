package com.example.gcamera.camera;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.hardware.Camera;
import android.media.AudioManager;
import android.media.SoundPool;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.SeekBar;
import android.widget.Toast;

import com.example.common.corel.BaseApp;
import com.example.gcamera.R;
import com.example.gcamera.camera.utils.BitmapUtils;
import com.example.gcamera.camera.utils.FileUtil;
import com.example.gcamera.camera.utils.SPConfigUtil;
import com.example.gcamera.camera.utils.StringUtils;
import com.example.utils.LogListener;
import com.example.utils.LoggerUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;


/**
 * Created by zzg on 2018/3/23.
 * 正方形的CamerContainer
 */

public class ZyCameraContainer extends FrameLayout implements IActivityLifiCycle, LogListener {
    private final Context mContext;
    private ZYCameraView mCameraView;
    private FocusImageView mFocusImageView;
    private SeekBar mZoomSeekBar;
    private Activity mActivity;
    private SensorControler mSensorControler;
    private BaseApp appInstance;
    private int mScreenWidth;
    private int mScreenHeight;

    private SoundPool mSoundPool;

    public static final int RESETMASK_DELY = 1000; //一段时间后遮罩层一定要隐藏

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

        }
    };
    private int mFocusSoundId;
    private boolean mFocusSoundPrepared;

    public ZyCameraContainer(@NonNull Context context) {
        this(context, null);
    }

    public ZyCameraContainer(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ZyCameraContainer(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        appInstance = BaseApp.getAppInstance();
        mScreenWidth = appInstance.getScreenWidth();
        mScreenHeight = appInstance.getScreenHeight();
        initView();
    }

    private void initView() {
        inflate(mContext, R.layout.custom_camera_container, this);

        mCameraView = (ZYCameraView) findViewById(R.id.cameraView);
        mFocusImageView = (FocusImageView) findViewById(R.id.focusImageView);
        mZoomSeekBar = (SeekBar) findViewById(R.id.zoomSeekBar);

        mSensorControler = SensorControler.getInstance();


        mSensorControler.setCameraFocusListener(new SensorControler.CameraFocusListener() {
            @Override
            public void onFocus() {
                int screenWidth = mScreenWidth;
                Point point = new Point(screenWidth / 2, screenWidth / 2);

                onCameraFocus(point);
            }
        });

        mCameraView.setOnCameraPrepareListener(new ZYCameraView.OnCameraPrepareListener() {
            @Override
            public void onPrepare(CameraManager.CameraDirection cameraDirection) {
                postDelayed(new Runnable() {
                    @Override
                    public void run() {

                    }
                }, RESETMASK_DELY);
                //在这里相机已经准备好 可以获取maxZoom
                mZoomSeekBar.setMax(mCameraView.getMaxZoom());

                if (cameraDirection == CameraManager.CameraDirection.CAMERA_BACK) {
                    mHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            int screenWidth = mScreenWidth;
                            Point point = new Point(screenWidth / 2, screenWidth / 2);
                            onCameraFocus(point);
                        }
                    }, 800);
                }
            }
        });
        mCameraView.setSwitchCameraCallBack(new ZYCameraView.SwitchCameraCallBack() {
            @Override
            public void switchCamera(boolean isSwitchFromFront) {
                if (isSwitchFromFront) {
                    mHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            int screenWidth = mScreenWidth;
                            Point point = new Point(screenWidth / 2, screenWidth / 2);
                            onCameraFocus(point);
                        }
                    }, 300);
                }
            }
        });
        mCameraView.setPictureCallback(pictureCallback);
        mZoomSeekBar.setOnSeekBarChangeListener(onSeekBarChangeListener);
    }

    private final SeekBar.OnSeekBarChangeListener onSeekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress,
                                      boolean fromUser) {
            // TODO Auto-generated method stub
            mCameraView.setZoom(progress);
            mHandler.removeCallbacksAndMessages(mZoomSeekBar);
            //ZOOM模式下 在结束两秒后隐藏seekbar 设置token为mZoomSeekBar用以在连续点击时移除前一个定时任务
            mHandler.postAtTime(new Runnable() {

                @Override
                public void run() {
                    // TODO Auto-generated method stub
                    mZoomSeekBar.setVisibility(View.GONE);
                }
            }, mZoomSeekBar, SystemClock.uptimeMillis() + 2000);
        }


        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
            // TODO Auto-generated method stub

        }


        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            // TODO Auto-generated method stub

        }
    };

    private final Camera.PictureCallback pictureCallback = new Camera.PictureCallback() {
        @Override
        public void onPictureTaken(final byte[] data, Camera camera) {
            // mActivity.rest();

            LoggerUtils.loge(ZyCameraContainer.this, "pictureCallback");

            new SavePicTask(data, mCameraView.isBackCamera()).start();
        }
    };

    private final Camera.AutoFocusCallback autoFocusCallback = new Camera.AutoFocusCallback() {

        @Override
        public void onAutoFocus(boolean success, Camera camera) {
            //聚焦之后根据结果修改图片
            if (success) {
                mFocusImageView.onFocusSuccess();
            } else {
                //聚焦失败显示的图片，由于未找到合适的资源，这里仍显示同一张图片
                mFocusImageView.onFocusFailed();
            }
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    //一秒之后才能再次对焦
                    mSensorControler.unlockFocus();
                }
            }, 1000);
        }
    };

    /**
     * 相机对焦  默认不需要延时
     */
    private void onCameraFocus(final Point point) {
        onCameraFocus(point, false);
    }

    /**
     * 相机对焦
     *
     * @param needDelay 是否需要延时
     */
    public void onCameraFocus(final Point point, boolean needDelay) {
        long delayDuration = needDelay ? 300 : 0;

        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!mSensorControler.isFocusLocked()) {
                    if (mCameraView.onFocus(point, autoFocusCallback)) {
                        mSensorControler.lockFocus();
                        mFocusImageView.startFocus(point);

                        //播放对焦音效
                        if (mFocusSoundPrepared) {
                            mSoundPool.play(mFocusSoundId, 1.0f, 0.5f, 1, 0, 1.0f);
                        }
                    }
                }
            }
        }, delayDuration);
    }

    public void bindActivity(Activity activity) {
        this.mActivity = activity;
        if (mCameraView != null) {
            mCameraView.bindActivity(activity);
        }
    }

    public void switchFlashMode() {
        mCameraView.switchFlashMode();
    }

    @Override
    public void onStart() {
        mSensorControler.onStart();

        if (mCameraView != null) {
            mCameraView.onStart();
        }

        mSoundPool = getSoundPool();
    }

    private SoundPool getSoundPool() {
        if (mSoundPool == null) {
            mSoundPool = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
            mFocusSoundId = mSoundPool.load(mContext, R.raw.camera_focus, 1);
            mFocusSoundPrepared = false;
            mSoundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
                @Override
                public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                    mFocusSoundPrepared = true;
                }
            });
        }
        return mSoundPool;
    }

    @Override
    public void onStop() {
        mSensorControler.onStop();

        if (mCameraView != null) {
            mCameraView.onStop();
        }

        mSoundPool.release();
        mSoundPool = null;
    }

    /**
     * 旋转bitmap
     * 对于前置摄像头和后置摄像头采用不同的旋转角度  前置摄像头还需要做镜像水平翻转
     */
    public Bitmap rotateBitmap(Bitmap bitmap, boolean isBackCamera) {
        System.gc();
        int degrees = isBackCamera ? 0 : 0;
        degrees = mCameraView.getPicRotation();
        if (null == bitmap) {
            return bitmap;
        }
        Matrix matrix = new Matrix();
        matrix.setRotate(degrees, bitmap.getWidth() / 2, bitmap.getHeight() / 2);
        if (!isBackCamera) {
            matrix.postScale(-1, 1, bitmap.getWidth() / 2, bitmap.getHeight() / 2);   //镜像水平翻转
        }
//            Bitmap bmp = Bitmap.createBitmap(bitmap,0,0,bitmap.getWidth(),bitmap.getHeight(),matrix,!isBackCamera);
        //不需要透明度 使用RGB_565
        Bitmap bmp = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.RGB_565);
        Paint paint = new Paint();
        Canvas canvas = new Canvas(bmp);
        canvas.drawBitmap(bitmap, matrix, paint);


        if (null != bitmap) {
            bitmap.recycle();
        }

        return bmp;
    }


    /**
     * 获取以中心点为中心的正方形区域
     */
    private Rect getCropRect(byte[] data) {
        //获得图片大小
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeByteArray(data, 0, data.length, options);

        int width = options.outWidth;
        int height = options.outHeight;
        int centerX = width / 2;
        int centerY = height / 2;

        int PHOTO_LEN = Math.min(width, height);
        return new Rect(centerX - PHOTO_LEN / 2, centerY - PHOTO_LEN / 2, centerX + PHOTO_LEN / 2, centerY + PHOTO_LEN / 2);
    }

    /**
     * 给出合适的sampleSize的建议
     */
    private int suggestSampleSize(byte[] data, int target) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        options.inPurgeable = true;
        BitmapFactory.decodeByteArray(data, 0, data.length, options);

        int w = options.outWidth;
        int h = options.outHeight;
        int candidateW = w / target;
        int candidateH = h / target;
        int candidate = Math.max(candidateW, candidateH);
        if (candidate == 0)
            return 1;
        if (candidate > 1) {
            if ((w > target) && (w / candidate) < target)
                candidate -= 1;
        }
        if (candidate > 1) {
            if ((h > target) && (h / candidate) < target)
                candidate -= 1;
        }
        //if (VERBOSE)
        LoggerUtils.loge(this, "for w/h " + w + "/" + h + " returning " + candidate + "(" + (w / candidate) + " / " + (h / candidate));
        return candidate;
    }

    private String mImagePath;

    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            boolean result = (Boolean) msg.obj;

            LoggerUtils.loge(ZyCameraContainer.this, "TASK onPostExecute:" + (System.currentTimeMillis() - lastTime));

            if (result) {
                fileScan(mImagePath);
//                releaseCamera();    //不要在这个地方释放相机资源   这里是浪费时间的最大元凶  约1500ms左右
//                mActivity.postTakePhoto();
                LoggerUtils.loge(ZyCameraContainer.this, "TASK:" + (System.currentTimeMillis() - lastTime));
            } else {
                LoggerUtils.loge(ZyCameraContainer.this, "photo save failed!");
                Toast.makeText(mContext, R.string.topic_camera_takephoto_failure, Toast.LENGTH_SHORT).show();

//                mActivity.rest();

                mCameraView.startPreview();
            }
        }
    };

    public void fileScan(String filePath) {
        Uri data = Uri.parse("file://" + filePath);
        mActivity.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, data));
    }

    long lastTime;

    private class SavePicTask extends Thread {
        private byte[] data;
        private boolean isBackCamera;
        private boolean sampleSizeSuggested;
        private boolean ioExceptionRetried;     //寻找合适的bitmap发生io异常  允许一次重试


        SavePicTask(byte[] data, boolean isBackCamera) {
            sampleSizeSuggested = false;
            ioExceptionRetried = false;
            this.data = data;
            this.isBackCamera = isBackCamera;
        }

        @Override
        public void run() {
            super.run();

            long current = System.currentTimeMillis();

            Message msg = handler.obtainMessage();
            msg.obj = saveToSDCard(data);
            handler.sendMessage(msg);

            LoggerUtils.loge(ZyCameraContainer.this, "save photo:" + (System.currentTimeMillis() - current) + "ms");
        }

        /**
         * 将拍下来的照片存放在SD卡中
         *
         * @return imagePath 图片路径
         */
        public boolean saveToSDCard(byte[] data) {
            lastTime = System.currentTimeMillis();

            //ADD 生成保存图片的路径
            mImagePath = FileUtil.getCameraImgPath();
            LoggerUtils.loge(ZyCameraContainer.this, "ImagePath:" + mImagePath);

            //保存到SD卡
            if (StringUtils.isEmpty(mImagePath)) {
                LoggerUtils.loge(ZyCameraContainer.this, "要保存的图片路径为空");
                return false;
            }

            if (!FileUtil.checkSDcard()) {
                Toast.makeText(mContext, R.string.tips_sdcard_notexist, Toast.LENGTH_LONG).show();

                return false;
            }

            LoggerUtils.loge(ZyCameraContainer.this, "saveToSDCard beforefindFitBitmap time:" + (System.currentTimeMillis() - lastTime));
            //从本地读取合适的sampleSize,默认为1
            int sampleSize = SPConfigUtil.loadInt("sampleSize", 1);
            Bitmap bitmap = findFitBitmap(data, getCropRect(data), sampleSize);

            if (bitmap == null) {
                return false;
            }

            LoggerUtils.loge(ZyCameraContainer.this, "saveToSDCard beforeSave time:" + (System.currentTimeMillis() - lastTime));
            BitmapUtils.saveBitmap(bitmap, mImagePath);
            BaseApp.getAppInstance().setCameraBitmap(bitmap);
//            bitmap.recycle();


            LoggerUtils.loge(ZyCameraContainer.this, "saveToSDCard afterSave time:" + (System.currentTimeMillis() - lastTime));
            return true;
        }

        /**
         * 寻找合适的bitmap  剪切rect  并且做旋转  镜像处理
         */
        private Bitmap findFitBitmap(byte[] data, Rect rect, int sampleSize) {
            InputStream is = null;
            System.gc();
            try {
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inJustDecodeBounds = false;
                options.inSampleSize = sampleSize;
                options.inPreferredConfig = Bitmap.Config.RGB_565;
                options.inPurgeable = true;
                options.inInputShareable = true;

                is = new ByteArrayInputStream(data);

                Bitmap bitmap = BitmapUtils.decode(is, rect, options);

//                BitmapRegionDecoder decoder = BitmapRegionDecoder.newInstance(is, false);
//                bitmap = decoder.decodeRegion(rect, options);


                //未抛出异常，保存合适的sampleSize
                SPConfigUtil.save("sampleSize", sampleSize + "");

                LoggerUtils.loge(ZyCameraContainer.this, "sampleSize:" + sampleSize);
                LoggerUtils.loge(ZyCameraContainer.this, "saveToSDCard afterLoad Bitmap time:" + (System.currentTimeMillis() - lastTime));

//                if(mCameraView.needRotateBitmap()) {
                bitmap = rotateBitmap(bitmap, isBackCamera);
                LoggerUtils.loge(ZyCameraContainer.this, "saveToSDCard afterRotate Bitmap time:" + (System.currentTimeMillis() - lastTime));
//                }
                return bitmap;
            } catch (OutOfMemoryError e) {
                e.printStackTrace();
                System.gc();

                /* 是否对sampleSize做出过建议，没有就做一次建议，按照建议的尺寸做出缩放，做过就直接缩小图片**/
                if (sampleSizeSuggested) {
                    return findFitBitmap(data, rect, sampleSize * 2);
                } else {
                    return findFitBitmap(data, rect, suggestSampleSize(data, mScreenWidth));
                }
            } catch (Exception e) {
                e.printStackTrace();
                //try again
                if (!ioExceptionRetried) {
                    ioExceptionRetried = true;
                    return findFitBitmap(data, rect, sampleSize);
                }
                return null;
            } finally {
                if (is != null) {
                    try {
                        is.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
