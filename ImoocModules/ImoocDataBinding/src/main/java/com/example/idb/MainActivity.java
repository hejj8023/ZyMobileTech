package com.example.idb;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.android.databinding.library.baseAdapters.BR;
import com.blankj.utilcode.util.ToastUtils;
import com.example.idb.databinding.ActivityDemoBinding;


public class MainActivity extends AppCompatActivity {
    ActivityDemoBinding demoBinding = null;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        demoBinding = DataBindingUtil.setContentView(this, R.layout.activity_demo);

        user = new User("baby", "tiaopi");
        // 第一种方式
        // TODO: 2018/6/13 data binding -> executeBindings 方法中有做空指针避免，即使是传递null也不会造成
        // TODO: 2018/6/13 应用的crash
        demoBinding.setUserData(user);
        //第二种方式
        // demoBinding.setVariable(BR.userData, user);

        MethodCiteBindPresenter methodCiteBindPresenter = new MethodCiteBindPresenter();
//        demoBinding.setVariable(BR.dPresenter, methodCiteBindPresenter);

        demoBinding.setDPresenter(methodCiteBindPresenter);

        // 只有手动调用viewstub的inflate方法之后，才能获取到view的内容
        demoBinding.vsDemo.getViewStub().inflate();
    }

    //方法引用绑定
    public class MethodCiteBindPresenter {

        /**
         * 方法必须和原view的签名相同，方法必须是公开方法，否则会报错
         */
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            // TODO: 2018/6/13 更新textview数据
            user.setFirstName(s.toString());
            demoBinding.setUserData(user);
        }

        public void onClick(View v) {
            ToastUtils.showShort("小样,你点了我...");
        }

        // 监听器绑定
        public void onClickListenerBinding(User user) {
            ToastUtils.showShort("小样,你点了 " + user.getLastName() + " ...");
        }
    }
}
