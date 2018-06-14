package com.example.idb.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.blankj.utilcode.util.ToastUtils;
import com.example.idb.BR;
import com.example.idb.R;
import com.example.idb.User;
import com.example.idb.viewholder.ListSampleViewHolder;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ListSampleAdapter extends RecyclerView.Adapter<ListSampleViewHolder> {
    // 已离职
    private static final int STATE_FIRE_ON = 0;

    // 未离职
    private static final int STATE_FIRE_OFF = 1;

    private final Context mContext;
    private LayoutInflater layoutInflater;
    private List<User> userList = new ArrayList<>();

    private ViewDataBinding dataBinding;

    public class ListItemClickPresenter {
        public void onMenuItemClick(User user) {
            ToastUtils.showShort("点击了:" + user.getFirstName());
        }
    }

    public ListSampleAdapter(Context context) {
        this.mContext = context;
        layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getItemViewType(int position) {
        User user = userList.get(position);
        if (user.bIsFired.get()) {
            return STATE_FIRE_ON;
        } else {
            return STATE_FIRE_OFF;
        }
    }

    @NonNull
    @Override
    public ListSampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ListSampleViewHolder sampleViewHolder = null;
        switch (viewType) {
            case STATE_FIRE_ON:
                dataBinding = DataBindingUtil.inflate(layoutInflater, R.layout.layout_item_fire_on, parent, false);
                break;
            case STATE_FIRE_OFF:
                dataBinding = DataBindingUtil.inflate(layoutInflater, R.layout.layout_item_fire_off, parent, false);
                break;
        }
        sampleViewHolder = new ListSampleViewHolder(dataBinding);
        return sampleViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ListSampleViewHolder holder, int position) {
        User user = userList.get(position);
        // TODO: 2018/6/14 动态绑定
        holder.getBinding().setVariable(BR.useritem, user);
        holder.getBinding().setVariable(BR.clickpresenter, new ListItemClickPresenter());
        // 立即刷新
        holder.getBinding().executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public void addAll(List<User> list) {
        userList.addAll(list);
        notifyItemRangeChanged(0, userList.size());
    }

    Random random = new Random(System.currentTimeMillis());

    public void add(User user) {
        // TODO: 2018/6/14 要避免越界
        int position = random.nextInt(userList.size() + 1);
        userList.add(position, user);
        // 添加了数据
        notifyItemInserted(position);
    }

    public void remove() {
        // 从第一个开始移除
        if (userList.size() == 0)
            return;

        int position = random.nextInt(userList.size());
        userList.remove(position);
        notifyItemRemoved(position);
    }
}
