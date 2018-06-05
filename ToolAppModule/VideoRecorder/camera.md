## Android (系统+自定义)短视频录制（含暂停继续录制功能） 总结

     https://blog.csdn.net/android_technology/article/details/69388902

    https://github.com/Gentleman-jun/VideoRecordDemo

    一个集成了微信朋友圈长按视频录制短按拍照功能的库，适配Android7.0，项目集成简单快速
     https://github.com/AndroidEngineerChenXiaoshuang/CSVideo

     <uses-feature android:name="android.hardware.camera.autofocus" android:required="true" />


## 高清视频问题的考虑

     如果使用了setOutputFormate()方法，就不应该使用setProfile方法了，本人亲测使用了setProfile方法之后，后面有关mediaRecorder对象的其他参数就不应该被设置，否则就会报illegalStateException，也就是说在这一个方法中就可以完成低质量和高质量的设置。
     不过还是希望大家不要使用setProfile这个方法，这个方法不够灵活。我们可以自定义MediaRecorder的一些设置。高清视频的设置主要是以下几个方法：
     mediaRecorder.setVideoSize(1920, 1080); // 设置视频大小
     mediaRecorder.setVideoEncodingBitRate(5*1024*1024);
     mediaRecorder.setVideoFrameRate(60); // 设置帧率

 帧率越高，size越大（size是指录像时预览的size和保存的视频尺寸，我们和上面设置的预览size保持一致），bit流越大，视频质量就越高。如果需要低质量的视频就从这几个参数中修改就行了。

## android仿微信录制短视频,拍照,自动聚焦,手动聚焦,滑动缩放功能(Camera+TextureView+rxjava实现)

 https://blog.csdn.net/u012216274/article/details/68059637

## WxRecoderVideo
https://github.com/maimingliang/WxRecoderVideo


## 视频播放
https://blog.csdn.net/itachi85/article/details/7216962

## 音视频转码合成
https://blog.csdn.net/smile3670/article/details/41279749
https://github.com/sannies/mp4parser