package com.example.comicbook.ui.fragment;

import android.view.View;

import com.example.comicbook.R;
import com.example.comicbook.mvp.contract.MimeContract;
import com.example.comicbook.mvp.presenter.MimePresenter;
import com.example.comicbook.ui.activity.SampleListActivity;
import com.zysdk.vulture.clib.sample.rx.RxBaseSampleFragment;
import com.zysdk.vulture.clib.utils.IntentUtils;
import com.zysdk.vulture.clib.widget.MenuWidget;

import butterknife.BindView;
import butterknife.OnClick;

public class MimeFragment extends RxBaseSampleFragment<MimePresenter, MimeContract
        .IBookView> implements MimeContract.IBookView {

    @BindView(R.id.menu_dnmode_manage)
    MenuWidget mwDnModeManage;
    @BindView(R.id.menu_cache_manage)
    MenuWidget mwCacheManage;
    @BindView(R.id.menu_feedback)
    MenuWidget mwFeedback;
    @BindView(R.id.menu_about)
    MenuWidget mwAbout;
    @BindView(R.id.menu_update_manage)
    MenuWidget mwUpdate;

    @Override
    protected MimePresenter createPresenter() {
        return new MimePresenter();
    }

    @Override
    public int getContentId() {
        return R.layout.fragment_mime;
    }

    @Override
    public void initView() {
    }

    @OnClick(R.id.menu_update_manage)
    public void onViewClick(View view) {
        IntentUtils.forward(SampleListActivity.class);
    }
}
