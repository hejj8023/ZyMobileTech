package com.example.gcamera.activity;

import com.google.android.cameraview.CameraView;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Environment;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.gcamera.R;
import com.example.utils.LoggerUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;

/**
 * Created by zzg on 2018/3/25.
 */

public class CustomerGoogleCamerViewActivity extends GoogleCameraViewActivity {

    private Handler mH = new Handler();

    @Override
    protected int getContentViewId() {
        return R.layout.layout_google_camera_view2;
    }

    @Override
    protected void initView() {
        super.initView();
        if (cameraView != null) {
            cameraView.setFlash(CameraView.FLASH_OFF);
            cameraView.addCallback(myCallback);
        }
    }

    public CameraView.Callback myCallback
            = new CameraView.Callback() {

        @Override
        public void onCameraOpened(CameraView cameraView) {
            LoggerUtils.loge(CustomerGoogleCamerViewActivity.this, "onCameraOpened");
        }

        @Override
        public void onCameraClosed(CameraView cameraView) {
            LoggerUtils.loge(CustomerGoogleCamerViewActivity.this, "onCameraClosed");
        }

        @Override
        public void onPictureTaken(CameraView cameraView, final byte[] data) {
            LoggerUtils.loge(CustomerGoogleCamerViewActivity.this, "onPictureTaken " + data.length);
            Toast.makeText(cameraView.getContext(), R.string.picture_taken, Toast.LENGTH_SHORT)
                    .show();

            LoggerUtils.loge(CustomerGoogleCamerViewActivity.this, "cameraView w = "
                    + cameraView.getWidth() + " , cameraView h = " + cameraView.getHeight());

            getBackgroundHandler().post(new Runnable() {
                @Override
                public void run() {
                    File dir = new File(Environment.getExternalStorageDirectory(), "JCameraView");
                    if (!dir.exists()) {
                        dir.mkdirs();
                    }

                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-ddHHmmss");
                    String fileName = dateFormat.format(new Date(System.currentTimeMillis()));
                    File file = new File(dir, "原始图" + fileName + ".jpg");
                    savePic(dir, data, file);
                    try {
                        Thread.sleep(800);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    // 将图片以surface的大小进行显示
                    Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
                    LoggerUtils.loge(CustomerGoogleCamerViewActivity.this, "bitmap w = "
                            + bitmap.getWidth() + " , bitmap h = " + bitmap.getHeight());
                    // 将图片缩放至surfaceview可见区域大小
                    Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap, cameraView.getWidth(), cameraView.getHeight(), true);
                    LoggerUtils.loge(CustomerGoogleCamerViewActivity.this, "scaledBitmap w = "
                            + scaledBitmap.getWidth() + " , scaledBitmap h = " + scaledBitmap.getHeight());

                    // TODO: 2018/3/26 中心区域截取200*200
                    Bitmap tBmp1 = Bitmap.createBitmap(scaledBitmap, scaledBitmap.getWidth() / 2, scaledBitmap.getHeight() / 2, 200, 200);
                    LoggerUtils.loge(CustomerGoogleCamerViewActivity.this, "tBmp1 w = "
                            + tBmp1.getWidth() + " , tBmp1 h = " + tBmp1.getHeight());
                    fileName = dateFormat.format(new Date(System.currentTimeMillis()));
                    file = new File(dir, "crop-1-200x200缩放图" + fileName + ".jpg");
                    cmsBitmap(file, scaledBitmap);

                    // TODO: 2018/3/26 BitmapRegionDecoder 200*200
                    cropByRDecoder(dir, dateFormat, scaledBitmap, "cropDecoder-1-200x200缩放图");

                    Bitmap cScaledBitmap = scaledBitmap;
                    fileName = dateFormat.format(new Date(System.currentTimeMillis()));
                    file = new File(dir, "scale缩放图" + fileName + ".jpg");
                    cmsBitmap(file, scaledBitmap);

                    try {
                        Thread.sleep(800);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    // 缩放至150->4:3
                    scaledBitmap = Bitmap.createScaledBitmap(bitmap, 150, (int) (150 / 0.75f), true);
                    LoggerUtils.loge(CustomerGoogleCamerViewActivity.this, "scaledBitmap w = "
                            + scaledBitmap.getWidth() + " , scaledBitmap h = " + scaledBitmap.getHeight());

                    fileName = dateFormat.format(new Date(System.currentTimeMillis()));
                    file = new File(dir, "p-scale150缩放图" + fileName + ".jpg");
                    cmsBitmap(file, scaledBitmap);
                    if (bitmap != null && !bitmap.isRecycled()) {
                        bitmap.recycle();
                    }

                    // TODO: 2018/3/25 将图片4:3进行截取，surfaceview宽度的0.75
                    int tH = (int) (cameraView.getWidth() * 0.75f);
                    // 区域裁剪
                    Bitmap bitmap1 = Bitmap.createBitmap(cScaledBitmap, 0, (cScaledBitmap.getHeight() - tH) / 2, cameraView.getWidth(), tH);
                    if (bitmap1 != null) {
                        LoggerUtils.loge(CustomerGoogleCamerViewActivity.this, "bitmap1 w = "
                                + bitmap1.getWidth() + " , bitmap1 h = " + bitmap1.getHeight());
                    }
                    fileName = dateFormat.format(new Date(System.currentTimeMillis()));
                    file = new File(dir, "最终裁剪图" + fileName + ".jpg");
                    cmsBitmap(file, bitmap1);

                    if (cScaledBitmap != null && !cScaledBitmap.isRecycled()) {
                        cScaledBitmap.recycle();
                    }

                    try {
                        Thread.sleep(800);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    // TODO: 2018/3/26 BitmapRegionDecoder 200*200
                    cropByRDecoder(dir, dateFormat, bitmap1, "cropDecoder-2-200x200缩放图");

                    try {
                        Thread.sleep(800);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    // TODO: 2018/3/26 中心区域截取200*200
                    tBmp1 = Bitmap.createBitmap(bitmap1, bitmap1.getWidth() / 2, bitmap1.getHeight() / 2, 200, 200);
                    LoggerUtils.loge(CustomerGoogleCamerViewActivity.this, "tBmp1 w = "
                            + tBmp1.getWidth() + " , tBmp1 h = " + tBmp1.getHeight());
                    fileName = dateFormat.format(new Date(System.currentTimeMillis()));
                    file = new File(dir, "crop-2-200x200缩放图" + fileName + ".jpg");
                    cmsBitmap(file, scaledBitmap);

                    if (tBmp1 != null && !tBmp1.isRecycled()) {
                        tBmp1.recycle();
                    }

                    // 缩放至150->4:3
                    // scaledBitmap = Bitmap.createScaledBitmap(bitmap1, (int) (150 / 0.75f), 150, true);
                    scaledBitmap = Bitmap.createScaledBitmap(bitmap1, 150, (int) (150 * 0.75f), true);
                    LoggerUtils.loge(CustomerGoogleCamerViewActivity.this, "150px scaledBitmap w = "
                            + scaledBitmap.getWidth() + " , 150px scaledBitmap h = " + scaledBitmap.getHeight());

                    fileName = dateFormat.format(new Date(System.currentTimeMillis()));
                    file = new File(dir, "s150px缩放图" + fileName + ".jpg");
                    cmsBitmap(file, scaledBitmap);

                    if (scaledBitmap != null && !scaledBitmap.isRecycled()) {
                        scaledBitmap.recycle();
                    }

                    if (bitmap1 != null && !bitmap1.isRecycled()) {
                        bitmap1.recycle();
                    }
                    LoggerUtils.loge(CustomerGoogleCamerViewActivity.this, "finished");
                }
            });
        }
    };

    private void cropByRDecoder(File dir, SimpleDateFormat dateFormat, Bitmap bitmap, String filePrefix) {
        String fileName;
        File file;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        boolean compress = bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        if (!compress) {
            bitmap.compress(Bitmap.CompressFormat.PNG, 80, baos);
        }

        byte[] imgData = baos.toByteArray();
        Bitmap tBmp = null;
        try {
            BitmapRegionDecoder regionDecoder = BitmapRegionDecoder.newInstance(imgData, 0, imgData.length, true);
            int l = bitmap.getWidth() / 2;
            int t = bitmap.getHeight() / 2;
            Rect rect = new Rect(l - 200, l + 200, t - 200, t + 200);
            tBmp = regionDecoder.decodeRegion(rect, new BitmapFactory.Options());
            if (tBmp != null) {
                LoggerUtils.loge(CustomerGoogleCamerViewActivity.this, "tBmp w = "
                        + tBmp.getWidth() + " , tBmp h = " + tBmp.getHeight());
                fileName = dateFormat.format(new Date(System.currentTimeMillis()));
                file = new File(dir, filePrefix + fileName + ".jpg");
                cmsBitmap(file, tBmp);
            } else {
                LoggerUtils.loge(CustomerGoogleCamerViewActivity.this, "bitmap生成失败");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (tBmp != null && !tBmp.isRecycled()) {
                tBmp.recycle();
            }

            try {
                baos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void cmsBitmap(File file, Bitmap bitmap) {
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(file);
            boolean compress = bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
            if (compress) {
                LoggerUtils.loge(CustomerGoogleCamerViewActivity.this, "compress sucess");
            } else {
                LoggerUtils.loge(CustomerGoogleCamerViewActivity.this, "compress failure");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void savePic(File dir, byte[] data, File file) {
        OutputStream os = null;

        if (file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            os = new FileOutputStream(file);
            os.write(data);
            os.close();
        } catch (IOException e) {
            LoggerUtils.loge(CustomerGoogleCamerViewActivity.this, "Cannot write to " + file + e);
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    // Ignore
                }
            }
        }
    }
}
