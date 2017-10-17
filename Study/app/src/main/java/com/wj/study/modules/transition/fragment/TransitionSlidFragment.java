package com.wj.study.modules.transition.fragment;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.transition.Slide;
import android.transition.TransitionManager;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.wj.base.BaseFragment;
import com.wj.study.R;

/**
 * Author：王江 on 2017/2/23 15:37
 * Description:
 */

public final class TransitionSlidFragment extends BaseFragment {
    @Override
    public final int getLayoutId() {
        return R.layout.transition_public;
    }

    @Override
    public final void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        final ViewGroup transitionsContainer = (ViewGroup) view.findViewById(R.id.transition_container);
        final TextView text = (TextView) transitionsContainer.findViewById(R.id.transition_text);
        text.setVisibility(View.VISIBLE);
        Button button = (Button) transitionsContainer.findViewById(R.id.transition_button);
        button.setOnClickListener(new View.OnClickListener() {
            boolean visible = true;

            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= 21) {
                    Slide slide = new Slide(Gravity.RIGHT);
                    slide.setDuration(200);
                    slide.setInterpolator(new FastOutSlowInInterpolator());
                    TransitionManager.beginDelayedTransition(transitionsContainer, slide);
                    visible = !visible;
                    text.setVisibility(visible ? View.VISIBLE : View.GONE);
                } else {
                    Toast.makeText(v.getContext(), "请在API 21以上运行！", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
