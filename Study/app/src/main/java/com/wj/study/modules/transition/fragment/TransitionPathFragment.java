package com.wj.study.modules.transition.fragment;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.transition.ArcMotion;
import android.transition.ChangeBounds;
import android.transition.TransitionManager;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.wj.base.BaseFragment;
import com.wj.study.R;

/**
 * Author：王江 on 2017/2/27 15:44
 * Description:
 */

public class TransitionPathFragment extends BaseFragment {
    @Override
    public int getLayoutId() {
        return R.layout.transition_path_fragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        final FrameLayout transitionsContainer = (FrameLayout) view.findViewById(R.id.transition_path_container);
        Button button = (Button) transitionsContainer.findViewById(R.id.transition_path_button);
        button.setOnClickListener(new View.OnClickListener() {
            boolean isReturnAnimation = false;

            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= 21) {
                    ChangeBounds changeBounds = new ChangeBounds();
                    ArcMotion arcMotion = new ArcMotion();
                    arcMotion.setMinimumHorizontalAngle(15f);
                    arcMotion.setMinimumVerticalAngle(0f);
                    arcMotion.setMinimumVerticalAngle(90f);
                    changeBounds.setPathMotion(new ArcMotion());
                    changeBounds.setDuration(500);
                    TransitionManager.beginDelayedTransition(transitionsContainer,
                            changeBounds);
                    FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) v.getLayoutParams();
                    params.gravity = isReturnAnimation ? (Gravity.LEFT | Gravity.TOP) :
                            (Gravity.BOTTOM | Gravity.RIGHT);
                    v.setLayoutParams(params);
                    isReturnAnimation = !isReturnAnimation;
                }
            }
        });
    }
}
