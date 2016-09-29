package com.wj.study.modules.diffuitl;

import android.support.v7.util.DiffUtil;

import com.wj.study.domain.User;

import java.util.List;

/**
 * Author：王江 on 2016/9/29 11:25
 * Description:
 */

public class UserDiffCallback extends DiffUtil.Callback {
    private List<User> mOldUsers;
    private List<User> mNewUsers;

    public UserDiffCallback(List<User> oldUsers, List<User> newUsers) {
        mOldUsers = oldUsers;
        mNewUsers = newUsers;
    }

    @Override
    public int getOldListSize() {
        return mOldUsers.size();
    }

    @Override
    public int getNewListSize() {
        return mNewUsers.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return mOldUsers.get(oldItemPosition).getId() == (mNewUsers.get(newItemPosition)).getId();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return mOldUsers.get(oldItemPosition).getName().equals(mOldUsers.get(newItemPosition).getName());
    }
}
