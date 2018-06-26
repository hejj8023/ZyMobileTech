package com.example.idb.ui.activity;

import android.databinding.DataBindingUtil;
import android.databinding.OnRebindCallback;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.transition.TransitionManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;

import com.example.idb.R;
import com.example.idb.databinding.ActivityAnimationBinding;


public class AnimationTestActivity extends AppCompatActivity {

    private ActivityAnimationBinding animationBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        animationBinding = DataBindingUtil.setContentView(this, R.layout.activity_animation);
        animationBinding.addOnRebindCallback(new OnRebindCallback() {
            @Override
            public boolean onPreBind(ViewDataBinding binding) {
                ViewGroup view = (ViewGroup) binding.getRoot();
                TransitionManager.beginDelayedTransition(view);
                return true;
            }
        });
        animationBinding.setAPresenter(new Presenter());
    }

    public class Presenter {
        public void onCheckedChanged(View view, boolean isCheck) {
            animationBinding.setShowIamge(isCheck);
        }
    }
}
