package com.example.idb;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.idb.databinding.ActivityExpressionSampleBinding;

public class ExpressionSampleActivity extends AppCompatActivity {

    ActivityExpressionSampleBinding expressionSampleBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expression_sample);

        User user = new User("test-after", "afload");
        user.setAvatorUri("http://a.hiphotos.baidu.com/image/pic/item/0df3d7ca7bcb0a46aa1f61a36763f6246b60af6f.jpg");

        expressionSampleBinding = DataBindingUtil.setContentView(this, R.layout.activity_expression_sample);
        expressionSampleBinding.setUserdata(user);
    }
}
