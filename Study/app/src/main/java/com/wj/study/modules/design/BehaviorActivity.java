package com.wj.study.modules.design;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.SwipeDismissBehavior;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.wj.base.BaseActivity;
import com.wj.study.R;

public class BehaviorActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public int getContentLayoutId() {
        return R.layout.activity_behavior;
    }

    @Override
    public void initViews(View view) {


        CardView cardView = (CardView) findViewById(R.id.card_view);
        TextView desView = (TextView) cardView.findViewById(R.id.behavior_description);
        desView.setText("An interaction behavior plugin for child views of CoordinatorLayout to provide support for the 'swipe-to-dismiss' gesture.");
        SwipeDismissBehavior behavior = new SwipeDismissBehavior();
        behavior.setListener(new SwipeDismissBehavior.OnDismissListener() {
            @Override
            public void onDismiss(View view) {
                Toast.makeText(BehaviorActivity.this, "Card Swiped!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDragStateChanged(int state) {

            }
        });
        behavior.setSwipeDirection(SwipeDismissBehavior.SWIPE_DIRECTION_ANY);
        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) cardView.getLayoutParams();
        layoutParams.setBehavior(behavior);

    }

    @Override
    public void onClick(View v) {

    }
}
