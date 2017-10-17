package com.wj.study.modules.transition.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.wj.base.BaseFragment;
import com.wj.study.R;

/**
 * Author：王江 on 2017/2/27 18:13
 * Description:
 */

public class TransitionNameFragment extends BaseFragment {
    @Override
    public int getLayoutId() {
        return R.layout.transition_name_fragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        Button button = (Button) view.findViewById(R.id.transition_name_button);
        LinearLayout layout = (LinearLayout) view.findViewById(R.id.transition_name_layout);
    }



}
