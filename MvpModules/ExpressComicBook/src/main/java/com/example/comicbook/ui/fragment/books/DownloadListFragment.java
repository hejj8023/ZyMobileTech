package com.example.comicbook.ui.fragment.books;

import com.example.comicbook.R;
import com.example.comicbook.mvp.contract.DownloadListContract;
import com.example.comicbook.mvp.presenter.DownloadListPresenter;
import com.zysdk.vulture.clib.sample.fragment.BaseSampleFragment;

public class DownloadListFragment extends BaseSampleFragment<DownloadListPresenter,
        DownloadListContract.IDownloadListView> implements DownloadListContract
        .IDownloadListView {
    @Override
    protected DownloadListPresenter createPresenter() {
        return new DownloadListPresenter();
    }

    @Override
    public int getContentId() {
        return R.layout.fragment_download_list;
    }
}
