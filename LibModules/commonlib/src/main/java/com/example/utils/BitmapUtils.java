package com.example.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Rect;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by example on 2018/4/3.
 */

public class BitmapUtils {
    /**
     * 图片区域截取
     *
     * @param filePath
     * @param reqSize
     * @return
     */
    public static Bitmap regionDecoderByPath(String filePath, int reqSize) {
        Bitmap bitmap = BitmapFactory.decodeFile(filePath);
        try {
            BitmapRegionDecoder regionDecoder = BitmapRegionDecoder.newInstance(filePath, true);
            bitmap = getRegionBitmap(reqSize, bitmap, regionDecoder);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    /**
     * 图片区域截取
     *
     * @param data
     * @param reqSize
     * @return
     */
    public static Bitmap regionDecoderByPath(byte[] data, int reqSize) {
        Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
        try {
            BitmapRegionDecoder regionDecoder = BitmapRegionDecoder.newInstance(data, 0, data.length, true);
            bitmap = getRegionBitmap(reqSize, bitmap, regionDecoder);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    /**
     * 图片区域截取
     *
     * @param is
     * @param reqSize
     * @return
     */
    public static Bitmap regionDecoderByPath(InputStream is, int reqSize) {
        Bitmap bitmap = BitmapFactory.decodeStream(is);
        try {
            BitmapRegionDecoder regionDecoder = BitmapRegionDecoder.newInstance(is, true);
            bitmap = getRegionBitmap(reqSize, bitmap, regionDecoder);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    private static Bitmap getRegionBitmap(int reqSize, Bitmap bitmap, BitmapRegionDecoder regionDecoder) {
        int h = bitmap.getHeight() / 2;
        int w = bitmap.getWidth() / 2;
        Rect react = new Rect();
        int baseLine = reqSize / 2;
        react.left = w - baseLine;
        react.top = h - baseLine;
        react.right = w + baseLine;
        react.bottom = h + baseLine;
        bitmap = regionDecoder.decodeRegion(react, null);
        return bitmap;
    }
}
