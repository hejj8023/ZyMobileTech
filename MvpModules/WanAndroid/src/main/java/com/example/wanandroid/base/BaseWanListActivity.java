package com.example.wanandroid.base;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;

import com.example.wanandroid.Const;
import com.example.wanandroid.R;
import com.example.wanandroid.adapter.ArticleListAdapter;
import com.example.wanandroid.inter.OnArticleListItemClickListener;
import com.example.wanandroid.ui.activity.TreeActivity;
import com.example.wanandroid.utils.CommonInternalUtil;
import com.zhiyangstudio.commonlib.adapter.BaseListAdapter;
import com.zhiyangstudio.commonlib.mvp.BaseAbsListActivity;
import com.zhiyangstudio.commonlib.mvp.inter.IView;
import com.zhiyangstudio.commonlib.mvp.presenter.BasePresenter;
import com.zhiyangstudio.commonlib.utils.IntentUtils;
import com.zhiyangstudio.commonlib.utils.UiUtils;

/**
 * Created by zzg on 2018/4/21.
 */

public abstract class BaseWanListActivity<P extends BasePresenter<V>, V extends IView, T> extends BaseAbsListActivity<P, V, T> implements OnArticleListItemClickListener {

    protected Toolbar toolbar;
    protected LinearLayout containerLayout;

    @Override
    public int getContentId() {
        return R.layout.activity_base_wan_android;
    }

    @Override
    public void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        containerLayout = (LinearLayout) findViewById(R.id.frameLayout);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        toolbar.setTitle(getCurrentTitle());
        // TODO: 2018/4/17 toolbar返回键点击时的操作
        toolbar.setNavigationOnClickListener(v -> {
            finish();
        });

        View listView = UiUtils.inflateView(R.layout.layout_base_recycler_list, containerLayout);
        containerLayout.addView(listView);
        super.initView();
    }

    protected abstract String getCurrentTitle();

    @Override
    protected boolean isCanLoadMore() {
        return true;
    }

    @Override
    protected BaseListAdapter getListAdapter() {
        return new ArticleListAdapter(this, getType());
    }

    protected abstract int getType();

    @Override
    public void onItemClick(String title, String url) {
        CommonInternalUtil.goWebView(title, url);
    }

    @Override
    public void onCollectClick(int pos, int id, int originId) {

    }

    @Override
    public void onCollectClick(int pos, int id) {

    }

    @Override
    public void onTreeClick(int chapterId, String chapterName) {
        Intent intent = new Intent(mContext, TreeActivity.class);
        intent.putExtra(Const.BUNDLE_KEY.ACTION_TYPE, Const.BUNDLE_KEY.ACTION_LIST);
        intent.putExtra(Const.BUNDLE_KEY.CHAPTER_ID, chapterId);
        intent.putExtra(Const.BUNDLE_KEY.CHAPTER_NAME, chapterName);
        IntentUtils.forward(intent);
    }

    @Override
    protected int getStatusbarColor() {
        return R.color._0091ea;
    }

    @Override
    protected boolean hasSupportTransStatusBar() {
        return true;
    }


    @Override
    public void initData() {
        setTitle(getCurrentTitle());
    }

    @Override
    public void refreshUi() {

    }

    @Override
    public void release() {

    }
}
