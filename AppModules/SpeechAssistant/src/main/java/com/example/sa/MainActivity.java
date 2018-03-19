package com.example.sa;

import android.view.Menu;
import android.view.MenuItem;

import com.example.common.corel.BaseActivity;
import com.example.sa.activity.TestSpeechActivity;
import com.example.sa.activity.TestTtsActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected int getContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        setTitle("语音助手");
    }

    @Override
    protected void addListener() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected PermissionListener getPermissonCallBack() {
        return null;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_tts:
                forward(TestTtsActivity.class);
                return true;
            case R.id.menu_speech:
                forward(TestSpeechActivity.class);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
