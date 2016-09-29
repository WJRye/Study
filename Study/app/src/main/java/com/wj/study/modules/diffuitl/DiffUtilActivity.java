package com.wj.study.modules.diffuitl;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.wj.study.BaseActivity;
import com.wj.study.R;
import com.wj.study.adapter.DiffUtilAdapter;


public class DiffUtilActivity extends BaseActivity {

    private DiffUtilAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getContentLayoutId() {
        return R.layout.activity_diff_util;
    }

    @Override
    public void initViews() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mAdapter = new DiffUtilAdapter(UserDao.getUsers());
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onClick(View v) {

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.diif_util, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_sort_by_age:
                mAdapter.swapData(UserDao.getUsersSortedByAge());
                break;
            case R.id.menu_sort_by_name:
                mAdapter.swapData(UserDao.getUsersSortedByName());
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
