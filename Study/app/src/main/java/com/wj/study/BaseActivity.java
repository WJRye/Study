package com.wj.study;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

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

        View view = getContentView();

        if (hasActionBar()) {
            LinearLayout layout = new LinearLayout(this);
            layout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            layout.setOrientation(LinearLayout.VERTICAL);
            mToolbar = (Toolbar) getLayoutInflater().inflate(R.layout.toolbar, null).findViewById(R.id.toolbar);
            setSupportActionBar(mToolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
            layout.addView(mToolbar, 0);
            layout.addView(view, 1);
            setContentView(layout);
        } else {
            setContentView(view);
        }

        initViews();
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
        startActivity(new Intent(this, clazz));
    }

    public void openActivity(Intent intent) {
        startActivity(intent);
    }


    /**
     * 得到Toolbar
     *
     * @return Toolbar
     */
    public final Toolbar getToolbar() {
        return mToolbar;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
