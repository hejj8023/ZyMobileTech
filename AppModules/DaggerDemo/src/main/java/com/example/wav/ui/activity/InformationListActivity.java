package com.example.wav.ui.activity;

import android.graphics.Color;

import com.example.wav.R;
import com.example.wav.bean.ADEnity;
import com.example.wav.bean.InformationBean;
import com.example.wav.mvp.contract.InformationContract;
import com.example.wav.mvp.presenter.InformationListPresenter;
import com.example.wav.widget.ADTextView;
import com.zhiyangstudio.commonlib.corel.BaseToolbarSupportActivity;

import java.util.List;

import butterknife.BindView;

/**
 * Created by example on 2018/5/14.
 */

public class InformationListActivity extends BaseToolbarSupportActivity implements
        InformationContract.IInformationView {

    @BindView(R.id.textview)
    ADTextView mADTextView;
    private List<ADEnity> mList;
    private InformationListPresenter mListPresenter;

    @Override
    protected boolean initToolBar() {
        setTitle("资讯列表");
        return true;
    }

    @Override
    protected int getToolbarBgColor() {
        return R.color._0091ea;
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_information_list;
    }

    @Override
    public void initView() {
        mADTextView.setSpeed(3);
        mADTextView.setInterval(1500);
        mADTextView.setFrontColor(Color.WHITE);
        mADTextView.setBackColor(Color.RED);
        mADTextView.setmTexts(mList);
    }

    @Override
    public void initData() {
        mListPresenter = new InformationListPresenter();
        mListPresenter.attachView(this);
        mListPresenter.getInformationList();
//        mList = new ArrayList<>();
//        mList.add(new ADEnity("推荐", "国货PK美国货,结果让人震惊,国货PK美国货,结果让人震惊国货PK美国货,结果让人震惊", "连接1"));
//        mList.add(new ADEnity("推荐", "这次XiPhone,可能让你迷路,可能让你迷路", "连接2"));
//        mList.add(new ADEnity("HOT", "为什么吉普,奥巴马都爱钓鱼", "连接3"));
//        mList.add(new ADEnity("HOT", "虽然我字难看,但我钢笔好看啊,虽然我字难看,但我钢笔好看啊,虽然我字难看,但我钢笔好看啊", "连接4"));
    }

    @Override
    public void showLoading(String msg) {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showFail(String msg) {

    }

    @Override
    public void showError() {

    }

    @Override
    public void showEmpty() {

    }

    @Override
    public void steDataCount(int total) {

    }

    @Override
    public int getPage() {
        return 0;
    }

    @Override
    public void setData(List<InformationBean> data) {
        if (data != null && data.size() > 0) {

        }
    }
}
