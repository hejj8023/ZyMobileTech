package com.example.rcv.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.rcv.R;
import com.example.utils.UiUtils;

import java.util.List;

/**
 * Created by example on 2018/3/29.
 */

public class BasicListAdapter extends RecyclerView.Adapter<BasicListAdapter.ViewHolder> {
    private final List<String> mList;

    public BasicListAdapter(List<String> list) {
        this.mList = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(UiUtils.inflateView(R.layout.layout_item_list_basic, parent));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.textView.setText(mList.get(position));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textview);
        }
    }
}
