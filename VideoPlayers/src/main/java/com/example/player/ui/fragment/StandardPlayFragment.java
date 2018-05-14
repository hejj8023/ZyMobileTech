package com.example.player.ui.fragment;

import android.os.Bundle;

import com.example.player.R;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;
import com.zhiyangstudio.commonlib.corel.BaseFragment;

/**
 * Created by example on 2018/5/14.
 */

public class StandardPlayFragment extends BaseFragment {

    private StandardGSYVideoPlayer mGsyVideoPlayer;

    @Override
    public int getContentId() {
        return R.layout.fragment_standard_play;
    }

    @Override
    public void initView() {
        mGsyVideoPlayer = mRootView.findViewById(R.id.player);
    }

    @Override
    public void addListener() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void refreshUi() {

    }

    @Override
    public void release() {

    }

    @Override
    protected void initArguments(Bundle bundle) {

    }
}
