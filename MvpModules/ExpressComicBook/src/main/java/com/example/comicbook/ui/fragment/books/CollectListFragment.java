package com.example.comicbook.ui.fragment.books;

import com.example.comicbook.R;
import com.example.comicbook.mvp.contract.CollectListContract;
import com.example.comicbook.mvp.presenter.CollectListPresenter;
import com.zysdk.vulture.clib.sample.rx.RxBaseSampleFragment;

public class CollectListFragment extends RxBaseSampleFragment<CollectListPresenter,
        CollectListContract.ICollectListView> implements CollectListContract.ICollectListView {

    @Override
    protected CollectListPresenter createPresenter() {
        return new CollectListPresenter();
    }

    @Override
    public int getContentId() {
        return R.layout.fragment_collect_list;
    }
}
