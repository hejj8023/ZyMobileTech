package com.example.bailan.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.example.bailan.R;
import com.example.bailan.bean.DataBean;
import com.example.bailan.bean.MenuItemInfo;
import com.example.bailan.widget.AdvertView;
import com.example.bailan.widget.AutoScrollTextView;
import com.example.bailan.widget.GridMenuRecyclerView;
import com.example.bailan.widget.LimitScrollerView;
import com.zhiyangstudio.commonlib.utils.UiUtils;

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
    @BindView(R.id.limitScroll)
    LimitScrollerView mLimitScrollerView;
    @BindView(R.id.activity_main_advertView)
    AdvertView mAdvertView;
    @BindView(R.id.activity_main_scrollTextView)
    AutoScrollTextView scrollTextView;
    private List<MenuItemInfo> mItemInfos;
    private MyLimitScrollAdapter adapter;

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


        //API:1、设置数据适配器
        adapter = new MyLimitScrollAdapter();
        mLimitScrollerView.setDataAdapter(adapter);

        List<DataBean> datas = new ArrayList<>();
        datas.add(new DataBean(R.mipmap.ic_launcher, "1.劲爆促销中，凡在此商场消费满888的顾客，请拿着小票到前台咨询处免费领取美女一枚"));
        datas.add(new DataBean(R.mipmap.ic_launcher, "2.劲爆促销中，凡在此商场消费满888的顾客，请拿着小票到前台咨询处免费领取美女一枚"));
        datas.add(new DataBean(R.mipmap.ic_launcher, "3.劲爆促销中，凡在此商场消费满888的顾客，请拿着小票到前台咨询处免费领取美女一枚"));
        datas.add(new DataBean(R.mipmap.ic_launcher, "4.劲爆促销中，凡在此商场消费满888的顾客，请拿着小票到前台咨询处免费领取美女一枚"));
        datas.add(new DataBean(R.mipmap.ic_launcher, "5.劲爆促销中，凡在此商场消费满888的顾客，请拿着小票到前台咨询处免费领取美女一枚"));
        datas.add(new DataBean(R.mipmap.ic_launcher, "6.劲爆促销中，凡在此商场消费满888的顾客，请拿着小票到前台咨询处免费领取美女一枚"));
        datas.add(new DataBean(R.mipmap.ic_launcher, "7.劲爆促销中，凡在此商场消费满888的顾客，请拿着小票到前台咨询处免费领取美女一枚"));
        datas.add(new DataBean(R.mipmap.ic_launcher, "8.劲爆促销中，凡在此商场消费满888的顾客，请拿着小票到前台咨询处免费领取美女一枚"));
        datas.add(new DataBean(R.mipmap.ic_launcher, "9.劲爆促销中，凡在此商场消费满888的顾客，请拿着小票到前台咨询处免费领取美女一枚"));

        UiUtils.postDelayed(new Runnable() {
            @Override
            public void run() {
                adapter.setDatas(datas);
            }
        }, 1500);


        final List<String> strList = new ArrayList<>();
        strList.add("如果奇迹有颜色，那么一定是红蓝");
        strList.add("人面不知何处去 桃花依旧笑春风,人面不知何处去 桃花依旧笑春风人面不知何处去 桃花依旧笑春风人面不知何处去 桃花依旧笑春风");
        strList.add("道者深方能言之浅");
        mAdvertView.setData(strList);

//        scrollTextView.startScroll();
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
        //API:3、停止滚动
        mLimitScrollerView.cancel();
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

    //TODO 修改适配器绑定数据
    class MyLimitScrollAdapter implements LimitScrollerView.LimitScrollAdapter {

        private List<DataBean> datas;

        public void setDatas(List<DataBean> datas) {
            this.datas = datas;
            //API:2、开始滚动
            mLimitScrollerView.startScroll();
        }

        @Override
        public int getCount() {
            return datas == null ? 0 : datas.size();
        }

        @Override
        public View getView(int index) {
            View itemView = LayoutInflater.from(mContext).inflate(R.layout.limit_scroller_item, null, false);
            ImageView iv_icon = (ImageView) itemView.findViewById(R.id.iv_icon);
            TextView tv_text = (TextView) itemView.findViewById(R.id.tv_text);

            //绑定数据
            DataBean data = datas.get(index);
            itemView.setTag(data);
            iv_icon.setImageResource(data.getIcon());
            tv_text.setText(data.getText());
            return itemView;
        }
    }
}
