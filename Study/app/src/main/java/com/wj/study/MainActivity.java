package com.wj.study;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Trace;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.wj.base.BaseActivity;
import com.wj.study.adapter.MainAdapter;
import com.wj.study.utils.LogUtil;


public class MainActivity extends BaseActivity {

    private final static String TAG = "MainActivity";

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Trace.beginSection("MainActivity_onCreate");
        super.onCreate(savedInstanceState);
        Trace.endSection();
    }

    private MainAdapter mAdapter;

    @Override
    public void initViews(View view) {
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new MainAdapter(this);
        recyclerView.setAdapter(mAdapter);
    }


    @Override
    public void onClick(View v) {
    }

    @Override
    public int getContentLayoutId() {
        return R.layout.recycler_view;
    }


    @Override
    public void onStop() {
        super.onStop();
        LogUtil.d(TAG, "onStop()");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_git) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("https://github.com/WJRye/Study"));
            openActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
