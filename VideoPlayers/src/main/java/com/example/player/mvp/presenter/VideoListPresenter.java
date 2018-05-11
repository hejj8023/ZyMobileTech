package com.example.player.mvp.presenter;

import com.example.player.bean.VideoBean;
import com.example.player.mvp.inter.VideoListContract;
import com.example.player.mvp.model.VideoListModel;
import com.zhiyangstudio.commonlib.mvp.presenter.BasePresenter;
import com.zhiyangstudio.commonlib.net.callback.AbsBaseObserver;

import java.util.List;

/**
 * Created by example on 2018/5/11.
 */

public class VideoListPresenter extends BasePresenter<VideoListContract.IVideoListView>
        implements VideoListContract.IVideoListPresenter {

    private final VideoListModel mModel;
    private VideoListContract.IVideoListView mListView;

    public VideoListPresenter() {
        mModel = new VideoListModel();
    }

    @Override
    public void getVideoList() {
        mListView = getView();
        mModel.loadVidels(new AbsBaseObserver<List<VideoBean>>(this, VideoListModel.class.getName
                ()) {
            @Override
            public void onNext(List<VideoBean> videoBeans) {
                if (videoBeans != null && videoBeans.size() > 0) {
                    mListView.setData(videoBeans);
                }
            }
        });
    }
}
