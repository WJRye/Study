package com.wj.study.modules.transition.adapter;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wj.study.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Author：王江 on 2017/2/27 10:32
 * Description:
 */

public class TransitionExplodeAdapter extends RecyclerView.Adapter {
    private List<Integer> mData = new ArrayList<>();
    private OnItemClickListener mOnItemClickListener;

    public TransitionExplodeAdapter() {
        for (int i = 0; i < 100; i++) {
            mData.add(i);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        TextView textView = new TextView(parent.getContext());
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, parent.getResources().getDimensionPixelSize(R.dimen.textSize_14sp));
        RecyclerView.LayoutParams params = new RecyclerView.LayoutParams(-1, parent.getResources().getDimensionPixelSize(R.dimen.size_90dp));
        int margin = parent.getResources().getDimensionPixelSize(R.dimen.margin_10dp);
        params.setMargins(margin, margin, margin, margin);
        textView.setLayoutParams(params);
        textView.setBackgroundColor(Color.RED);

        return new ViewCache(textView);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        ViewCache viewCache = (ViewCache) holder;
        viewCache.textView.setText(mData.get(position).toString());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null)
                    mOnItemClickListener.onItemClick(v, holder.getLayoutPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    private static class ViewCache extends RecyclerView.ViewHolder {
        private TextView textView;

        public ViewCache(View itemView) {
            super(itemView);
            textView = (TextView) itemView;
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mOnItemClickListener = listener;
    }
}
