package com.example.idb.ui.activity;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.idb.R;
import com.example.idb.bean.User;
import com.example.idb.databinding.ActivityExpressionSampleBinding;

import java.util.Random;

public class ExpressionSampleActivity extends AppCompatActivity {

    ActivityExpressionSampleBinding expressionSampleBinding;

    Random random = new Random(System.currentTimeMillis());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expression_sample);

        User user = new User("test-after", "afload");
        user.setAvatorUri("http://a.hiphotos.baidu.com/image/pic/item/0df3d7ca7bcb0a46aa1f61a36763f6246b60af6f.jpg");
        // 随机设置是否可见
        user.setFire(random.nextBoolean());
        expressionSampleBinding = DataBindingUtil.setContentView(this, R.layout.activity_expression_sample);
        expressionSampleBinding.setUserdata(user);
    }
}
