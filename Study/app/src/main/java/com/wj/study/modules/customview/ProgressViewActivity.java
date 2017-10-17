package com.wj.study.modules.customview;

import android.os.Bundle;
import android.view.View;

import com.wj.base.BaseActivity;
import com.wj.study.R;
import com.wj.study.view.ProgressViewWithStatus;
import com.wj.study.view.ProgressViewWithoutStatus;

public class ProgressViewActivity extends BaseActivity {

    private ProgressViewWithStatus mProgressViewWithStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getContentLayoutId() {
        return R.layout.activity_progress_view;
    }

    @Override
    public void initViews(View view) {
        ProgressViewWithoutStatus progressViewWithoutStatus01 = (ProgressViewWithoutStatus) findViewById(R.id.progress_view_without_status_01);
        ProgressViewWithoutStatus progressViewWithoutStatus02 = (ProgressViewWithoutStatus) findViewById(R.id.progress_view_without_status_02);
        ProgressViewWithoutStatus progressViewWithoutStatus03 = (ProgressViewWithoutStatus) findViewById(R.id.progress_view_without_status_03);

        progressViewWithoutStatus01.show(ProgressViewWithoutStatus.TYPE_ONE);
        progressViewWithoutStatus02.show(ProgressViewWithoutStatus.TYPE_TWO);
        progressViewWithoutStatus03.show(ProgressViewWithoutStatus.TYPE_THREE);

        mProgressViewWithStatus = (ProgressViewWithStatus) findViewById(R.id.progress_view_with_status);
        mProgressViewWithStatus.setPercentTextSize(getResources().getDimensionPixelSize(R.dimen.textSize_14sp));
        mProgressViewWithStatus.setIsShowPercentText(true);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.progress_view_with_status_start:
                mProgressViewWithStatus.setProgressGradually(1.0f);
                break;
        }
    }


}
