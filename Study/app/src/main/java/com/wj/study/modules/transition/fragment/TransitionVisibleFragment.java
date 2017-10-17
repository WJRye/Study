package com.wj.study.modules.transition.fragment;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.transition.TransitionManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.wj.base.BaseFragment;
import com.wj.study.R;

/**
 * Author：王江 on 2017/2/22 17:08
 * Description:
 */

public class TransitionVisibleFragment extends BaseFragment {

    @Override
    public int getLayoutId() {
        return R.layout.transition_public;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        final ViewGroup transitionsContainer = (ViewGroup) view.findViewById(R.id.transition_container);
        final TextView text = (TextView) transitionsContainer.findViewById(R.id.transition_text);
        Button button = (Button) transitionsContainer.findViewById(R.id.transition_button);
        button.setOnClickListener(new View.OnClickListener() {
            boolean visible;

            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= 19) {
                    TransitionManager.beginDelayedTransition(transitionsContainer);
                    visible = !visible;
                    text.setVisibility(visible ? View.VISIBLE : View.GONE);
                } else {
                    Toast.makeText(v.getContext(), "请在API 19以上运行！", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
