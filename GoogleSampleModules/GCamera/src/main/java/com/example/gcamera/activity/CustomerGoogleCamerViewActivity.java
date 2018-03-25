package com.example.gcamera.activity;

import com.google.android.cameraview.CameraView;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Environment;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.gcamera.R;
import com.example.utils.LoggerUtils;

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

    @BindView(R.id.imageview)
    ImageView imageView;

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

                    // 将图片以surface的大小进行显示
                    Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
                    LoggerUtils.loge(CustomerGoogleCamerViewActivity.this, "bitmap w = "
                            + bitmap.getWidth() + " , bitmap h = " + bitmap.getHeight());
                    // 将图片缩放至surfaceview可见区域大小
                    Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap, cameraView.getWidth(), cameraView.getHeight(), true);
                    LoggerUtils.loge(CustomerGoogleCamerViewActivity.this, "scaledBitmap w = "
                            + scaledBitmap.getWidth() + " , scaledBitmap h = " + scaledBitmap.getHeight());

                    Bitmap cScaledBitmap = scaledBitmap;
                    fileName = dateFormat.format(new Date(System.currentTimeMillis()));
                    file = new File(dir, "scale缩放图" + fileName + ".jpg");
                    cmsBitmap(file, scaledBitmap);

                    scaledBitmap = Bitmap.createScaledBitmap(bitmap, 150, 150, true);
                    LoggerUtils.loge(CustomerGoogleCamerViewActivity.this, "scaledBitmap w = "
                            + scaledBitmap.getWidth() + " , scaledBitmap h = " + scaledBitmap.getHeight());

                    fileName = dateFormat.format(new Date(System.currentTimeMillis()));
                    file = new File(dir, "p-scale150缩放图" + fileName + ".jpg");
                    cmsBitmap(file, scaledBitmap);


                    // TODO: 2018/3/25 将图片4:3进行截取，surfaceview宽度的0.75
                    int tH = (int) (cameraView.getWidth() * 0.75f);
                    // 区域裁剪
                    Bitmap bitmap1 = Bitmap.createBitmap(cScaledBitmap, 0, (cScaledBitmap.getHeight() - tH) / 2, cameraView.getWidth(), tH);
                    Canvas canvas = new Canvas(bitmap1);
                    canvas.drawBitmap(bitmap1, 0, 0, null);
                    if (bitmap1 != null) {
                        LoggerUtils.loge(CustomerGoogleCamerViewActivity.this, "bitmap1 w = "
                                + bitmap1.getWidth() + " , bitmap1 h = " + bitmap1.getHeight());
                    }
                    fileName = dateFormat.format(new Date(System.currentTimeMillis()));
                    file = new File(dir, "最终裁剪图" + fileName + ".jpg");
                    cmsBitmap(file, bitmap1);

                    // 缩放150-150(图片会拉伸)
                    scaledBitmap = Bitmap.createScaledBitmap(bitmap1, 150, 150, true);
                    LoggerUtils.loge(CustomerGoogleCamerViewActivity.this, "150px scaledBitmap w = "
                            + scaledBitmap.getWidth() + " , 150px scaledBitmap h = " + scaledBitmap.getHeight());

                    fileName = dateFormat.format(new Date(System.currentTimeMillis()));
                    file = new File(dir, "s150px缩放图" + fileName + ".jpg");
                    cmsBitmap(file, scaledBitmap);

                    Bitmap finalScaledBitmap = scaledBitmap;
                    mH.post(new Runnable() {
                        @Override
                        public void run() {
                            if (imageView == null)
                                return;
                            imageView.setVisibility(View.VISIBLE);
                            imageView.setImageBitmap(finalScaledBitmap);
                        }
                    });

                    // TODO: 2018/3/25 将比例缩放的图，缩放至4:3 
                }
            });
        }
    };

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
