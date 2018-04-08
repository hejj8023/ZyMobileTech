# ZyAndroidTechs
android,java,面试知识点复习,jee项目汇总

# google sample示例

https://github.com/googlesamples?language=java

# material-camera

    repositories {
        jcenter()
        maven { url "https://dl.bintray.com/drummer-aidan/maven" }
    }
    
    dependencies {
        compile 'com.afollestad:material-camera:0.4.4'
    }

## Android Manifest

    <activity
        android:name="com.afollestad.materialcamera.CaptureActivity"
        android:theme="@style/MaterialCamera.CaptureActivity" />
    <activity
        android:name="com.afollestad.materialcamera.CaptureActivity2"
        android:theme="@style/MaterialCamera.CaptureActivity" />
        
## Code for Video
    private final static int CAMERA_RQ = 6969; 
    
    File saveFolder = new File(Environment.getExternalStorageDirectory(), "MaterialCamera Sample");
    if (!saveFolder.mkdirs())
        throw new RuntimeException("Unable to create save directory, make sure WRITE_EXTERNAL_STORAGE permission is granted.");
    
    new MaterialCamera(this)                               // Constructor takes an Activity
        .allowRetry(true)                                  // Whether or not 'Retry' is visible during playback
        .autoSubmit(false)                                 // Whether or not user is allowed to playback videos after recording. This can affect other things, discussed in the next section.
        .saveDir(saveFolder)                               // The folder recorded videos are saved to
        .primaryColorAttr(R.attr.colorPrimary)             // The theme color used for the camera, defaults to colorPrimary of Activity in the constructor
        .showPortraitWarning(true)                         // Whether or not a warning is displayed if the user presses record in portrait orientation
        .defaultToFrontFacing(false)                       // Whether or not the camera will initially show the front facing camera
        .allowChangeCamera(true)                           // Allows the user to change cameras. 
        .retryExits(false)                                 // If true, the 'Retry' button in the playback screen will exit the camera instead of going back to the recorder
        .restartTimerOnRetry(false)                        // If true, the countdown timer is reset to 0 when the user taps 'Retry' in playback
        .continueTimerInPlayback(false)                    // If true, the countdown timer will continue to go down during playback, rather than pausing.
        .videoEncodingBitRate(1024000)                     // Sets a custom bit rate for video recording.
        .audioEncodingBitRate(50000)                       // Sets a custom bit rate for audio recording.
        .videoFrameRate(24)                                // Sets a custom frame rate (FPS) for video recording.
        .qualityProfile(MaterialCamera.QUALITY_HIGH)       // Sets a quality profile, manually setting bit rates or frame rates with other settings will overwrite individual quality profile settings
        .videoPreferredHeight(720)                         // Sets a preferred height for the recorded video output.
        .videoPreferredAspect(4f / 3f)                     // Sets a preferred aspect ratio for the recorded video output.
        .maxAllowedFileSize(1024 * 1024 * 5)               // Sets a max file size of 5MB, recording will stop if file reaches this limit. Keep in mind, the FAT file system has a file size limit of 4GB.
        .iconRecord(R.drawable.mcam_action_capture)        // Sets a custom icon for the button used to start recording
        .iconStop(R.drawable.mcam_action_stop)             // Sets a custom icon for the button used to stop recording
        .iconFrontCamera(R.drawable.mcam_camera_front)     // Sets a custom icon for the button used to switch to the front camera
        .iconRearCamera(R.drawable.mcam_camera_rear)       // Sets a custom icon for the button used to switch to the rear camera
        .iconPlay(R.drawable.evp_action_play)              // Sets a custom icon used to start playback
        .iconPause(R.drawable.evp_action_pause)            // Sets a custom icon used to pause playback
        .iconRestart(R.drawable.evp_action_restart)        // Sets a custom icon used to restart playback
        .labelRetry(R.string.mcam_retry)                   // Sets a custom button label for the button used to retry recording, when available
        .labelConfirm(R.string.mcam_use_video)             // Sets a custom button label for the button used to confirm/submit a recording
        .autoRecordWithDelaySec(5)                         // The video camera will start recording automatically after a 5 second countdown. This disables switching between the front and back camera initially.
        .autoRecordWithDelayMs(5000)                       // Same as the above, expressed with milliseconds instead of seconds.
        .audioDisabled(false)                              // Set to true to record video without any audio.
        .start(CAMERA_RQ);                                 // Starts the camera activity, the result will be sent back to the current Activity

        
# activity 全屏

    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            
            
# 全屏显示(activity onstart)
    
      if (Build.VERSION.SDK_INT >= 19) {
          View decorView = getWindow().getDecorView();
          decorView.setSystemUiVisibility(
                  View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                          | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                          | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                          | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                          | View.SYSTEM_UI_FLAG_FULLSCREEN
                          | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
      } else {
          View decorView = getWindow().getDecorView();
          int option = View.SYSTEM_UI_FLAG_FULLSCREEN;
          decorView.setSystemUiVisibility(option);
      }
                  
# 屏蔽掉common_lib,使用jitpack库
    allprojects {
        repositories {
            ...
            maven { url 'https://jitpack.io' }
        }
    }
    
    dependencies {
        compile 'com.github.syusikoku:ZYSdk:v1.0.0'
    }                  
    
  不使用common_lib，在线的github组织仓库    
  
# 重要说明:
  本项目在本地开发避免开两个工程来回修改，setting.gradle中做如下操作:
  
      include ':ZySdkLib'
      project(':ZySdkLib').projectDir = new File('../ZYSdk/SDKLibrary')
      
  使用使用的工程中做如下依赖:
  
      compile project(':ZySdkLib')