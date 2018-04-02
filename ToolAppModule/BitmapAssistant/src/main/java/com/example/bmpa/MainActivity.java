package com.example.bmpa;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.common.corel.BaseActivity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import butterknife.OnClick;

public class MainActivity extends BaseActivity {

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

    }

    @Override
    protected PermissionListener getPermissonCallBack() {
        return null;
    }

    @OnClick({R.id.btn_create_bmp})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.btn_create_bmp:
                createSourceFile();
                break;
        }
    }

    private void createSourceFile() {
        File firDir = new File(Environment.getExternalStorageDirectory(), "test");
        if (!firDir.exists()) {
            firDir.mkdirs();
        } else {
            // TODO: 2018/4/2 图片存在就直接返回
            return;
        }
        int imgWidth = (int) (1920 * 1.5);
        int imgHeight = (int) (1080 * 1.5);
        Rect rect = new Rect(0, 0, imgWidth, imgHeight);//画一个矩形
        Paint textPaint = new Paint();
        textPaint.setColor(Color.WHITE);
        textPaint.setTextSize(50);
        textPaint.setStyle(Paint.Style.FILL);
        //该方法即为设置基线上那个点究竟是left,center,还是right  这里我设置为center
        textPaint.setTextAlign(Paint.Align.CENTER);

        Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
        float top = fontMetrics.top;//为基线到字体上边框的距离,即上图中的top
        float bottom = fontMetrics.bottom;//为基线到字体下边框的距离,即上图中的bottom

        int baseLineY = (int) (rect.centerY() - top / 2 - bottom / 2);//基线中间点的y轴计算公式

        // 创建空图片
        Bitmap bitmap = Bitmap.createBitmap(imgWidth, imgHeight, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.rgb(255, 0, 0));
        paint.setShadowLayer(1f, 0f, 1f, Color.DKGRAY);
        canvas.drawRect(rect, paint);

        Rect bounds = new Rect();
        String mText = "A";
        textPaint.getTextBounds(mText, 0, mText.length(), bounds);
        canvas.drawText(mText, rect.width() / 2, rect.height() / 2, textPaint);
        saveFile(bitmap, firDir.getAbsolutePath(), "test_source.png");
    }

    private void saveFile(Bitmap bitmap, String fileDir, String fileName) {
        File imgFile = new File(fileDir, fileName);
        if (!imgFile.exists()) {
            try {
                imgFile.createNewFile();
                try {
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, new FileOutputStream(imgFile));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
