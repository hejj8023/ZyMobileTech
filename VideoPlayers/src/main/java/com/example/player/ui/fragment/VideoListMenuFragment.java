package com.example.player.ui.fragment;

import android.os.Bundle;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.player.R;
import com.example.player.bean.VideoBean;
import com.example.player.mvp.inter.VideoListContract;
import com.example.player.mvp.presenter.VideoListPresenter;
import com.example.player.ui.activity.GSYVideoPlayerActivity;
import com.example.player.ui.activity.MainActivity;
import com.zhiyangstudio.commonlib.refreshsupport.smartrefresh.BaseMVPSRRListFragment;
import com.zhiyangstudio.commonlib.utils.IntentUtils;
import com.zhiyangstudio.commonlib.utils.UiUtils;

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
        mLoadingLayout.setBackgroundColor(UiUtils.getColor(R.color.colorPrimary));
    }

    @Override
    protected BaseQuickAdapter<VideoBean, BaseViewHolder> getListAdapter() {
        return new BaseQuickAdapter<VideoBean, BaseViewHolder>(R.layout
                .layout_item_video_list_menu, mList) {
            @Override
            protected void convert(BaseViewHolder helper, VideoBean item) {
                helper.setText(R.id.tv_video_name, item.getFileName());
                helper.setText(R.id.tv_video_size, item.getDuration());
                helper.setOnClickListener(R.id.ll_root, v -> {
                    ((MainActivity)getActivity()).closeDrawer();
                    // 先使用GSYVideoPlayer，再使用exoplayer
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("videoBean", item);
                    IntentUtils.forward(GSYVideoPlayerActivity.class,bundle);
                });
            }
        };
    }

}
