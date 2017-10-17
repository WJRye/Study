package com.wj.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Author：王江 on 2017/2/22 16:51
 * Description:
 */

public abstract class BaseFragment extends Fragment {

    public final static String TAG = BaseFragment.class.getSimpleName();

    public BaseFragment() {
    }

    @Nullable
    @Override
    public final View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getLayoutId(), container, false);
    }

    public abstract int getLayoutId();

}
