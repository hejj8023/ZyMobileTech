package com.example.bailan.ui.fragment;

import android.os.Bundle;
import android.view.View;

import com.blankj.utilcode.util.ToastUtils;
import com.example.bailan.R;
import com.example.bailan.bean.MenuItemInfo;
import com.example.bailan.widget.GridMenuRecyclerView;
import com.zhiyangstudio.commonlib.utils.UiUtils;
import com.zhiyangstudio.commonlib.widget.LooperTextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by example on 2018/5/25.
 */

public class MimeFragment extends com.zhiyangstudio.commonlib.corel.BaseFragment {

    @BindView(R.id.rv_menu)
    GridMenuRecyclerView mRecyclerView;
    @BindView(R.id.looperview)
    LooperTextView mLooperTextView;
    private List<MenuItemInfo> mItemInfos;

    @Override
    public int getContentId() {
        return R.layout.fragment_account_center;
    }

    @Override
    public void initView() {
        mRecyclerView.setSpanCount(4);
    }

    @Override
    public void addListener() {
        mRecyclerView.setOnItemClickListener((pos, item) -> {
            ToastUtils.showShort("点击了:" + item.getName());
        });
    }

    @Override
    public void initData() {
        String[] names = UiUtils.getStrArrs(R.array.arrs_menu_name_mime);
        int[] icons = UiUtils.getIntArrs(R.array.arrs_menu_icon_mime);

        mItemInfos = new ArrayList<>();
        MenuItemInfo menuItemInfo = null;
        for (int i = 0; i < names.length; i++) {
            menuItemInfo = new MenuItemInfo();
            menuItemInfo.setName(names[i]);
            int imgResId = 0;
            if (i == 0) {
                imgResId = R.drawable.icon_market_lucky_draw;
            } else if (i == 1) {
                imgResId = R.drawable.ic_mine_package_normal;
            } else if (i == 2) {
                imgResId = R.drawable.icon_market_comment;
            } else {
                imgResId = R.drawable.icon_market_message;
            }
            menuItemInfo.setIconId(imgResId);
            mItemInfos.add(menuItemInfo);
        }
        mRecyclerView.setData(mItemInfos);

        List<String> adList2 = new ArrayList<>();
        adList2.add(" android 自定义View开发实战(五) TextView滚动显示");
        adList2.add("android EditText中的几大坑");
        adList2.add("ListView详解之二----XListView");
        adList2.add("Android项目Tab类型主界面大总结 Fragment+TabPageIndicator+ViewPager");
        adList2.add("本文主要是实现一个自定义的圆环，两种颜色交替进行，非百分之百原创，不喜勿喷。下面直接上干货。先上效果图： 1.   定义View的xml属性 " +
                "自定义该View的属性，首先在res/values/下建立一个attrs.xml， 在里面定义我们的属性和声明我们的整个样式 ...");
        adList2.add("ListView之二 XListView");
        adList2.add("android 自定义View开发实战(二) CustomCircleView");
        mLooperTextView.setDefaultTextColor(UiUtils.getColor(R.color.white));
        mLooperTextView.setTipList(adList2);
    }

    @Override
    public void refreshUi() {

    }

    @Override
    public void release() {

    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    protected void initArguments(Bundle bundle) {

    }

    @OnClick({R.id.mw_notify_test})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.mw_notify_test:
                break;
        }
    }

}
