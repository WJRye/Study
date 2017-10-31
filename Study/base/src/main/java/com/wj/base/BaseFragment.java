package com.wj.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import example.com.wj.base.R;


/**
 * Author：王江 on 2017/2/22 16:51
 * Description:
 */

public abstract class BaseFragment extends Fragment {

    public final static String TAG = "BaseFragment";

    public BaseFragment() {
    }

    @Nullable
    @Override
    public final View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        int layoutId = getLayoutId();
        int resource = layoutId <= 0 ? R.layout.empty_view : layoutId;
        return inflater.inflate(resource, container, false);
    }

    @Override
    public abstract void onViewCreated(View view, @Nullable Bundle savedInstanceState);

    public abstract int getLayoutId();

}
