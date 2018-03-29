package com.example.rcv.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.rcv.R;
import com.example.utils.UiUtils;
import com.lsjwzh.widget.recyclerviewpager.RecyclerViewPager;

import java.util.List;

/**
 * Created by example on 2018/3/29.
 */

public class LayoutAdapter extends RecyclerView.Adapter<LayoutAdapter.SimpleViewHolder> {
    private List<String> mList;
    private RecyclerViewPager mView;

    public LayoutAdapter(List<String> list, RecyclerViewPager viewPager) {
        this.mList = list;
        this.mView = viewPager;
    }

    @Override
    public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SimpleViewHolder(UiUtils.inflateView(R.layout.item, parent));
    }

    @Override
    public void onBindViewHolder(SimpleViewHolder holder, int position) {
        String str = mList.get(position);
        holder.textView.setText(str);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class SimpleViewHolder extends RecyclerView.ViewHolder {

        private final TextView textView;

        public SimpleViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.title);
        }
    }
}
