package com.wj.study.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wj.study.R;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter {

    private List<String> mItems = null;

    public ItemAdapter(List<String> datas) {
        mItems = datas;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.default_item, null);
        view.setLayoutParams(new RecyclerView.LayoutParams(-1, parent.getContext().getResources().getDimensionPixelSize(R.dimen.size_45dp)));
        return new ViewCache(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((ViewCache) holder).title.setText(mItems.get(position));
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public static class ViewCache extends RecyclerView.ViewHolder {

        TextView title;

        public ViewCache(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.default_item_title);
        }
    }
}

