package com.wj.study.modules.transition.fragment;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.transition.ChangeBounds;
import android.transition.ChangeImageTransform;
import android.transition.TransitionManager;
import android.transition.TransitionSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.wj.base.BaseFragment;
import com.wj.study.R;

/**
 * Author：王江 on 2017/2/27 17:14
 * Description:
 */

public class TransitionImageFragment extends BaseFragment {
    @Override
    public int getLayoutId() {
        return R.layout.transition_image_fragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        final FrameLayout transitionsContainer = (FrameLayout) view.findViewById(R.id.transition_image_container);
        final ImageView imageView = (ImageView) transitionsContainer.findViewById(R.id.transition_image);
        imageView.setOnClickListener(new View.OnClickListener() {
            boolean expanded = true;

            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= 21) {

                    TransitionManager.beginDelayedTransition(transitionsContainer, new TransitionSet()
                            .addTransition(new ChangeBounds())
                            .addTransition(new ChangeImageTransform()));

                    FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) imageView.getLayoutParams();
                    params.width = expanded ? FrameLayout.LayoutParams.MATCH_PARENT :
                            FrameLayout.LayoutParams.WRAP_CONTENT;
                    params.height = expanded ? FrameLayout.LayoutParams.MATCH_PARENT :
                            FrameLayout.LayoutParams.WRAP_CONTENT;
                    imageView.setLayoutParams(params);
                    imageView.setScaleType(expanded ? ImageView.ScaleType.CENTER_CROP :
                            ImageView.ScaleType.FIT_CENTER);
                    expanded = !expanded;
                }
            }
        });
    }
}
