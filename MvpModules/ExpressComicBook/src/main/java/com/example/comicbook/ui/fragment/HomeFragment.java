package com.example.comicbook.ui.fragment;

import com.example.comicbook.R;
import com.example.comicbook.mvp.contract.HomeListContract;
import com.example.comicbook.mvp.presenter.HomeListPresenter;
import com.zysdk.vulture.clib.sample.fragment.BaseSampleFragment;

public class HomeFragment extends BaseSampleFragment<HomeListPresenter, HomeListContract
        .IHomeView> implements HomeListContract.IHomeView {

    @Override
    protected HomeListPresenter createPresenter() {
        return new HomeListPresenter();
    }

    @Override
    public int getContentId() {
        return R.layout.fragment_home_list;
    }
}
