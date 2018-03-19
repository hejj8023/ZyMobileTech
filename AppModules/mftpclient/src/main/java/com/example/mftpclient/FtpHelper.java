package com.example.mftpclient;

import android.os.Environment;

import com.example.utils.LogListener;
import com.example.utils.LoggerUtils;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.SocketException;

/**
 * Created by example on 2018/3/14.
 */

public class FtpHelper implements LogListener {
    public static FtpHelper mInstance;
    private final FTPClient ftpClient;
    private String serverAddress;
    private int serverPort;
    private String serverUName;
    private String serverPwd;
    private int connectTimeout = 5000;

    private FtpHelper(String ipAddress, int port, String userName, String pwd) {
        this.serverAddress = ipAddress;
        this.serverPort = port;
        this.serverUName = userName;
        this.serverPwd = pwd;
        ftpClient = new FTPClient();
    }

    public static FtpHelper getInstance(String ipAddress, int port, String userName, String pwd) {
        if (mInstance == null) {
            synchronized (FtpHelper.class) {
                if (mInstance == null) {
                    mInstance = new FtpHelper(ipAddress, port, userName, pwd);
                }
            }
        }
        return mInstance;
    }

    public boolean initFTPSetting() {

        int reply;

        try {
            //设定连接超时时间
            ftpClient.setConnectTimeout(connectTimeout);

            // 要连接的FTP服务器Url,Port
            ftpClient.connect(serverAddress, serverPort);

            // 登陆FTP服务器
            ftpClient.login(serverUName, serverPwd);

            // 看返回的值是不是230，如果是，表示登陆成功
            reply = ftpClient.getReplyCode();

            LoggerUtils.loge(this, "replyCode =" + reply);
            if (!FTPReply.isPositiveCompletion(reply)) {
                // 断开
                ftpClient.disconnect();
                return false;
            }

            return true;

        } catch (SocketException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 断开FTP服务器
     */
    public boolean disconnectFTP() {
        try {
            ftpClient.disconnect();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 上传文件
     *
     * @param FilePath 要上传文件所在SDCard的路径
     * @param FileName 要上传的文件的文件名(如：Sim唯一标识码)
     * @return true为成功，false为失败
     */
    public boolean uploadFile(String FilePath, String FileName) {

        if (!ftpClient.isConnected()) {
            if (!initFTPSetting()) {
                return false;
            }
        }

        try {

            // 设置存储路径
//            ftpClient.makeDirectory("/FtpFileTest");
//            ftpClient.changeWorkingDirectory("/FtpFileTest");

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
            return false;
        }
        return true;
    }

    /**
     * 下载文件
     *
     * @param dir      要存放的文件夹名称
     * @param fileName 远程FTP服务器上的那个文件的名字
     * @return true为成功，false为失败
     */
    public boolean downLoadFile(String dir, String fileName) {
        if (!ftpClient.isConnected()) {
            if (!initFTPSetting()) {
                return false;
            }
        }

        try {

            // TODO: 2018/3/14 转到下载目录
            ftpClient.changeWorkingDirectory("/data");

            // 列出该目录下所有文件
            FTPFile[] files = ftpClient.listFiles();

            // 遍历所有文件，找到需要下载的文件的文件
            for (FTPFile file : files) {
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
            return false;
        }

        return true;
    }
}
