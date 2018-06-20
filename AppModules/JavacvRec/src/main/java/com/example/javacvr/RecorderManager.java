package com.example.javacvr;

import android.app.Activity;
import android.hardware.Camera;
import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.os.Environment;
import android.util.Log;
import android.view.SurfaceView;

import com.googlecode.javacv.FFmpegFrameRecorder;
import com.googlecode.javacv.cpp.opencv_core.CvMat;
import com.googlecode.javacv.cpp.opencv_core.CvPoint2D32f;
import com.googlecode.javacv.cpp.opencv_core.IplImage;

import java.io.File;
import java.nio.Buffer;
import java.nio.ShortBuffer;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.googlecode.javacv.cpp.opencv_core.CV_32FC1;
import static com.googlecode.javacv.cpp.opencv_core.IPL_DEPTH_8U;
import static com.googlecode.javacv.cpp.opencv_core.cvCreateMat;
import static com.googlecode.javacv.cpp.opencv_imgproc.cv2DRotationMatrix;
import static com.googlecode.javacv.cpp.opencv_imgproc.cvWarpAffine;

/**
 * Recorder controller, used to start,stop record, and combine all the videos
 * together
 *
 * @author xiaodong
 */
public class RecorderManager {

    public static final String LOG_TAG = "RecorderManager";
    private MediaRecorder mediaRecorder = null;
    private CameraManager cameraManager = null;
    private String parentPath = null;
    private List<String> videoTempFiles = new ArrayList<String>();
    private SurfaceView mySurfaceView = null;
    public static final int MAX_TIME = 6000;
    private boolean isMax = false;
    private long videoStartTime;
    private int totalTime = 0;
    private boolean isStart = false;
    Activity mainActivity = null;

    private IplImage yuvIplimage = null;
    private volatile FFmpegFrameRecorder recorder;
    private int sampleAudioRateInHz = 44100;
    private int frameRate = 30;
    /* audio data getting thread */
    private AudioRecord audioRecord;
    private AudioRecordRunnable audioRecordRunnable;
    private Thread audioThread;
    volatile boolean runAudioThread = true;

    private boolean isFinished = false;

    public RecorderManager(CameraManager cameraManager,
                           SurfaceView mySurfaceView, Activity mainActivity) {
        this.cameraManager = cameraManager;
        this.mySurfaceView = mySurfaceView;
        this.mainActivity = mainActivity;
        parentPath = generateParentFolder();
        reset();

    }

    private Camera getCamera() {
        return cameraManager.getCamera();
    }

    public boolean isStart() {
        return isStart;
    }

    public long getVideoStartTime() {
        return videoStartTime;
    }

    public int checkIfMax(long timeNow) {

        int during = 0;
        if (isStart) {
            during = (int) (totalTime + (timeNow - videoStartTime));
            System.out.println(during + ",T:" + totalTime + ",N:" + timeNow
                    + ",S:" + videoStartTime);
            if (during >= MAX_TIME) {
                stopRecord();
                during = MAX_TIME;
                isMax = true;
            }
        } else {
            during = totalTime;
            if (during >= MAX_TIME) {
                during = MAX_TIME;
            }
        }

        return during;
    }

