package com.wj.study.modules.other.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.wj.base.BaseFragment;
import com.wj.study.R;

/**
 * Created by wangjiang on 17/10/17.
 */

public class ConstraintFragment extends BaseFragment {
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        final ConstraintLayout layout = (ConstraintLayout) view;
        final ImageView imageView = (ImageView) layout.findViewById(R.id.constraint_image_view);
        Button button = (Button) layout.findViewById(R.id.constraint_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateImageView(layout, imageView);
            }
        });

    }

    private void updateImageView(ConstraintLayout layout, ImageView imageView) {
//        ConstraintSet constraintSet = new ConstraintSet();
//        constraintSet.clone(layout);
//        constraintSet.setDimensionRatio(R.id.constraint_image_view,-1, 15.9f);
//        constraintSet.applyTo(layout);
//
        ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) imageView.getLayoutParams();
        params.dimensionRatio = "15:9";
        imageView.setLayoutParams(params);

//        Fade fade = new Fade(Fade.OUT);
//        fade.setDuration(2*1000);
//        fade.setInterpolator(new LinearInterpolator());
//        TransitionManager.beginDelayedTransition(layout,fade);
//        imageView.setVisibility(View.GONE);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_constraint;
    }
}
