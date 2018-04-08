package com.example.fta.utils;

import com.example.fta.Const;
import com.zhiyangstudio.sdklibrary.common.utils.EmptyUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DecimalFormat;

/**
 * Created by zzg on 2018/4/7.
 */

public class FileUtils {
    /**
     * 生成本地文件路径
     */
    public static File gerateLocalFile(String filePath, String dirName) {
        String fileName = getFileName(filePath);
        File file = new File(Const.SDCARD_PATH, dirName);
        if (!file.exists()) {
            file.mkdirs();
        }
        return new File(file, fileName);
    }

    /**
     * 根据文件路径获取文件名称
     */
    public static String getFileName(String filePath) {
        if (EmptyUtils.isEmpty(filePath)) {
            return "";
        }
        return filePath.substring(filePath.lastIndexOf(File.separator) + 1);
    }

    /**
     * 转换文件大小
     */
    public static String formatFileSize(long fileSize) {
        if (fileSize <= 0) {
            return "0KB";
        }

        DecimalFormat df = new DecimalFormat("#.00");
        String fileSizeStr = "";
        if (fileSize < 1024) {
            fileSizeStr = df.format((double) fileSize) + "B";
        } else if (fileSize < 1048576) {
            fileSizeStr = df.format((double) fileSize / 1024) + "K";
        } else if (fileSize < 1073741824) {
            fileSizeStr = df.format((double) fileSize / 1048576) + "M";
        } else {
            fileSizeStr = df.format((double) fileSize / 1073741824) + "G";
        }
        return fileSizeStr;
    }

    /**
     * 取得文件大小
     */
    public static long getFileSize(File file) throws IOException {
        long size = 0;
        if (file.exists()) {
            FileInputStream fileInputStream = new FileInputStream(file);
            size = fileInputStream.available();
        } else {
            file.createNewFile();
        }
        return size;
    }
}
