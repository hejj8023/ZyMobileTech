package com.example.player.mvp.model;

import com.example.player.Const;
import com.example.player.bean.VideoBean;
import com.example.player.mvp.inter.VideoListContract;
import com.zhiyangstudio.commonlib.utils.FileUtils;
import com.zhiyangstudio.commonlib.utils.RxUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;

/**
 * Created by example on 2018/5/11.
 */

public class VideoListModel implements VideoListContract.IVideoListModel {
    @Override
    public void loadVidels(Observer<List<VideoBean>> observer) {
        Observable.create(new ObservableOnSubscribe<List<VideoBean>>() {
            @Override
            public void subscribe(ObservableEmitter<List<VideoBean>> e) throws Exception {
                // TODO: 2018/5/11 先获取指定目录下的所有的视频，以后再做扫描整个手机的视频
                List<VideoBean> videoBeans = new ArrayList<>();
                File file = new File(Const.VIDEO_DIR_PATH);
                if (file != null && file.exists()) {
                    File[] files = file.listFiles();
                    if (files != null && files.length > 0) {
                        VideoBean videoBean = null;
                        for (int i = 0; i < files.length; i++) {
                            File tF = files[i];
                            videoBean = new VideoBean();
                            String filePath = tF.getAbsolutePath();
                            videoBean.setFilePath(filePath);
                            String fileName = file.getName();
                            videoBean.setFileName(fileName);
                            videoBean.setLength(FileUtils.getFileSize(tF));
                            videoBeans.add(videoBean);
                        }
                    }
                }
                e.onNext(videoBeans);
            }
        }).compose(RxUtils.io_main()).subscribe(observer);
    }
}
