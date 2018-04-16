package com.example.wanandroid.ui.activity;

import android.content.Intent;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.blankj.utilcode.util.ToastUtils;
import com.example.wanandroid.Const;
import com.example.wanandroid.R;
import com.example.wanandroid.base.BaseWanAndroidActivity;
import com.just.agentweb.AgentWeb;
import com.zhiyangstudio.commonlib.mvp.presenter.BasePresenter;
import com.zhiyangstudio.commonlib.utils.CommonUtils;

import butterknife.BindView;

/**
 * Created by example on 2018/4/12.
 * agent webview使用:https://github.com/Justson/AgentWeb
 */

public class WebViewActivity extends BaseWanAndroidActivity {

    @BindView(R.id.webview_container)
    FrameLayout frameLayout;

    private String url;
    private AgentWeb agentWeb;
    private String mTitle;

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        Intent intent = getIntent();
        mTitle = intent.getStringExtra(Const.BUNDLE_KEY.HOME_LIST_ITEM_TITLE);
        toolbar.setTitle(mTitle);
        url = intent.getStringExtra(Const.BUNDLE_KEY.HOME_LIST_ITEM_URL);

        agentWeb = AgentWeb.with(this)
                .setAgentWebParent(frameLayout, new FrameLayout.LayoutParams(-1, -1))
                .useDefaultIndicator(R.color.black)
                .createAgentWeb()
                .ready().go(url);
    }

    @Override
    public void refreshUi() {
    }

    @Override
    public void release() {

    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected boolean initToolBar() {
        return true;
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_webview;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        agentWeb.getWebLifeCycle().onDestroy();
    }

    @Override
    protected PermissionListener getPermissonCallBack() {
        return null;
    }

    @Override
    protected void onPause() {
        agentWeb.getWebLifeCycle().onPause();
        super.onPause();
    }

    @Override
    protected void onResume() {
        agentWeb.getWebLifeCycle().onResume();
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.webview_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.aciton_web_fav:
                ToastUtils.showShort("收藏");
                break;
            case R.id.aciton_web_share:
                CommonUtils.shareText("玩Android分享(" + mTitle + "):" + url);
                ToastUtils.showShort("分享");
                break;
            case R.id.aciton_web_open:
                ToastUtils.showShort("使用浏览器打开");
                CommonUtils.openByWebBroswer(url);
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {
        CommonUtils.makeHeightMenu(menu);
        return super.onMenuOpened(featureId, menu);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // 将事件将给AgentWeb做处理
        if (agentWeb.handleKeyEvent(keyCode, event)) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
