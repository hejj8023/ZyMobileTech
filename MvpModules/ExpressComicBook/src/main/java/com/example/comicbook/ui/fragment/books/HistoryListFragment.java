package com.example.comicbook.ui.fragment.books;

import com.example.comicbook.R;
import com.example.comicbook.mvp.contract.HistoryListContract;
import com.example.comicbook.mvp.presenter.HistoryListPresenter;
import com.zysdk.vulture.clib.sample.rx.RxBaseSampleFragment;

public class HistoryListFragment extends RxBaseSampleFragment<HistoryListPresenter,
        HistoryListContract.IHistoryListView> implements HistoryListContract.IHistoryListView {

    @Override
    protected HistoryListPresenter createPresenter() {
        return new HistoryListPresenter();
    }

    @Override
    public int getContentId() {
        return R.layout.fragment_history_list;
    }
}
