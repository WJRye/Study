package com.wj.study.modules.transition;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.wj.base.BaseActivity;
import com.wj.study.R;
import com.wj.study.modules.transition.fragment.TransitionExplodeFragment;
import com.wj.study.modules.transition.fragment.TransitionImageFragment;
import com.wj.study.modules.transition.fragment.TransitionMainFragment;
import com.wj.study.modules.transition.fragment.TransitionNameFragment;
import com.wj.study.modules.transition.fragment.TransitionPathFragment;
import com.wj.study.modules.transition.fragment.TransitionSlidFragment;
import com.wj.study.modules.transition.fragment.TransitionVisibleFragment;

public class TransitionActivity extends BaseActivity {

    public final static String TAG_MAIN = TransitionMainFragment.class.getName();
    public final static String TAG_VISIBLE = TransitionVisibleFragment.class.getName();
    public final static String TAG_SLID = TransitionSlidFragment.class.getName();
    public final static String TAG_EXPLODE = TransitionExplodeFragment.class.getName();
    public final static String TAG_IMAGE = TransitionImageFragment.class.getName();
    public final static String TAG_PATH = TransitionPathFragment.class.getName();
    public final static String TAG_TRANSITION_NAME = TransitionNameFragment.class.getName();
    private Fragment mCurrentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getContentLayoutId() {
        return R.layout.activity_transition;
    }

    @Override
    public void initViews(View view) {
        mCurrentFragment = new TransitionMainFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.transition_container, mCurrentFragment, TAG_MAIN).commit();
    }

    public final void replaceFragment(String tag) {
        Log.d("TAG", "replaceFragment");
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(tag);
        if (fragment == null) {
            try {
                fragment = (Fragment) Class.forName(tag).newInstance();
                if (fragment == null) return;
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.transition_container, fragment, tag).commit();
                mCurrentFragment = fragment;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public final void onClick(View v) {
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            if (mCurrentFragment != null && !TAG_MAIN.equals(mCurrentFragment.getTag())) {
                replaceFragment(TAG_MAIN);
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
