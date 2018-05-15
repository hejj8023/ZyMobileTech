package com.example.wav.ui.fragment.filter;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.example.wav.R;
import com.example.wav.manager.PickViewManager;
import com.zhiyangstudio.commonlib.corel.BaseFragment;

import java.util.Date;

import butterknife.OnClick;

/**
 * Created by example on 2018/5/15.
 */

public class FilterDateRangeFragment extends BaseFragment {
    @Override
    public int getContentId() {
        return R.layout.fragment_date_range;
    }

    @Override
    public void initView() {

    }

    @Override
    public void addListener() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void refreshUi() {

    }

    @Override
    public void release() {

    }

    @Override
    protected void initArguments(Bundle bundle) {

    }

    @OnClick({R.id.tv_last_month, R.id.tv_today})
    public void onViewClick(View view) {
        // TODO: 2018/5/15 日期的选择
        switch (view.getId()) {
            case R.id.tv_last_month:
            case R.id.tv_today:
                TimePickerView pvTime = PickViewManager.getTimePickerView(mActivity, new
                        OnTimeSelectListener() {
                            @Override
                            public void onTimeSelect(Date date, View v) {
                                TextView textView = (TextView) v;
                                textView.setText(getTimeYMD(date));
                            }
                        });
                pvTime.show(view);
                break;
        }
    }
}
