package com.wj.study.modules.customview;

import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wj.base.BaseActivity;
import com.wj.study.R;

/**
 * Author：王江 on 2017/6/12 14:41
 * Description:
 */
public class ArcViewActivity extends BaseActivity {

    private EditText mNewText;
    private EditText mOldText;

    public TextView mTop;
    public TextView mAscent;
    public TextView mDescent;
    public TextView mBottom;
    public TextView mBaseline;
    public TextView mLeading;
    public TextView mTextBounds;

    private MyTextView myTextView;

    @Override
    public int getContentLayoutId() {
        return R.layout.activity_arc_view;
    }

    @Override
    public void initViews(View view) {
        LinearLayout frameLayout = (LinearLayout) findViewById(R.id.activity_arc_layout);
        int margin = getResources().getDimensionPixelSize(R.dimen.margin_10dp);
        int defaultWidth = getResources().getDisplayMetrics().widthPixels - 2 * margin;

        myTextView = new MyTextView(this, defaultWidth, defaultWidth / 2);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(-2, -2);
        params.gravity = Gravity.CENTER_VERTICAL;
        params.leftMargin = margin;
        params.rightMargin = margin;
        frameLayout.addView(myTextView, 0, params);

        mNewText = (EditText) findViewById(R.id.arc_new_text);
        mOldText = (EditText) findViewById(R.id.arc_old_text);

        mTop = (TextView) findViewById(R.id.arc_top);
        mAscent = (TextView) findViewById(R.id.arc_ascent);
        mDescent = (TextView) findViewById(R.id.arc_descent);
        mBottom = (TextView) findViewById(R.id.arc_bottom);
        mBaseline = (TextView) findViewById(R.id.arc_baseline);
        mLeading = (TextView) findViewById(R.id.arc_leading);
        mTextBounds = (TextView) findViewById(R.id.arc_bounds);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.acr_update:
                mNewText.setText(mOldText.getText().toString());
                myTextView.setText(mOldText.getText().toString());
                mOldText.setText(null);
                break;
        }
    }
}
