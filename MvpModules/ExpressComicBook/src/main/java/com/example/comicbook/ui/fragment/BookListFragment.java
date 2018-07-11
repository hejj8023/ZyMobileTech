package com.example.comicbook.ui.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.example.comicbook.R;
import com.example.comicbook.mvp.contract.BookListContract;
import com.example.comicbook.mvp.presenter.BookListPresenter;
import com.example.comicbook.ui.fragment.books.CollectListFragment;
import com.example.comicbook.ui.fragment.books.DownloadListFragment;
import com.example.comicbook.ui.fragment.books.HistoryListFragment;
import com.zysdk.vulture.clib.sample.adapter.SampleFragmentPagerAdapter;
import com.zysdk.vulture.clib.sample.rx.RxBaseSampleFragment;
import com.zysdk.vulture.clib.utils.CommonUtils;
import com.zysdk.vulture.clib.utils.ResourceUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class BookListFragment extends RxBaseSampleFragment<BookListPresenter, BookListContract
        .IBookView> implements BookListContract.IBookView {

    @BindView(R.id.tablayout_books)
    TabLayout tableLayout;

    @BindView(R.id.vp_books)
    ViewPager viewPager;

    private String[] titles = {
            ResourceUtils.getStr(R.string.collect),
            ResourceUtils.getStr(R.string.history),
            ResourceUtils.getStr(R.string.download)
    };

    private List<Fragment> fragmentList = new ArrayList<>();

    @Override
    protected BookListPresenter createPresenter() {
        return new BookListPresenter();
    }

    @Override
    public int getContentId() {
        return R.layout.fragment_book_list;
    }

    @Override
    public void initView() {
        fragmentList.add(new CollectListFragment());
        fragmentList.add(new HistoryListFragment());
        fragmentList.add(new DownloadListFragment());
        viewPager.setAdapter(new SampleFragmentPagerAdapter(getChildFragmentManager(),
                fragmentList, titles));
        tableLayout.setupWithViewPager(viewPager);

        CommonUtils.setUpIndicatorWidth(tableLayout, 50, 50);
    }
}
