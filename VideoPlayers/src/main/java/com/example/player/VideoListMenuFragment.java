package com.example.player;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.player.bean.VideoBean;
import com.example.player.mvp.inter.VideoListContract;
import com.example.player.mvp.presenter.VideoListPresenter;
import com.zhiyangstudio.commonlib.refreshsupport.smartrefresh.BaseMVPSRRListFragment;

/**
 * Created by example on 2018/5/11.
 */

public class VideoListMenuFragment extends BaseMVPSRRListFragment<VideoListPresenter,
        VideoListContract.IVideoListView, VideoBean> implements VideoListContract.IVideoListView {
    @Override
    protected VideoListPresenter createPresenter() {
        return new VideoListPresenter();
    }

    @Override
    protected void loadRemoteData() {
        if (mPresenter != null) {
            mPresenter.getVideoList();
        }
    }

    @Override
    protected void initPageNumb() {

    }

    @Override
    protected BaseQuickAdapter<VideoBean, BaseViewHolder> getListAdapter() {
        return new BaseQuickAdapter<VideoBean, BaseViewHolder>(R.layout
                .layout_item_video_list_menu,mList) {
            @Override
            protected void convert(BaseViewHolder helper, VideoBean item) {

            }
        };
    }

}
