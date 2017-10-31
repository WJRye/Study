package com.wj.study.modules.other;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.View;

import com.wj.base.BaseActivity;
import com.wj.study.R;
import com.wj.study.modules.other.fragment.OtherListFragment;

public class OtherActivity extends BaseActivity {

    private Fragment mCurrentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        replace(new OtherListFragment(), (String) getSupportActionBar().getTitle());
    }

    @Override
    public int getContentLayoutId() {
        return R.layout.activity_other;
    }

    @Override
    public void initViews(View rootView) {

    }

    @Override
    public void onClick(View v) {

    }

    public final void replace(Fragment fragment, String title) {
        mCurrentFragment = fragment;
        getSupportActionBar().setTitle(title);
        getSupportFragmentManager().beginTransaction().replace(R.id.other_container, fragment).commit();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && !(mCurrentFragment instanceof OtherListFragment)) {
            replace(new OtherListFragment(), (String) getSupportActionBar().getTitle());
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
