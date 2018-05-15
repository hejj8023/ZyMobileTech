package com.example.player.ui.activity;

import android.support.v4.app.Fragment;
import android.view.Menu;
import android.view.MenuItem;

import com.example.player.R;
import com.example.player.ui.fragment.AdvPlayFragment;
import com.example.player.ui.fragment.CustomPlayFragment;
import com.example.player.ui.fragment.ListPlayFragment;
import com.example.player.ui.fragment.NormalPlayFragment;
import com.example.player.ui.fragment.StandardPlayFragment;
import com.example.player.ui.fragment.StandardSamplePlayFragment;
import com.zhiyangstudio.commonlib.corel.BaseToolbarSupportActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by example on 2018/5/14.
 */

public class GSYVideoPlayerActivity extends BaseToolbarSupportActivity {

    private List<Fragment> mFragments;

    @Override
    protected boolean initToolBar() {
        setTitle("GSYVideoPlayer");
        return true;
    }

    @Override
    protected int getToolbarBgColor() {
        return R.color.colorPrimary;
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_gsy_video_player;
    }

    @Override
    public void initData() {
        mFragments = new ArrayList<>();
        Fragment fragment = new StandardPlayFragment();
        mFragments.add(fragment);
        fragment = new StandardSamplePlayFragment();
        mFragments.add(fragment);
        fragment = new NormalPlayFragment();
        mFragments.add(fragment);
        fragment = new ListPlayFragment();
        mFragments.add(fragment);
        fragment = new AdvPlayFragment();
        mFragments.add(fragment);
        fragment = new CustomPlayFragment();
        mFragments.add(fragment);

        showTargetFragment(mFragments.get(0));
    }

    private void showTargetFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_root_play, fragment)
                .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_gsy_video_player, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int index = 0;
        switch (item.getItemId()) {
            case R.id.action_standard:
                index = 0;
                break;
            case R.id.action_standard_sample:
                index = 1;
                break;
            case R.id.action_normal:
                index = 2;
                break;
            case R.id.action_list:
                index = 3;
                break;
            case R.id.action_adv:
                index = 4;
                break;
            case R.id.action_customer:
                index = 5;
                break;
        }
        showTargetFragment(mFragments.get(index));
        return true;
    }

}
