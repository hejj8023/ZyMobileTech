package com.example.idb;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.idb.databinding.ActivityMainBinding;
import com.zysdk.vulture.clib.utils.IntentUtils;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding activityMainBinding = null;


    public class MainPresenter {

        Class<? extends Activity> tCls = null;

        public void onSampleMenuClick(View view) {
            tCls = SampleActivity.class;
            IntentUtils.forward(tCls);
        }

        public void onListMenuClick(View view) {
            tCls = ListSampleActivity.class;
            IntentUtils.forward(tCls);
        }

        public void onExpressionClick(View view) {
            tCls = ExpressionSampleActivity.class;
            IntentUtils.forward(tCls);
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        activityMainBinding.setMainpresnter(new MainPresenter());

    }


    //    @OnClick({R.id.btn_sample, R.id.btn_list})
//    public void onViewClick(View view) {
//        Class<? extends Activity> tCls = null;
//
//        switch (view.getId()) {
//            case R.id.btn_sample:
//                tCls = SampleActivity.class;
//                break;
//            case R.id.btn_list:
//                tCls = ListSampleActivity.class;
//                break;
//        }
//
//        if (tCls != null) {
//            IntentUtils.forward(tCls);
//        }
//    }
}
