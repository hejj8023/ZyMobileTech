package com.example.fta.manager;

import android.os.Environment;

import com.zysdk.vulture.clib.utils.LogListener;
import com.zysdk.vulture.clib.utils.LoggerUtils;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by zzg on 2018/4/1.
 */

public class FtpClientManager implements LogListener {
    private static FtpClientManager INSTANCE = null;
    private String mUserName, mUserPwd, mServerIp, mServerPort;
    private FTPClient ftpClient;
    private int connectTimeout = 5000;
    private boolean hasConnectSucess;

    private FtpClientManager() {
    }

    public static FtpClientManager getInstance() {
        if (INSTANCE == null) {
            synchronized (FtpClientManager.class) {
                if (INSTANCE == null) {
                    INSTANCE = new FtpClientManager();
                }
            }
        }
        return INSTANCE;
    }

    public void setUserName(String name) {
        this.mUserName = name;
    }

    public void setServerIp(String ipAddress) {
        this.mServerIp = ipAddress;
    }

    public void setServerPort(String port) {
        this.mServerPort = port;
    }

    public void setUserPwd(String pwd) {
        this.mUserPwd = pwd;
    }

    public void init() {
        ftpClient = new FTPClient();
        //设定连接超时时间
        ftpClient.setConnectTimeout(connectTimeout);
    }

    /**
     * 断开FTP服务器
     */
    public void disconnectFTP() {
        try {
            ftpClient.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 上传文件
     *
     * @param FilePath 要上传文件所在SDCard的路径
     * @param FileName 要上传的文件的文件名(如：Sim唯一标识码)
     * @return true为成功，false为失败
     */
    public void uploadFile(String FilePath, String FileName) {

        if (!ftpClient.isConnected()) {
            LoggerUtils.loge(this, "ftp未连接...");
            return;
        }

        try {

            // 设置存储路径
            ftpClient.makeDirectory("/FtpFileTest");
            ftpClient.changeWorkingDirectory("/FtpFileTest");

            // 设置上传文件需要的一些基本信息
            ftpClient.setBufferSize(1024);
            ftpClient.setControlEncoding("UTF-8");
            ftpClient.enterLocalPassiveMode();
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

            // 文件上传吧～
            FileInputStream fileInputStream = new FileInputStream(FilePath);
            ftpClient.storeFile(FileName, fileInputStream);

            // 关闭文件流
            fileInputStream.close();

            // 退出登陆FTP，关闭ftpCLient的连接
            ftpClient.logout();
            ftpClient.disconnect();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 下载文件
     *
     * @param dir      要存放的文件夹名称
     * @param fileName 远程FTP服务器上的那个文件的名字
     * @return true为成功，false为失败
     */
    public void downLoadFile(String dir, String fileName) {
        try {
            if (!ftpClient.isConnected()) {
                LoggerUtils.loge(this, "ftp未连接...");
                connect();
            }

            if (!hasConnectSucess)
                return;
            // TODO: 2018/3/14 转到下载目录
            ftpClient.changeWorkingDirectory("/data");

            // 列出该目录下所有文件
            FTPFile[] files = ftpClient.listFiles();

            // 遍历所有文件，找到需要下载的文件的文件
            for (FTPFile file : files) {
                LoggerUtils.loge(this, "file -> " + file.getName());
                if (file.getName().equals(fileName)) {
                    // TODO: 2018/3/14 找到需要下载的文件才执行下载操作
                    // 根据绝对路径初始化文件
                    File localFile = new File(Environment.getExternalStorageDirectory(), dir);
                    if (!localFile.exists()) {
                        localFile.mkdir();
                    }
                    // 输出流
                    OutputStream outputStream = new FileOutputStream(localFile + "/" + file.getName());

                    // 下载文件
                    ftpClient.retrieveFile(file.getName(), outputStream);

                    // 清除缓存
                    outputStream.flush();

                    // 关闭流
                    outputStream.close();
                } else {
                    LoggerUtils.loge(this, "要下载的文件未找到,请确认文件名是否正确");
                }
            }

            // 退出登陆FTP，关闭ftpCLient的连接
            ftpClient.logout();
            ftpClient.disconnect();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void connect() throws IOException {
        if (hasConnectSucess)
            return;

        // 要连接的FTP服务器Url,Port
        ftpClient.connect(mServerIp, Integer.parseInt(mServerPort));

        // 登陆FTP服务器
        ftpClient.login(mUserName, mUserPwd);

        // 看返回的值是不是230，如果是，表示登陆成功
        int reply = ftpClient.getReplyCode();

        LoggerUtils.loge(this, "replyCode =" + reply);
        if (!FTPReply.isPositiveCompletion(reply)) {
            // 断开
            ftpClient.disconnect();
        }
        hasConnectSucess = true;

    }

    public FTPFile[] getFileList() throws IOException {
        if (!ftpClient.isConnected()) {
            LoggerUtils.loge(this, "ftp未连接...");
            connect();
        }

        if (!hasConnectSucess)
            return new FTPFile[0];

        // 列出该目录下所有文件
        try {
            FTPFile[] files = ftpClient.listFiles();
            return files;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new FTPFile[0];
    }
}
