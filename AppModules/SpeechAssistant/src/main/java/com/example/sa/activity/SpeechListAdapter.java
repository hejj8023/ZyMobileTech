package com.example.sa.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sa.R;

import java.util.List;
import java.util.Map;

/**
 * Created by example on 2018/3/2.
 */
public class SpeechListAdapter extends RecyclerView.Adapter<SpeechListAdapter.ViewHolder> {

    private Context mContext;
    private LayoutInflater mLayoutInfalater;
    private List<Map<String, Object>> mList;
    private OnListItemClickListener mListener;

    public SpeechListAdapter(Context context, List<Map<String, Object>> list) {
        this.mContext = context;
        this.mLayoutInfalater = LayoutInflater.from(mContext);
        this.mList = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInfalater.inflate(R.layout.layout_item_list_sppech, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Map<String, Object> map = mList.get(position);
        if (map != null) {
            String title = (String) map.get("title");
            holder.textView.setText(title);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = (Intent) map.get("intent");
                if (mListener != null) {
                    mListener.onItemClick(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void setOnItemClick(OnListItemClickListener listener) {
        this.mListener = listener;
    }

    public interface OnListItemClickListener {
        void onItemClick(Intent intent);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tv_title);
        }
    }
}
