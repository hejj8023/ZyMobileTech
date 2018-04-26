package com.example.wav.ui.activity;

import android.os.Process;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;

import com.blankj.utilcode.util.ToastUtils;
import com.example.wav.R;
import com.example.wav.base.BaseAdvActivity;
import com.example.wav.mvp.contract.MainContract;
import com.example.wav.mvp.presenter.MainPresenter;
import com.zhiyangstudio.commonlib.utils.IntentUtils;

public class MainActivity extends BaseAdvActivity<MainPresenter, MainContract.IMainView> implements MainContract.IMainView {

    private long mExitTime;

    @Override
    protected boolean initToolBar() {
        setTitle("玩~");
        return true;
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        mPresenter.log("initView");
    }

    @Override
    public void initData() {

    }

    @Override
    public void refreshUi() {

    }

    @Override
    public void release() {

    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                ToastUtils.showShort("筛选");
                IntentUtils.forward(DeviceListActivity.class);
                break;
        }
        return true;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if (System.currentTimeMillis() - mExitTime < 2000) {
                finish();
                Process.killProcess(Process.myPid());
            } else {
                mExitTime = System.currentTimeMillis();
                ToastUtils.showShort("请再按一次退出程序...");
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
