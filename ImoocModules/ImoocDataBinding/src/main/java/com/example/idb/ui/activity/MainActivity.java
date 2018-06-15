package com.example.idb.ui.activity;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.idb.R;
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

        public void onTwowayClick(View view) {
            tCls = TwowayActivity.class;
            IntentUtils.forward(tCls);
        }

        public void onLabmdaClick(View view) {
            tCls = LambdaActivity.class;
            IntentUtils.forward(tCls);
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        activityMainBinding.setMainpresnter(new MainPresenter());

    }

}
