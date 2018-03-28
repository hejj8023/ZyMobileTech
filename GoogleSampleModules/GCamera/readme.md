# ZyCameraView参考资料
## SweetCamera
https://github.com/WellerV/SweetCamera

## JCameraView
https://github.com/CJT2325/CameraView

## google CameraView
https://github.com/google/cameraview



图片操作:
//将960×1280缩放到540×800
Bitmap sizeBitmap = Bitmap.createScaledBitmap(rotaBitmap, 540, 800, true);
截取
Bitmap rectBitmap = Bitmap.createBitmap(sizeBitmap, 100, 200, 300, 300);

Android使用BitmapRegionDecoder获取指定区域的图片
https://blog.csdn.net/vaecer/article/details/42988427

准备：概念说明
1、Drawable就是一个可画的对象,其可能是一张位图(BitmapDrawable),也可能是一个图形(ShapeDrawable),还有可能是一个图层(LayerDrawable),我们根据画图的需求，创建相应的可画对象 
2、Canvas画布，绘图的目的区域，用于绘图 
3、Bitmap位图，用于图的处理 
4、Matrix矩阵 ,图像矩阵

1.从resources中获取Bitmap

     Resources res = getResources();
     Bitmap bmp = BitmapFactory.decodeResource(res, R.drawable.icon);

2.Bitmap → byte[]

      public byte[] Bitmap2Bytes(Bitmap bm) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
            return baos.toByteArray();
      }

3.byte[] → Bitmap

    public Bitmap Bytes2Bimap(byte[] b) {
        if (b.length != 0) {
            return BitmapFactory.decodeByteArray(b, 0, b.length);
        } else {
            return null;
        }
    }

4.Bitmap缩放

    public static Bitmap zoomBitmap(Bitmap bitmap, int width, int height) {
        int w = bitmap.getWidth();
        int h = bitmap.getHeight();
        Matrix matrix = new Matrix();
        float scaleWidth = ((float) width / w);
        float scaleHeight = ((float) height / h);
        matrix.postScale(scaleWidth, scaleHeight);
        Bitmap newbmp = Bitmap.createBitmap(bitmap, 0, 0, w, h, matrix, true);
        return newbmp;
    }

5.将Drawable转化为Bitmap

    public static Bitmap drawableToBitmap(Drawable drawable) {
        // 取 drawable 的长宽
        int w = drawable.getIntrinsicWidth();
        int h = drawable.getIntrinsicHeight();
    
        // 取 drawable 的颜色格式
        Bitmap.Config config = drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
                : Bitmap.Config.RGB_565;
        // 建立对应 bitmap
        Bitmap bitmap = Bitmap.createBitmap(w, h, config);
        // 建立对应 bitmap 的画布
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, w, h);
        // 把 drawable 内容画到画布中
        drawable.draw(canvas);
        return bitmap;
    }

6.获得圆角图片

    public static Bitmap getRoundedCornerBitmap(Bitmap bitmap, float roundPx) {
        int w = bitmap.getWidth();
        int h = bitmap.getHeight();
        Bitmap output = Bitmap.createBitmap(w, h, Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, w, h);
        final RectF rectF = new RectF(rect);
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
    
        return output;
    }

7.Bitmap转换成Drawable

     Bitmap bm=xxx; //xxx根据你的情况获取
     BitmapDrawable bd= new BitmapDrawable(getResource(), bm); 
     //因为BtimapDrawable是Drawable的子类，最终直接使用bd对象即可。

8.Drawable缩放

    public static Drawable zoomDrawable(Drawable drawable, int w, int h) {
        int width = drawable.getIntrinsicWidth();
        int height = drawable.getIntrinsicHeight();
        // drawable转换成bitmap
        Bitmap oldbmp = drawableToBitmap(drawable);
        // 创建操作图片用的Matrix对象
        Matrix matrix = new Matrix();
        // 计算缩放比例
        float sx = ((float) w / width);
        float sy = ((float) h / height);
        // 设置缩放比例
        matrix.postScale(sx, sy);
        // 建立新的bitmap，其内容是对原bitmap的缩放后的图
       Bitmap newbmp = Bitmap.createBitmap(oldbmp, 0, 0, width, height,matrix, true);
        return new BitmapDrawable(newbmp);
    }