    // ---------------------------------------
    // initialize ffmpeg_recorder
    // ---------------------------------------
    public void initRecorder() {
        String ffmpeg_link = parentPath + "/" + "video.mp4";
        Log.e(LOG_TAG, "init recorder");

        if (yuvIplimage == null) {
            yuvIplimage = IplImage.create(cameraManager.getDefaultSize().width,
                    cameraManager.getDefaultSize().height, IPL_DEPTH_8U, 2);
            Log.e(LOG_TAG, "create yuvIplimage");
        }

        Log.e(LOG_TAG, "ffmpeg_url: " + ffmpeg_link);
        recorder = new FFmpegFrameRecorder(ffmpeg_link,
                cameraManager.getDefaultSize().width,
                cameraManager.getDefaultSize().height, 1);
        recorder.setFormat("mp4");
        recorder.setSampleRate(sampleAudioRateInHz);
        // Set in the surface changed method
        recorder.setFrameRate(frameRate);

        Log.e(LOG_TAG, "recorder initialize success");

        audioRecordRunnable = new AudioRecordRunnable();
        audioThread = new Thread(audioRecordRunnable);
        try {
            recorder.start();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        audioThread.start();
    }

    // ---------------------------------------------
    // audio thread, gets and encodes audio data
    // ---------------------------------------------
    class AudioRecordRunnable implements Runnable {

        @Override
        public void run() {
            android.os.Process
                    .setThreadPriority(android.os.Process.THREAD_PRIORITY_URGENT_AUDIO);

            // Audio
            int bufferSize;
            short[] audioData;
            int bufferReadResult;

            bufferSize = AudioRecord.getMinBufferSize(sampleAudioRateInHz,
                    AudioFormat.CHANNEL_CONFIGURATION_MONO,
                    AudioFormat.ENCODING_PCM_16BIT);
            audioRecord = new AudioRecord(MediaRecorder.AudioSource.MIC,
                    sampleAudioRateInHz,
                    AudioFormat.CHANNEL_CONFIGURATION_MONO,
                    AudioFormat.ENCODING_PCM_16BIT, bufferSize);

            audioData = new short[bufferSize];

            Log.d(LOG_TAG, "audioRecord.startRecording()");
            audioRecord.startRecording();

            /* ffmpeg_audio encoding loop */
            while (!isFinished) {
                // Log.e(LOG_TAG,"recording? " + recording);
                bufferReadResult = audioRecord.read(audioData, 0,
                        audioData.length);
                if (bufferReadResult > 0) {
                    // Log.e(LOG_TAG, "bufferReadResult: " + bufferReadResult);
                    // If "recording" isn't true when start this thread, it
                    // never get's set according to this if statement...!!!
                    // Why? Good question...
                    if (isStart) {
                        try {
                            Buffer[] barray = new Buffer[1];
                            barray[0] = ShortBuffer.wrap(audioData, 0,
                                    bufferReadResult);
                            recorder.record(barray);
                            // Log.e(LOG_TAG,"recording " + 1024*i + " to " +
                            // 1024*i+1024);
                        } catch (FFmpegFrameRecorder.Exception e) {
                            Log.e(LOG_TAG, e.getMessage());
                            e.printStackTrace();
                        }
                    }
                }
            }
            Log.e(LOG_TAG, "AudioThread Finished, release audioRecord");

            /* encoding finish, release recorder */
            if (audioRecord != null) {
                audioRecord.stop();
                audioRecord.release();
                audioRecord = null;
                Log.e(LOG_TAG, "audioRecord released");
            }
        }
    }

    public void reset() {
        for (String file : videoTempFiles) {
            File tempFile = new File(file);
            if (tempFile.exists()) {
                tempFile.delete();
            }
        }
        videoTempFiles = new ArrayList<String>();
        isStart = false;
        totalTime = 0;
        isMax = false;
    }

    public List<String> getVideoTempFiles() {
        return videoTempFiles;
    }

    public String getVideoParentpath() {
        return parentPath;
    }

    public void startRecord() {
        Log.e(LOG_TAG, "startRecord ... ");
        if (isMax) {
            return;
        }
        isStart = true;
        videoStartTime = new Date().getTime();
    }

    public void stopRecord() {
        Log.e(LOG_TAG, "stopRecord ... ");
        if (recorder != null && isStart) {
            runAudioThread = false;
            if (!isMax) {
                totalTime += new Date().getTime() - videoStartTime;
                videoStartTime = 0;
            }
            isStart = false;

        }
    }

    public void releaseRecord() {
        Log.e(LOG_TAG, "releaseRecord ... ");
        isFinished = true;
        Log.e(LOG_TAG, "Finishing recording, calling stop and release on recorder");
        try {
            recorder.stop();
            recorder.release();
        } catch (FFmpegFrameRecorder.Exception e) {
            e.printStackTrace();
        }
        recorder = null;
    }

    public void onPreviewFrame(byte[] data, Camera camera) {
        Log.e(LOG_TAG, "onPreviewFrame ... ");
        int during = checkIfMax(new Date().getTime());
        /* get video data */
        if (yuvIplimage != null && isStart) {
            yuvIplimage.getByteBuffer().put(data);
            //yuvIplimage = rotateImage(yuvIplimage.asCvMat(), 90).asIplImage();
            Log.e(LOG_TAG, "Writing Frame");
            try {
                Log.e(LOG_TAG, (System.currentTimeMillis() - videoStartTime) + "");
                if (during < 6000) {
                    Log.e(LOG_TAG, "recorder.record");
                    recorder.setTimestamp(1000 * during);
                    recorder.record(yuvIplimage);
                }
            } catch (FFmpegFrameRecorder.Exception e) {
                Log.e(LOG_TAG, e.getMessage());
                e.printStackTrace();
            }
        }
    }

    public CvMat rotateImage(CvMat input, int angle) {
        CvPoint2D32f center = new CvPoint2D32f(input.cols() / 2.0F,
                input.rows() / 2.0F);

        CvMat rotMat = cvCreateMat(2, 3, CV_32FC1);
        cv2DRotationMatrix(center, angle, 1, rotMat);
        CvMat dst = cvCreateMat(input.rows(), input.cols(), input.type());
        cvWarpAffine(input, dst, rotMat);
        return dst;

    }

    public String generateParentFolder() {
        String parentPath = Environment.getExternalStorageDirectory()
                .getAbsolutePath() + "/mycapture/video/temp";
        File tempFile = new File(parentPath);
        if (!tempFile.exists()) {
            tempFile.mkdirs();
        }
        return parentPath;

    }
}
