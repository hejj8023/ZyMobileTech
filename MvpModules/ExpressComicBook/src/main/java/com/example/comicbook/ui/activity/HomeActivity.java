package com.example.comicbook.ui.activity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.view.ViewPager;
import android.widget.TabHost;

import com.example.comicbook.R;
import com.example.comicbook.ui.fragment.BookListFragment;
import com.example.comicbook.ui.fragment.HomeFragment;
import com.example.comicbook.ui.fragment.MimeFragment;
import com.example.comicbook.ui.weiget.TabIndicator;
import com.zysdk.vulture.clib.corel.BaseToolbarSupportActivity;
import com.zysdk.vulture.clib.sample.adapter.SampleFragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class HomeActivity extends BaseToolbarSupportActivity {

    private List<Fragment> fragmentList = new ArrayList<>();
    private FragmentTabHost tabHost;

    @BindView(R.id.viewpager)
    ViewPager viewPager;

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    public void initView() {
        tabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        tabHost.setup(mContext, getSupportFragmentManager(), R.id.viewpager);
    }

    @Override
    protected boolean initToolBar() {
        return false;
    }

    @Override
    public void initData() {
        fragmentList.add(new HomeFragment());
        fragmentList.add(new BookListFragment());
        fragmentList.add(new MimeFragment());

//        tabHost.addTab(tabHost.newTabSpec("首页").setIndicator("home_list"), fragmentList.get(0)
//                .getClass(), null);
//        tabHost.addTab(tabHost.newTabSpec("书架").setIndicator("book_list"), fragmentList.get(1)
//                .getClass(), null);
//        tabHost.addTab(tabHost.newTabSpec("我的").setIndicator("mime"), fragmentList.get(2)
//                .getClass(), null);

        TabIndicator tabIndicator = new TabIndicator(mContext);
        tabIndicator.setIcon(R.drawable.selector_home_menu);
        tabIndicator.setIconSize(50);
        tabHost.addTab(tabHost.newTabSpec("首页").setIndicator(tabIndicator), fragmentList.get(0)
                .getClass(), null);
        tabIndicator = new TabIndicator(mContext);
        tabIndicator.setIcon(R.drawable.selector_book_menu);
        tabIndicator.setIconSize(50);
        tabHost.addTab(tabHost.newTabSpec("书架").setIndicator(tabIndicator), fragmentList.get(1)
                .getClass(), null);
        tabIndicator = new TabIndicator(mContext);
        tabIndicator.setIcon(R.drawable.selector_mime_menu);
        tabIndicator.setIconSize(50);
        tabHost.addTab(tabHost.newTabSpec("我的").setIndicator(tabIndicator), fragmentList.get(2)
                .getClass(), null);

        tabHost.getTabWidget().setDividerDrawable(new ColorDrawable(Color.TRANSPARENT));

        viewPager.setAdapter(new SampleFragmentPagerAdapter(getSupportFragmentManager(),
                fragmentList));
    }

    @Override
    public void addListener() {
        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                int currentTab = tabHost.getCurrentTab();
                viewPager.setCurrentItem(currentTab, false);
            }
        });
    }

    @Override
    protected int getStatusbarColor() {
        return R.color.white;
    }
}
