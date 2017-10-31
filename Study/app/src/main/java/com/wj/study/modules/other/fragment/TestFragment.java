package com.wj.study.modules.other.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wj.base.BaseFragment;
import com.wj.study.R;
import com.wj.study.utils.LogUtil;

/**
 * Created by wangjiang on 17/10/28.
 */

public class TestFragment extends BaseFragment {
    private static final String TAG = "TestFragment";

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        final RelativeLayout layout = (RelativeLayout) view;

        view.findViewById(R.id.test_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (layout.isClickable()) {
                    layout.setClickable(false);
                } else {
                    layout.setClickable(true);
                }
            }
        });

        TextView textView = (TextView) view.findViewById(R.id.test_text);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtil.d(TAG, "textView-->onClick");
            }
        });
        textView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                LogUtil.d(TAG, "textView-->onTouch");
                return false;
            }
        });

        view.findViewById(R.id.test_camera).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtil.d(TAG, "imageView-->onClick");

            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtil.d(TAG, "rootView-->onClick");
            }
        });

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_test;
    }
}
