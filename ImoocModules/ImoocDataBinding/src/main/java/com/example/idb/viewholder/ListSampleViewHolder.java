package com.example.idb.viewholder;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;

public class ListSampleViewHolder<T extends ViewDataBinding> extends RecyclerView.ViewHolder {
    private final T mBinding;

    public ListSampleViewHolder(T binding) {
        super(binding.getRoot());
        this.mBinding = binding;
    }

    public T getBinding() {
        return mBinding;
    }
}
