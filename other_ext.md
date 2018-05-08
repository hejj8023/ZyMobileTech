compile 'com.github.fangx:haorefresh:1.0'
compile 'com.melnykov:floatingactionbutton:1.3.0'

/* RxKotlin */
implementation 'io.reactivex.rxjava2:rxkotlin:2.1.0'

/* RxBinding */
implementation 'com.jakewharton.rxbinding2:rxbinding:2.0.0'

/* RxLifecycle */
implementation 'com.trello.rxlifecycle2:rxlifecycle-components:2.2.0'
implementation 'com.trello.rxlifecycle2:rxlifecycle-kotlin:2.2.0'

/* RxPermissions */
implementation 'com.tbruyelle.rxpermissions2:rxpermissions:0.9.4'

/* Leak Canary */

//    debugImplementation 'com.squareup.leakcanary:leakcanary-android:1.5.2'

//    releaseImplementation 'com.squareup.leakcanary:leakcanary-android-no-op:1.5.2'

//    testImplementation 'com.squareup.leakcanary:leakcanary-android-no-op:1.5.2'

/* Block Canary */
debugImplementation 'com.github.markzhai:blockcanary-android:1.5.0'
releaseImplementation 'com.github.markzhai:blockcanary-no-op:1.5.0'

/* DBFlow */
kapt 'com.github.Raizlabs.DBFlow:dbflow-processor:4.0.5'
implementation 'com.github.Raizlabs.DBFlow:dbflow-core:4.0.5'
implementation 'com.github.Raizlabs.DBFlow:dbflow:4.0.5'
implementation 'com.github.Raizlabs.DBFlow:dbflow-kotlinextensions:4.0.5'
implementation 'com.android.support.constraint:constraint-layout:1.0.2'
implementation('com.github.ozodrukh:CircularReveal:2.0.1@aar') {
    transitive = true
}

/* BigImageViewer */
implementation 'com.github.piasy:BigImageViewer:1.4.0'
implementation 'com.github.piasy:GlideImageLoader:1.4.0'
implementation 'com.github.piasy:ProgressPieIndicator:1.4.0'

/*网络请求框架*/
compile 'pub.devrel:easypermissions:0.4.2'

compile 'com.zhy:autolayout:1.4.5'
compile 'com.squareup.picasso:picasso:2.5.2'

// Material date time picker
compile ("com.wdullaer:materialdatetimepicker:${datetimePickerVersion}") {
exclude group: 'com.android.support'
}

implementation 'de.hdodenhof:circleimageview:2.2.0'
implementation 'com.github.nirhart:ParallaxScroll:dd53d1f9d1'
implementation 'com.nononsenseapps:filepicker:3.0.1'

implementation 'ch.acra:acra:4.9.2'

implementation 'frankiesardo:icepick:3.2.0'
annotationProcessor 'frankiesardo:icepick-processor:3.2.0'

compile 'com.google.zxing:core:3.2.1'
compile 'de.hdodenhof:circleimageview:2.1.0'
compile 'com.lzy.widget:imagepicker:0.5.5'

compile 'cn.bmob.android:bmob-sdk:3.5.5'
compile 'cn.bmob.android:http-legacy:1.0'

compile 'com.github.orangegangsters:swipy:1.1.0@aar'
compile 'com.github.d-max:spots-dialog:0.7@aar'

compile 'com.jungly:gridPasswordView:0.3'
compile 'com.patrickpissurno:ripple-effect:1.3.1'
compile 'com.github.yiwent:PhysicsLayout:1.1.1'
compile 'com.github.yiwent:ShiftyTextview:1.1.0'

springview = "com.liaoinstan.springview:library:1.3.0"
swipebackhelper = "com.jude:swipebackhelper:3.1.2"

LifeHelper项目

//图片加载框架
compile 'com.squareup.picasso:picasso:2.5.2'
compile 'com.github.bumptech.glide:glide:3.7.0'
compile 'jp.wasabeef:glide-transformations:2.0.1'
//takePhoto框架
compile 'com.jph.takephoto:takephoto_library:4.0.3'
compile 'com.lzy.widget:imagepicker:0.5.0'
//导航栏
compile 'com.flyco.tablayout:FlycoTabLayout_Lib:2.1.0@aar'
//快速索引
compile 'me.yokeyword:indexablerecyclerview:1.3.0'
//BGA系列
compile 'cn.bingoogolapple:bga-refreshlayout:1.1.7'
compile 'cn.bingoogolapple:bga-adapter:1.1.5@aar'
//工具类
compile 'com.blankj:utilcode:1.7.1'
//洪洋大神流式布局
compile 'com.zhy:flowlayout-lib:1.0.3'
compile 'com.github.clans:fab:1.6.1'
//解析Html
compile 'org.jsoup:jsoup:1.8.2'
//电子书
compile 'org.ccil.cowan.tagsoup:tagsoup:1.2.1'
//权限库
compile 'pub.devrel:easypermissions:1.0.1'
//自定义日期控件
compile 'com.prolificinteractive:material-calendarview:1.4.0'
//阿里巴巴
compile('com.alibaba.android:vlayout:1.2.2@aar') {
    transitive = true
}
//视频播放
compile project(':vitamio')
//内存泄漏检测工具
//debugCompile 'com.squareup.leakcanary:leakcanary-android:1.5'
//releaseCompile 'com.squareup.leakcanary:leakcanary-android-no-op:1.5'
//腾讯bug管理平台
compile 'com.tencent.bugly:crashreport:2.6.0'
compile 'com.tencent.bugly:nativecrashreport:3.3.0'
//腾讯x5
compile files('libs/tbs_sdk_thirdapp_v3.2.0.1104_43200_sharewithdownload_withfilereader_withoutGame_obfs_20170609_115346.jar')


//环信
//Optional compile for GCM (Google Cloud Messaging).
//compile 'com.google.android.gms:play-services-gcm:9.4.0'
//compile 'com.hyphenate:hyphenate-sdk:3.3.0'
compile 'com.hyphenate:hyphenate-sdk-lite:3.3.0'



//关于开源库，都已经放到的github上，https://github.com/yangchong211
compile 'cn.yc:YCStateLib:1.1'                                  //状态管理
compile 'cn.yc:YCPhotoLib:1.0'                                  //图片选择器
compile 'cn.yc:YCDialogLib:3.2'                                 //弹窗
compile 'cn.yc:YCCountDownViewLib:1.0'                          //倒计时器
compile 'cn.yc:YCUtilsLib:1.5'                                  //公共类
compile 'cn.yc:YCMultiInputViewLib:1.0'                         //倒计数
compile 'cn.yc:YCProgressLib:1.0'                               //进度条
compile 'cn.yc:YCCardViewLib:1.2'                               //滑动卡片
compile 'cn.yc:YCCustomTextLib:2.1'                             //自定义超文本
compile 'cn.yc:YCRecycleViewLib:1.1'                            //RecycleView封装类，多选单选
compile 'cn.yc:YCWeatherLib:1.0'                                //天气自定义控件
compile 'cn.yc:YCGalleryLib:1.1'                                //自定义画廊
compile 'cn.yc:YCBaseAdapterLib:1.2'                            //adapter封装
compile 'cn.yc:YCBannerLib:1.2'                                 //轮播图
compile 'org.yczbj:YCRefreshViewLib:1.4'                        //RecyclerView封装，刷新加载

    materialDialog = "com.afollestad.material-dialogs:core:$materialDialogVersion"
    circleImage = "de.hdodenhof:circleimageview:$circleImageViewVersion"