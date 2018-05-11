package com.example.player.mvp.inter;

import com.example.player.bean.VideoBean;
import com.zhiyangstudio.commonlib.mvp.inter.ISampleRefreshView;

import java.util.List;

import io.reactivex.Observer;

/**
 * Created by example on 2018/5/11.
 */

public interface VideoListContract {
    public interface IVideoListView extends ISampleRefreshView<VideoBean> {
    }

    public interface IVideoListPresenter {
        void getVideoList();
    }

    public interface IVideoListModel {
        void loadVidels(Observer<List<VideoBean>> observer);
    }
}
