package com.wj.study.modules.other.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wj.base.BaseFragment;
import com.wj.study.R;
import com.wj.study.modules.other.OtherActivity;

/**
 * Created by wangjiang on 17/10/17.
 */

public class OtherListFragment extends BaseFragment {
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        final ArrayMap<String, String> map = new ArrayMap<>();
        map.put("Shadow", FragmentFactory.SHADOW);
        map.put("ConstraintLayout", FragmentFactory.CONSTRAINT);
        map.put("Test", FragmentFactory.TEST);
        OtherListAdapter adapter = new OtherListAdapter(map.keySet().toArray(new String[map.size()]));
        adapter.setOnItemClickListener(new OtherListAdapter.OnItemClickListener<String>() {
            @Override
            public void onItemClick(View view, int position, String data) {
                ((OtherActivity) getActivity()).replace(FragmentFactory.getFragment(map.get(data)), data);
            }
        });
        recyclerView.setAdapter(adapter);
    }

    @Override
    public int getLayoutId() {
        return R.layout.recycler_view;
    }

    private static class OtherListAdapter extends RecyclerView.Adapter {

        private String[] mTitles;

        OtherListAdapter(String[] titles) {
            mTitles = titles;
        }

        private OnItemClickListener<String> mOnItemClickListener;

        public void setOnItemClickListener(OnItemClickListener<String> onItemClickListener) {
            mOnItemClickListener = onItemClickListener;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.default_item, parent, false);
            return new ViewCache(view);
        }

        @Override
        public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
            ViewCache viewCache = (ViewCache) holder;
            viewCache.title.setText(mTitles[position]);
            viewCache.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnItemClickListener != null)
                        mOnItemClickListener.onItemClick(v, holder.getLayoutPosition(), mTitles[position]);
                }
            });
        }

        @Override
        public int getItemCount() {
            return mTitles.length;
        }

        public interface OnItemClickListener<T> {

            void onItemClick(View view, int position, T data);
        }
    }

    private static class ViewCache extends RecyclerView.ViewHolder {

        TextView title;

        ViewCache(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.default_item_title);
        }
    }
}
