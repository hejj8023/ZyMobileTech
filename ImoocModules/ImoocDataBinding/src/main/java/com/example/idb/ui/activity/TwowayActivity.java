package com.example.idb.ui.activity;

import android.databinding.DataBindingUtil;
import android.databinding.Observable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.blankj.utilcode.util.ToastUtils;
import com.example.idb.bean.FormModel;
import com.example.idb.R;
import com.example.idb.databinding.ActivityTwowayBinding;

public class TwowayActivity extends AppCompatActivity {

    private ActivityTwowayBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_twoway);
        binding.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                ToastUtils.showShort(String.valueOf(propertyId));
            }
        });
        binding.setModel(new FormModel("test123", "123456"));
    }
}
