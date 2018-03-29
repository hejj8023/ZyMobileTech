package com.example.rcv.ui.activity;

import com.example.common.corel.BaseActivity;
import com.example.rcv.R;
import com.example.utils.UiUtils;

import java.util.Arrays;
import java.util.List;

/**
 * Created by example on 2018/3/29.
 */

public abstract class BaseRecyclerViewActivity extends BaseActivity {

    protected List<String> dataList;

    @Override
    protected void initData() {
        String[] strs = UiUtils.getStrArrs(mContext, R.array.baic_data_list);
        dataList = Arrays.asList(strs);
    }

    @Override
    protected PermissionListener getPermissonCallBack() {
        return null;
    }

}
