package com.example.daw.ui.activity;

import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.example.daw.R;
import com.example.daw.ui.fragment.HomeFragment;
import com.example.daw.ui.fragment.KnowledgeFragment;
import com.example.daw.ui.fragment.NavigationFragment;
import com.example.daw.ui.fragment.ProjectFragment;
import com.example.daw.utils.BottomNavigationViewHelper;
import com.gyf.barlibrary.ImmersionBar;
import com.zhiyangstudio.commonlib.corel.BaseFragment;
import com.zhiyangstudio.commonlib.corel.BaseToolbarSupportActivity;
import com.zhiyangstudio.commonlib.utils.EmptyUtils;

import java.util.ArrayList;

import butterknife.BindView;

public class MainActivity extends BaseToolbarSupportActivity {

    @BindView(R.id.bottom_menu)
    BottomNavigationView mNavigationView;

    @BindView(R.id.common_top_title_tv)
    TextView tvTitle;

    @BindView(R.id.common_toolbar)
    Toolbar mToolbar;

    private ImmersionBar mImmersionBar;
    private ArrayList<BaseFragment> mFragments;

    @Override
    protected boolean initToolBar() {
        return false;
    }

    @Override
    protected int getToolbarBgColor() {
        return R.color._0091ea;
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        setSupportActionBar(mToolbar);
        setTitle("");
        setMainTitle("主界面");

        // TODO: 2018/5/4 需要设置这个要不然其它条目不显示
        BottomNavigationViewHelper.disableShiftMode(mNavigationView);
    }

    @Override
    public void addListener() {
        mNavigationView.setOnNavigationItemSelectedListener(item -> {
            int index = -1;
            String titleStr = "";
            switch (item.getItemId()) {
                case R.id.tab_main_pager:
                    index = 0;
                    titleStr = "主界面";
                    break;
                case R.id.tab_knowledge_hierarchy:
                    index = 1;
                    titleStr = "知识体系";
                    break;
                case R.id.tab_navigation:
                    index = 2;
                    titleStr = "导航";
                    break;
                case R.id.tab_project:
                    index = 3;
                    titleStr = "项目";
                    break;
            }
            if (index != -1) {
                showFragment(mFragments.get(index));
                setMainTitle(titleStr);
                return true;
            }
            return false;
        });
    }

    @Override
    public void initData() {
        mFragments = new ArrayList<>();
        mFragments.add(new HomeFragment());
        mFragments.add(new KnowledgeFragment());
        mFragments.add(new NavigationFragment());
        mFragments.add(new ProjectFragment());
        showFragment(mFragments.get(0));
    }

    private void showFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fl_content_container, fragment)
                .commitAllowingStateLoss();
    }

    public void setMainTitle(String str) {
        if (EmptyUtils.isEmpty(str))
            return;
        tvTitle.setText(str);
    }

    @Override
    public void beforeSetContentView() {
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.statusBarColor(R.color._0091ea);
        mImmersionBar.init();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        //必须调用该方法，防止内存泄漏
        if (mImmersionBar != null) {
            mImmersionBar.destroy();
        }
    }
}
