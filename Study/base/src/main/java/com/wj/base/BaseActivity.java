package com.wj.base;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wj.base.utils.DisplayUtil;

import example.com.wj.base.R;

/**
 * Author：王江 on 2016/7/7 16:20
 * Description:
 */
public abstract class BaseActivity extends AppCompatActivity implements IBaseView {

    private Toolbar mToolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getWindow().setFormat(PixelFormat.TRANSPARENT);

        View view = getContentView();
        if (view != null) {
            if (hasActionBar()) {
                LinearLayout layout = new LinearLayout(this);
                layout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                layout.setOrientation(LinearLayout.VERTICAL);

                mToolbar = (Toolbar) getLayoutInflater().inflate(R.layout.toolbar, null).findViewById(R.id.toolbar);
                setSupportActionBar(mToolbar);
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                getSupportActionBar().setHomeButtonEnabled(true);
                getSupportActionBar().setDisplayShowTitleEnabled(true);
                getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_TITLE);

                layout.addView(mToolbar, 0);
                layout.addView(view, 1, new LinearLayout.LayoutParams(-1, -1));
                setContentView(layout);
            } else {
                setContentView(view);
            }

            initViews(view);
        }
    }

    /**
     * 是否添加Toolbar
     *
     * @return true，添加Toolbar；false,不添加Toolbar。默认为false。
     */
    public boolean hasActionBar() {
        return true;
    }

    public View getContentView() {
        return getLayoutInflater().inflate(getContentLayoutId(), null);
    }

    public abstract int getContentLayoutId();

    public void openActivity(Class<?> clazz) {
        openActivity(new Intent(this, clazz));
    }

    public void openActivity(Intent intent) {
        if (intent.resolveActivity(getPackageManager()) != null)
            startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public Toolbar getToolbar() {
        return mToolbar;
    }

    /**
     * 自定义ActionBar
     *
     * @param view
     */
    public void setActionBarCustomView(View view) {
        if (!hasActionBar()) return;

        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayUseLogoEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        mToolbar.setContentInsetsAbsolute(0, 0);
        mToolbar.setContentInsetsRelative(0, 0);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(view, new ActionBar.LayoutParams(-1, -1));
    }

    /**
     * 设置默认的ActionBar
     */
    public void setDefaultActionBarView() {
        if (!hasActionBar()) return;

        RelativeLayout layout = new RelativeLayout(this);
        int height = DisplayUtil.getActionBarHeight(this);
        ImageView leftIcon = new ImageView(this);
        leftIcon.setClickable(true);
        RelativeLayout.LayoutParams leftParams = new RelativeLayout.LayoutParams(height, height);
        leftParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        leftParams.addRule(RelativeLayout.CENTER_VERTICAL);
        leftIcon.setLayoutParams(leftParams);
        leftIcon.setScaleType(ImageView.ScaleType.CENTER);
        leftIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onLeftIconClick();
            }
        });
        layout.addView(leftIcon);

        TextView centerTitle = new TextView(this);
        RelativeLayout.LayoutParams centerParams = new RelativeLayout.LayoutParams(-2, height);
        centerParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        centerTitle.setLayoutParams(centerParams);
        centerTitle.setTextColor(Color.WHITE);
        centerTitle.setGravity(Gravity.CENTER);
        layout.addView(centerTitle);

        setActionBarCustomView(layout);
    }

    public void onLeftIconClick() {
        finish();
    }

    /**
     * 获得自定义标题栏的左边ImageView
     *
     * @return ImageView
     */
    public ImageView getLeftIcon() {
        return (ImageView) getActionBarCustomChildView(0);
    }

    /**
     * 获得自定义标题栏的中间的TextView
     *
     * @return TextView
     */
    public TextView getCenterTitle() {
        return (TextView) getActionBarCustomChildView(1);
    }

    private View getActionBarCustomChildView(int index) {
        if (hasActionBar()) {
            View view = getSupportActionBar().getCustomView();
            if (view != null && getSupportActionBar().getDisplayOptions() == ActionBar.DISPLAY_SHOW_CUSTOM) {
                return ((RelativeLayout) view).getChildAt(index);
            }
        }
        return null;
    }
}
