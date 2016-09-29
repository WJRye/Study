package com.wj.study.adapter;

import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wj.study.R;
import com.wj.study.domain.User;
import com.wj.study.modules.diffuitl.UserDiffCallback;

import java.util.List;

/**
 * Author：王江 on 2016/9/28 19:44
 * Description:
 */

public final class DiffUtilAdapter extends RecyclerView.Adapter {

    private List<User> mUsers;

    public DiffUtilAdapter(List<User> users) {
        mUsers = users;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.default_item, parent, false);
        return new ViewCache(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewCache viewCache = (ViewCache) holder;
        User user = mUsers.get(position);
        viewCache.titleView.setText(user.getName());
    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }

    public static class ViewCache extends RecyclerView.ViewHolder {

        TextView titleView;

        public ViewCache(View itemView) {
            super(itemView);
            titleView = (TextView) itemView.findViewById(R.id.default_item_title);
        }
    }

    public final void swapData(List<User> newUsers) {
        UserDiffCallback diffCallback = new UserDiffCallback(mUsers, newUsers);
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);
        mUsers.clear();
        mUsers.addAll(newUsers);
        diffResult.dispatchUpdatesTo(this);
    }
}
