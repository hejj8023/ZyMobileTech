#ijkplayer

    https://github.com/Bilibili/ijkplayer

    # required
    allprojects {
        repositories {
            jcenter()
        }
    }
    
    dependencies {
        # required, enough for most devices.
        compile 'tv.danmaku.ijk.media:ijkplayer-java:0.8.8'
        compile 'tv.danmaku.ijk.media:ijkplayer-armv7a:0.8.8'
    
        # Other ABIs: optional
        compile 'tv.danmaku.ijk.media:ijkplayer-armv5:0.8.8'
        compile 'tv.danmaku.ijk.media:ijkplayer-arm64:0.8.8'
        compile 'tv.danmaku.ijk.media:ijkplayer-x86:0.8.8'
        compile 'tv.danmaku.ijk.media:ijkplayer-x86_64:0.8.8'
    
        # ExoPlayer as IMediaPlayer: optional, experimental
        compile 'tv.danmaku.ijk.media:ijkplayer-exo:0.8.8'
    }
# ExoPlayer

    repositories {
    jcenter()
    google()
    }
    https://github.com/google/ExoPlayer
    implementation 'com.google.android.exoplayer:exoplayer-core:2.X.X'
    implementation 'com.google.android.exoplayer:exoplayer-dash:2.X.X'
    implementation 'com.google.android.exoplayer:exoplayer-ui:2.X.X'
    implementation 'com.google.android.exoplayer:exoplayer:2.X.X'
    
    android-UniversalMusicPlaye (google基于exoplayer)
    https://github.com/googlesamples/android-UniversalMusicPlayer
    
    compileOnly 'com.google.android.wearable:wearable:2.3.0'
    
    implementation 'com.google.android.gms:play-services-cast-framework:15.0.0'
    implementation 'com.google.android.support:wearable:2.3.0'
    implementation 'com.android.support:appcompat-v7:27.1.1'
    implementation 'com.android.support:cardview-v7:27.1.1'
    implementation 'com.android.support:mediarouter-v7:27.1.1'
    implementation 'com.android.support:leanback-v17:27.1.1'
    implementation 'com.android.support:design:27.1.1'
    implementation 'com.android.support:percent:27.1.1'
    
    implementation 'com.google.android.exoplayer:exoplayer:2.7.3'
    
    testImplementation 'junit:junit:4.12'
    testImplementation 'org.mockito:mockito-core:2.15.0'
    androidTestImplementation 'junit:junit:4.12'
    androidTestImplementation 'com.android.support:support-annotations:27.1.1'
    androidTestImplementation 'com.android.support.test:runner:1.0.2'
    androidTestImplementation 'com.android.support.test:rules:1.0.2'

#GSYVideoPlayer
    https://github.com/CarGuo/GSYVideoPlayer
    //完整版引入
    compile 'com.shuyu:GSYVideoPlayer:4.1.2'
    compile 'com.shuyu:gsyVideoPlayer-java:4.1.2'
    
    //根据你的需求
    compile 'com.shuyu:gsyVideoPlayer-armv5:4.1.2'
    compile 'com.shuyu:gsyVideoPlayer-armv7a:4.1.2'
    compile 'com.shuyu:gsyVideoPlayer-arm64:4.1.2'
    compile 'com.shuyu:gsyVideoPlayer-x64:4.1.2'
    compile 'com.shuyu:gsyVideoPlayer-x86:4.1.2'
    compile 'com.shuyu:gsyVideoPlayer-java:4.1.2'
    
    compile 'com.shuyu:gsyVideoPlayer-ex_so:4.1.2'



JieCaoVideoPlayer

Timber

PLDroidPlayer

VideoPlayerManager