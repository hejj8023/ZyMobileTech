package com.example.bmpa;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Environment;
import android.view.View;
import android.widget.ImageView;

import com.zhiyangstudio.commonlib.corel.BaseActivity;
import com.zhiyangstudio.commonlib.glide.GlideUtils;
import com.zhiyangstudio.commonlib.utils.BitmapUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import butterknife.BindView;
import butterknife.OnClick;

public class PicCropScaleActivity extends BaseActivity {

    @BindView(R.id.image_view)
    ImageView imageView;
    private File firDir;
    private String sourFileName = "test_source.png";
    private File sourceFile;
    private int sv = 700;

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
                        bitmap = BitmapUtils.cropCenter(bitmap, sv);
                        saveFile(bitmap, firDir.getPath(), "test_source_700x700.png");
                    }
                }
                break;
            case R.id.btn_scbmp_crop_target:
                File file = new File(firDir, "test_1920x1080.png");
                if (file.exists()) {
                    Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
                    bitmap = BitmapUtils.cropCenter(bitmap, sv);
                    saveFile(bitmap, firDir.getPath(), "test_scale_700x700.png");
                }
                break;
            case R.id.btn_sobmp_crop_target_by_brd:
                if (sourceFile.exists()) {
                    Bitmap bitmap = BitmapUtils.cropRegoinDecoder(sourceFile.getAbsolutePath(), sv);
                    if (bitmap != null) {
                        saveFile(bitmap, firDir.getPath(), "test_brd_source_700x700.png");
                    }
                }
                break;
            case R.id.btn_scbmp_crop_target_by_brd:
                File tFile = new File(firDir, "test_1920x1080.png");
                if (tFile.exists()) {
                    Bitmap bitmap = BitmapUtils.cropRegoinDecoder(tFile.getAbsolutePath(), sv);
                    if (bitmap != null) {
                        saveFile(bitmap, firDir.getPath(), "test_brd_scale_700x700.png");
                    }
                }
                break;
            case R.id.btn_load_cicle_by_glide:
                File tmpFile = new File(firDir, "test_brd_scale_700x700.png");
                if (tmpFile.exists()) {
                    Bitmap bitmap = BitmapUtils.cropRegoinDecoder(tmpFile.getAbsolutePath(), 200);
                    if (bitmap != null) {
                        // TODO: 2018/4/3 使用glide加载circle图片
                        GlideUtils.displayCircle(mContext, bitmap, imageView);
                    }
                }
                break;
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

    private void scaleSourceBitmap() {
        Bitmap bitmap = BitmapFactory.decodeFile(sourceFile.getAbsolutePath());
        if (bitmap != null) {
            // 压缩到1920*1080尺寸
            // 使用1。 bitmapsale, 2。 使用option
            bitmap = BitmapUtils.scale(bitmap, 1920, 1080);
            saveFile(bitmap, firDir.getPath(), "test_1920x1080.png");
        }
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
