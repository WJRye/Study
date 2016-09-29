package com.wj.study.modules.design;

import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.wj.study.BaseActivity;
import com.wj.study.R;
import com.wj.study.modules.design.fragment.ArtFragment;
import com.wj.study.modules.design.fragment.ItemFragment;

import java.util.ArrayList;
import java.util.List;

public class AppBarLayoutActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public int getContentLayoutId() {
        return R.layout.activity_app_bar_layout;
    }

    @Override
    public void initViews() {

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);


        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);

    }

    @Override
    public boolean hasActionBar() {
        return false;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_share) {
            BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
            View view = getLayoutInflater().inflate(R.layout.bottom_sheet_dialog, null);
            bottomSheetDialog.setContentView(view);
            bottomSheetDialog.show();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.app_bar_layout, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private class ViewPagerAdapter extends FragmentPagerAdapter {


        private final String[] mTitles = {"音乐", "电影", "小说", "美术"};
        private List<Fragment> mFragments = new ArrayList<>(mTitles.length);

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
            mFragments.add(ItemFragment.getInstance(ItemFragment.TYPE_MUSIC));
            mFragments.add(ItemFragment.getInstance(ItemFragment.TYPE_MOVIE));
            mFragments.add(ItemFragment.getInstance(ItemFragment.TYPE_NOVEL));
            mFragments.add(new ArtFragment());
        }

        @Override
        public int getCount() {
            return mTitles.length;
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles[position];
        }

    }
}
