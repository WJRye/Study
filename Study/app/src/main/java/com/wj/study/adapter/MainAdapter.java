package com.wj.study.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wj.study.R;

/**
 * Author：王江 on 2016/7/7 16:47
 * Description:
 */
public class MainAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private String[] mTitles;

    public MainAdapter(Context context) {
        mTitles = context.getResources().getStringArray(R.array.titles);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.default_item, null);
        view.setLayoutParams(new RecyclerView.LayoutParams(-1, parent.getContext().getResources().getDimensionPixelSize(R.dimen.size_45dp)));
        return new ViewCache(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final String[] array = mTitles[holder.getLayoutPosition()].split("，");
        ViewCache viewCache = (ViewCache) holder;
        viewCache.title.setText(array[0]);
        viewCache.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(array[1]);
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mTitles.length;
    }

    public static class ViewCache extends RecyclerView.ViewHolder {

        TextView title;

        public ViewCache(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.default_item_title);
        }
    }
}
