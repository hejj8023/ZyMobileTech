package com.example.wanandroid.ui.activity;

import android.os.Build;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wanandroid.Const;
import com.example.wanandroid.R;
import com.example.wanandroid.base.BaseWanListActivity;
import com.example.wanandroid.bean.ArticleBean;
import com.example.wanandroid.bean.FriendBean;
import com.example.wanandroid.bean.HotwordBean;
import com.example.wanandroid.mvp.contract.SearchContract;
import com.example.wanandroid.mvp.presenter.SearchPresenter;
import com.zysdk.vulture.clib.CommonConst;
import com.zysdk.vulture.clib.utils.CommonUtils;
import com.zysdk.vulture.clib.utils.EmptyUtils;
import com.zysdk.vulture.clib.utils.LoggerUtils;
import com.zysdk.vulture.clib.utils.UiUtils;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by example on 2018/4/13.
 * 流式布局:https://github.com/hongyangAndroid/FlowLayout
 */

public class SearchActivity extends BaseWanListActivity<SearchPresenter, SearchContract
        .ISearchView, ArticleBean> implements SearchContract.ISearchView {

    private SearchView mSearchView;
    private View mHeaderView;
    private TagFlowLayout mKeywordTagLayout;
    private TagFlowLayout mFriendTagLayout;
    private List<HotwordBean> mHotwordDatas = new ArrayList<>();
    private List<FriendBean> mFriendBeanDatas = new ArrayList<>();
    // 搜索的词
    private String mKeyWord;


    @Override
    protected View initHeaderView() {
        mHeaderView = UiUtils.inflateView(R.layout.layout_search_header, recyclerView);
        mKeywordTagLayout = mHeaderView.findViewById(R.id.keywordTaglayout);
        mFriendTagLayout = mHeaderView.findViewById(R.id.friendTaglayout);
        return mHeaderView;
    }

    @Override
    protected void loadDatas() {
        LoggerUtils.loge(this, "loadDatas");
        if (mSearchView == null) {
            loadTagDatas();
            return;
        }

        loadArticleListDatas();
    }

    /**
     * 加载标签数据
     */
    private void loadTagDatas() {
        LoggerUtils.loge(this, "loadTagDatas");
        setRrefreshEnable(false);
        setCanLoadMore(false);
        mListData.clear();
        recyclerView.addHeaderView(mHeaderView);
        mListAdapter.notifyAllDatas(mListData, recyclerView);
        showContent();
        mPresenter.getHotWord();
        mPresenter.getFriend();
    }

    private void loadArticleListDatas() {
        LoggerUtils.loge(this, "loadArticleListDatas");
        mHotwordDatas.clear();
        mFriendBeanDatas.clear();
        mPresenter.search();
    }

    @Override
    protected SearchPresenter createPresenter() {
        return new SearchPresenter();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu, menu);
        MenuItem menuItem = menu.findItem(R.id.menu_search);
        View actionView = menuItem.getActionView();
        if (actionView != null && actionView instanceof SearchView) {
            mSearchView = (SearchView) actionView;
            mSearchView.setQueryHint(UiUtils.getStr(R.string.search_keyword));
            mSearchView.onActionViewExpanded();

            // 去除搜索框背景
            CommonUtils.deleteSearchPlate(mSearchView);

            SearchView.SearchAutoComplete searchAutoComplete = mSearchView.findViewById(R.id
                    .search_src_text);
            searchAutoComplete.setHintTextColor(UiUtils.getColor(R.color._60ffffff));

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                ImageView closeIv = mSearchView.findViewById(R.id.search_close_btn);
                closeIv.setBackground(UiUtils.getDrawable(R.drawable.ripple_close));
            }

            mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    LoggerUtils.loge(SearchActivity.this, "onQueryTextSubmit");
                    mKeyWord = query;
                    refreshData();
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    LoggerUtils.loge(SearchActivity.this, "onQueryTextChange");
                    if (EmptyUtils.isEmpty(newText)) {
                        mKeyWord = newText;
                        if (mHotwordDatas.size() == 0) {
                            loadTagDatas();
                        }
                    }
                    return false;
                }
            });
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void setData(List<ArticleBean> data) {
        recyclerView.removeHeaderView();
        setRrefreshEnable(true);
        setCanLoadMore(true);
        if (state != CommonConst.PAGE_STATE.STATE_LOAD_MORE) {
            recyclerView.scrollToPosition(0);
        }
        mListData.addAll(data);
    }

    @Override
    public void setHotwordData(List<HotwordBean> data) {
        LoggerUtils.loge(this, "setHotwordData");
        mHotwordDatas.clear();
        mHotwordDatas.addAll(data);
        mKeywordTagLayout.setAdapter(new TagAdapter<HotwordBean>(data) {
            @Override
            public View getView(FlowLayout parent, int position, HotwordBean hotwordBean) {
                TextView textView = (TextView) UiUtils.inflateView(R.layout.item_search_tag, parent);
                textView.setText(hotwordBean.getName());
                CommonUtils.setRandomTextColor(textView);
                return textView;
            }
        });
    }

    @Override
    public void setFriendData(List<FriendBean> data) {
        LoggerUtils.loge(this, "setFriendData");
        mFriendBeanDatas.clear();
        mFriendBeanDatas.addAll(data);
        mFriendTagLayout.setAdapter(new TagAdapter<FriendBean>(data) {
            @Override
            public View getView(FlowLayout parent, int position, FriendBean hotwordBean) {
                TextView textView = (TextView) UiUtils.inflateView(R.layout.item_search_tag, parent);
                textView.setText(hotwordBean.getName());
                CommonUtils.setRandomTextColor(textView);
                return textView;
            }
        });
    }

    @Override
    public String getKeyword() {
        return mKeyWord;
    }

    @Override
    protected String getCurrentTitle() {
        return UiUtils.getStr(R.string.search);
    }

    @Override
    protected int getType() {
        return Const.LIST_TYPE.SEARCH;
    }
}
