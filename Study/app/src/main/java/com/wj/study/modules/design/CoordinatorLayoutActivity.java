package com.wj.study.modules.design;


import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.wj.base.BaseActivity;
import com.wj.study.R;

public class CoordinatorLayoutActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getContentLayoutId() {
        return R.layout.activity_coordinator_layout;
    }

    @Override
    public void initViews(View view) {

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        TextView desView = (TextView) findViewById(R.id.coordinator_layout_description);
        desView.setText("CoordinatorLayout is a super-powered FrameLayout.\n" +
                "\n" +
                "CoordinatorLayout is intended for two primary use cases:\n" +
                "\n" +
                "1.As a top-level application decor or chrome layout\n" +
                "2.As a container for a specific interaction with one or more child views\n" +
                "\n" +
                "By specifying Behaviors for child views of a CoordinatorLayout you can provide many different interactions within a single parent and those views can also interact with one another. View classes can specify a default behavior when used as a child of a CoordinatorLayout using the DefaultBehavior annotation.\n" +
                "\n" +
                "Behaviors may be used to implement a variety of interactions and additional layout modifications ranging from sliding drawers and panels to swipe-dismissable elements and buttons that stick to other elements as they move and animate.\n" +
                "\n" +
                "Children of a CoordinatorLayout may have an anchor. This view id must correspond to an arbitrary descendant of the CoordinatorLayout, but it may not be the anchored child itself or a descendant of the anchored child. This can be used to place floating views relative to other arbitrary content panes.\n" +
                "\n" +
                "Children can spec");


    }


    @Override
    public void onClick(View v) {

    }

    @Override
    public boolean hasActionBar() {
        return false;
    }
}
