package com.example.testapp;

import com.zhiyangstudio.commonlib.corel.BaseApp;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by example on 2018/4/18.
 */

public class TestApp extends BaseApp {
    @Override
    public void onCreate() {
        super.onCreate();
        createTestList();
        createHostList();
    }

    private void createTestList() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add("index " + i);
        }
        Const.DATA.TEST_LIST.addAll(list);
    }

    private void createHostList() {
        List<HotBean> hotBeans = new ArrayList<>();
        Random random = new Random();
        HotBean hotBean = null;
        for (int i = 0; i < 20; i++) {
            hotBean = new HotBean();
            hotBean.setType(random.nextInt(3));
            hotBean.setTitle("title " + (i + 1));
            if (hotBean.getType() != 0) {
                hotBean.setImgUrl("http://www.wanandroid.com/blogimgs/50c115c2-cf6c-4802-aa7b-a4334de444cd.png");
            }
            hotBean.setContentTxt("content " + (random.nextInt(20)));
            hotBeans.add(hotBean);
        }
        Const.DATA.TEST_HOT_BEAN_LIST.addAll(hotBeans);
    }
}
