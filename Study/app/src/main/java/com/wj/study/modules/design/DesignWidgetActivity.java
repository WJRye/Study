package com.wj.study.modules.design;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.wj.study.BaseActivity;
import com.wj.study.R;

public class DesignWidgetActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public int getContentLayoutId() {
        return R.layout.activity_design_widget;
    }

    @Override
    public void initViews() {

        int[] resStringIds = {R.string.app_bar_layout, R.string.navigation_view, R.string.edit_text, R.string.floating_action_button, R.string.snackbar, R.string.coordinator_layout, R.string.behavior};
        int[] resIds = {R.id.item_app_bar_layout, R.id.item_navigation_view, R.id.item_edit_text, R.id.item_floating_action_button, R.id.item_snackbar, R.id.item_coordinator_layout, R.id.item_behavior};
        for (int i = 0, len = resIds.length; i < len; i++) {
            TextView textView = (TextView) findViewById(resIds[i]).findViewById(R.id.default_item_title);
            textView.setText(resStringIds[i]);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.item_app_bar_layout:
                openActivity(AppBarLayoutActivity.class);
                break;
            case R.id.item_navigation_view:
                openActivity(NavigationViewActivity.class);
                break;
            case R.id.item_edit_text:
                openActivity(EditTextActivity.class);
                break;
            case R.id.item_floating_action_button:
                openActivity(FloatingActionButtonActivity.class);
                break;
            case R.id.item_snackbar:
                openActivity(SnackbarActivity.class);
                break;
            case R.id.item_coordinator_layout:
                openActivity(CoordinatorLayoutActivity.class);
                break;
            case R.id.item_behavior:
                openActivity(BehaviorActivity.class);
                break;
            default:
                break;
        }
    }
}
