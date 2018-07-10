package com.example.comicbook.ui.fragment;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.comicbook.bean.Comic;
import com.example.comicbook.mvp.contract.HomeListContract;
import com.example.comicbook.mvp.presenter.HomeListPresenter;
import com.zysdk.vulture.clib.refreshsupport.rx.smartrefresh.RxBaseMVPSRRListFragment;

public class HomeFragment extends RxBaseMVPSRRListFragment<HomeListPresenter, HomeListContract
        .IHomeView, Comic> implements HomeListContract.IHomeView {

    @Override
    protected HomeListPresenter createPresenter() {
        return new HomeListPresenter(getActivity());
    }

    @Override
    protected void loadRemoteData() {
        mPresenter.getHomeList();
    }

    @Override
    protected void initPageNumb() {

    }

    @Override
    protected BaseQuickAdapter<Comic, BaseViewHolder> getListAdapter() {
        return new BaseMultiItemQuickAdapter(mList) {
            @Override
            protected void convert(BaseViewHolder helper, Object item) {

            }
        };
    }
}
