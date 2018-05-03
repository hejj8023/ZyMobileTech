package com.example.wav.ui.fragment.filter;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;

import com.example.wav.Const;
import com.example.wav.DataManager;
import com.example.wav.R;
import com.example.wav.base.BaseDaggerSupportListFragment;
import com.example.wav.bean.AccountCustomerInfo;
import com.example.wav.mvp.contract.FilterCustomerListContract;
import com.example.wav.mvp.presenter.FilterCustomerListPresenter;
import com.zhiyangstudio.commonlib.CommonConst;
import com.zhiyangstudio.commonlib.adapter.BaseListAdapter;
import com.zhiyangstudio.commonlib.adapter.lgrcommon.QuickViewHolder;

import java.util.List;

/**
 * Created by example on 2018/5/2.
 */

public class FilterCustomerListFragment extends
        BaseDaggerSupportListFragment<FilterCustomerListPresenter, FilterCustomerListContract
                .IFilterCustomerListView, AccountCustomerInfo> implements FilterCustomerListContract
        .IFilterCustomerListView {
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
    public void setData(List<AccountCustomerInfo> data) {
        if (state == CommonConst.PAGE_STATE.STATE_REFRESH) {
            mListData.clear();
            AccountCustomerInfo accountGroupInfo = data.get(0);
            accountGroupInfo.setChecked(true);
            Const.TMP_DATA.FILTER_CUSTOMER_ID = accountGroupInfo.getId() + "";
            DataManager.saveDefaultUserId(accountGroupInfo.getId() + "");
            data.set(0, accountGroupInfo);
            mListData.addAll(data);
        } else {
            mListData.addAll(data);
        }

    }

    @Override
    protected void inject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected void initArguments(Bundle bundle) {
    }

    @Override
    public void loadDatas() {
        mPresenter.getData();
    }

    @Override
    protected boolean isCanLoadMore() {
        return false;
    }

    @Override
    protected BaseListAdapter getListAdapter() {
        return new ListAdapter();
    }

    @Override
    protected View initHeaderView() {
        return null;
    }

    private class ListAdapter extends BaseListAdapter<AccountCustomerInfo> {
        @Override
        protected int getLayoutId(int viewType) {
            return R.layout.layout_item_customer_list;
        }

        @Override
        protected void bindDatas(QuickViewHolder holder, AccountCustomerInfo bean, int itemViewType, int position) {
            holder.setText(R.id.tv_title, bean.getText());
            CheckBox checkBox = holder.getView(R.id.cb_customer);
            if (bean.isChecked()) {
                checkBox.setChecked(true);
            } else {
                checkBox.setChecked(false);
            }
            holder.setOnClickListener(v -> {
                // 取消所有的选择
                for (AccountCustomerInfo datum : mListData) {
                    if (!datum.equals(bean))
                        datum.setChecked(false);
                }
                if (bean.isChecked()) {
                    bean.setChecked(false);
                    Const.TMP_DATA.FILTER_CUSTOMER_ID = "";
                    DataManager.saveDefaultUserId("");
                } else {
                    bean.setChecked(true);
                    Const.TMP_DATA.FILTER_CUSTOMER_ID = bean.getId() + "";
                    DataManager.saveDefaultUserId(bean.getId() + "");
                }
                checkBox.setChecked(bean.isChecked());
                notifyAllDatas(mListData,recyclerView);
            });
        }
    }
}
