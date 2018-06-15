package com.example.idb.ui.activity;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.blankj.utilcode.util.ToastUtils;
import com.example.idb.R;
import com.example.idb.bean.User;
import com.example.idb.databinding.ActivityLambdaBinding;

public class LambdaActivity extends AppCompatActivity {

    ActivityLambdaBinding lambdaBinding;

    public class ClickPresenter {
        public void onClick(User user) {
            ToastUtils.showShort(user.getFirstName());
        }

        public void onLongClick(Context context, User user) {
            ToastUtils.showShort(user.getLastName());
        }

        public void onFourcChange() {
            ToastUtils.showShort("焦点发生变化...");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lambdaBinding = DataBindingUtil.setContentView(this, R.layout.activity_lambda);
        lambdaBinding.setUserdata(new User("test", "hello-lambda"));
    }
}
