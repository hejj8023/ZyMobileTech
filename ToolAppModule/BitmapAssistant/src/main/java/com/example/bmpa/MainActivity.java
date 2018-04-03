package com.example.bmpa;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Environment;
import android.view.View;
import android.widget.ImageView;

import com.example.common.corel.BaseActivity;
import com.example.glide.GlideApp;
import com.example.glide.GlideCircleTransform;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    private File firDir;

    private String sourFileName = "test_source.png";
    private File sourceFile;

    @BindView(R.id.image_view)
    ImageView imageView;

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
        firDir = new File(Environment.getExternalStorageDirectory(), "test");
        if (!firDir.exists()) {
            firDir.mkdirs();
        }

        sourceFile = new File(firDir, sourFileName);
    }

    @Override
    protected PermissionListener getPermissonCallBack() {
        return null;
    }

    private int sv = 700;

    @OnClick({R.id.btn_create_bmp, R.id.btn_sobmp_scale_target,
            R.id.btn_sobmp_crop_target, R.id.btn_scbmp_crop_target,
            R.id.btn_sobmp_crop_target_by_brd, R.id.btn_scbmp_crop_target_by_brd,
            R.id.btn_load_cicle_by_glide
    })
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.btn_create_bmp:
                createSourceFile();
                break;
            case R.id.btn_sobmp_scale_target:
                if (!sourceFile.exists())
                    return;
                scaleSourceBitmap();
                break;
            case R.id.btn_sobmp_crop_target:
                if (sourceFile.exists()) {
                    Bitmap bitmap = BitmapFactory.decodeFile(sourceFile.getAbsolutePath());
                    if (bitmap != null) {
                        int h = bitmap.getHeight() / 2;
                        int w = bitmap.getWidth() / 2;
                        bitmap = Bitmap.createBitmap(bitmap, w - (sv / 2), h - (sv / 2), sv, sv);
                        saveFile(bitmap, firDir.getPath(), "test_source_700x700.png");
                    }
                }
                break;
            case R.id.btn_scbmp_crop_target:
                File file = new File(firDir, "test_1920x1080.png");
                if (file.exists()) {
                    Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
                    int h = bitmap.getHeight() / 2;
                    int w = bitmap.getWidth() / 2;
                    bitmap = Bitmap.createBitmap(bitmap, w - (sv / 2), h - (sv / 2), sv, sv);
                    saveFile(bitmap, firDir.getPath(), "test_scale_700x700.png");
                }
                break;
            case R.id.btn_sobmp_crop_target_by_brd:
                if (sourceFile.exists()) {
                    Bitmap bitmap = BitmapFactory.decodeFile(sourceFile.getAbsolutePath());
                    if (bitmap != null) {
                        int h = bitmap.getHeight() / 2;
                        int w = bitmap.getWidth() / 2;
                        Rect react = new Rect();
                        react.left = w - (sv / 2);
                        react.top = h - (sv / 2);
                        react.right = w + (sv / 2);
                        react.bottom = h + (sv / 2);
                        BitmapRegionDecoder regionDecoder = null;
                        try {
                            regionDecoder = BitmapRegionDecoder.newInstance(sourceFile.getAbsolutePath(), true);
                            Bitmap bitmap1 = regionDecoder.decodeRegion(react, null);
                            saveFile(bitmap1, firDir.getPath(), "test_brd_source_700x700.png");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                break;
            case R.id.btn_scbmp_crop_target_by_brd:
                File tFile = new File(firDir, "test_1920x1080.png");
                if (tFile.exists()) {
                    Bitmap bitmap = BitmapFactory.decodeFile(tFile.getAbsolutePath());
                    try {
                        BitmapRegionDecoder regionDecoder = BitmapRegionDecoder.newInstance(tFile.getAbsolutePath(), true);
                        int h = bitmap.getHeight() / 2;
                        int w = bitmap.getWidth() / 2;
                        Rect react = new Rect();
                        react.left = w - (sv / 2);
                        react.top = h - (sv / 2);
                        react.right = w + (sv / 2);
                        react.bottom = h + (sv / 2);
                        Bitmap bitmap1 = regionDecoder.decodeRegion(react, null);
                        saveFile(bitmap1, firDir.getPath(), "test_brd_scale_700x700.png");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case R.id.btn_load_cicle_by_glide:
                File tmpFile = new File(firDir, "test_brd_scale_700x700.png");
                if (tmpFile.exists()) {
                    Bitmap bitmap = BitmapFactory.decodeFile(tmpFile.getAbsolutePath());
                    try {
                        BitmapRegionDecoder regionDecoder = BitmapRegionDecoder.newInstance(tmpFile.getAbsolutePath(), true);
                        int h = bitmap.getHeight() / 2;
                        int w = bitmap.getWidth() / 2;
                        Rect react = new Rect();
                        int baseLine = 200 / 2;
                        react.left = w - baseLine;
                        react.top = h - baseLine;
                        react.right = w + baseLine;
                        react.bottom = h + baseLine;
                        Bitmap bitmap1 = regionDecoder.decodeRegion(react, null);

                        // TODO: 2018/4/3 使用glide加载circle图片
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        bitmap1.compress(Bitmap.CompressFormat.PNG, 100, baos);
                        byte[] bytes = baos.toByteArray();
                        GlideCircleTransform circleTransform = new GlideCircleTransform(mContext);
                        GlideApp.with(mContext).load(bytes).transform(circleTransform).into
                                (imageView);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                break;
        }
    }

    private void scaleSourceBitmap() {
        Bitmap bitmap = BitmapFactory.decodeFile(sourceFile.getAbsolutePath());
        if (bitmap != null) {
            // 压缩到1920*1080尺寸
            // 使用1。 bitmapsale, 2。 使用option
            bitmap = Bitmap.createScaledBitmap(bitmap, 1920, 1080, true);
            saveFile(bitmap, firDir.getPath(), "test_1920x1080.png");
        }
    }

    private void createSourceFile() {
        if (sourceFile.exists())
            return;

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
        saveFile(bitmap, firDir.getAbsolutePath(), sourFileName);
    }

    private void saveFile(Bitmap bitmap, String fileDir, String fileName) {
        File imgFile = new File(fileDir, fileName);
        if (!imgFile.exists()) {
            try {
                imgFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, new FileOutputStream(imgFile));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
