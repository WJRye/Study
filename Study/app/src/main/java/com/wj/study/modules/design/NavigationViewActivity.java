package com.wj.study.modules.design;

import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.wj.study.BaseActivity;
import com.wj.study.R;
import com.wj.study.adapter.ItemAdapter;
import com.wj.study.util.DisplayUtil;

import java.util.ArrayList;
import java.util.List;

public class NavigationViewActivity extends BaseActivity {
    public static final int TYPE_MUSIC = 1;
    public static final int TYPE_MOVIE = 2;
    public static final int TYPE_NOVEL = 3;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;

    private RecyclerView mRecyclerView;
    private List<String> mDatas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public int getContentLayoutId() {
        return R.layout.activity_navigation_view;
    }

    @Override
    public void initViews() {


        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        ImageView portraitView = (ImageView) navigationView.getHeaderView(0).findViewById(R.id.drawer_header_portrait);
        String[] titles = {"音乐", "电影", "小说", "美术"};
        int[] ids = {R.id.drawer_menu_music, R.id.drawer_menu_movie, R.id.drawer_menu_novel, R.id.drawer_menu_art};
        View drawerMenu = getLayoutInflater().inflate(R.layout.drawer_menu, null);
        for (int i = 0, len = ids.length; i < len; i++) {
            TextView titleView = (TextView) drawerMenu.findViewById(ids[i]).findViewById(R.id.default_item_title);
            titleView.setText(titles[i]);
        }
//        ImageView portraitView = (ImageView) drawerMenu.findViewById(R.id.portrait);
        Bitmap portraitBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.me);
        RoundedBitmapDrawable drawable = RoundedBitmapDrawableFactory.create(getResources(), portraitBitmap);
        drawable.setFilterBitmap(true);
        drawable.setAntiAlias(true);
        drawable.setCircular(true);
        portraitView.setImageDrawable(drawable);
//        navigationView.addView(drawerMenu);

        navigationView.setItemTextColor(new ColorStateList(new int[][]{{android.R.attr.state_pressed}, {android.R.attr.state_focused}, {}}, new int[]{R.color.colorPrimary, R.color.colorPrimary, R.color.colorPrimary}));
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                if (item.getItemId() == R.id.navigation_item_1) {
                    //do something
                    return true;
                }
                return false;
            }
        });

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ViewGroup.LayoutParams params = mDrawerLayout.getLayoutParams();
        params.width = DisplayUtil.getScreenWidth(this);
        params.height = DisplayUtil.getContentHeight(this);
        mDrawerLayout.setLayoutParams(params);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, getToolbar(), R.string.open, R.string.close) {
            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                getSupportActionBar().setTitle(R.string.menu);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle(R.string.detail);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

        };
        mDrawerLayout.addDrawerListener(mDrawerToggle);


        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mDatas = getDatas(TYPE_MUSIC);
        mRecyclerView.setAdapter(new ItemAdapter(mDatas));


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.drawer_menu_music:
                mDatas.clear();
                mDatas.addAll(getDatas(TYPE_MUSIC));
                mDrawerLayout.closeDrawer(GravityCompat.START);
                mRecyclerView.getAdapter().notifyDataSetChanged();
                break;
            case R.id.drawer_menu_movie:
                mDatas.clear();
                mDatas.addAll(getDatas(TYPE_MOVIE));
                mDrawerLayout.closeDrawer(GravityCompat.START);
                mRecyclerView.getAdapter().notifyDataSetChanged();
                break;
            case R.id.drawer_menu_novel:
                mDatas.clear();
                mDatas.addAll(getDatas(TYPE_NOVEL));
                mDrawerLayout.closeDrawer(GravityCompat.START);
                mRecyclerView.getAdapter().notifyDataSetChanged();
                break;
            case R.id.drawer_menu_art:
                mDrawerLayout.closeDrawer(GravityCompat.START);
                break;
            default:

                break;

        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.navigation_view, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        // Handle your other action bar items...

        return super.onOptionsItemSelected(item);
    }

    private List<String> getDatas(int type) {
        List<String> datas = new ArrayList<>();
        if (type == TYPE_MUSIC) {
            datas.add("Hello");
            datas.add("Turning table");
            datas.add("Lose yourself");
            datas.add("Not afraid");
            datas.add("Yes");
            datas.add("No");
            datas.add("You don't you're beautiful");
            datas.add("You're my sunshine");
            datas.add("You raise me up");
            datas.add("天气这么热");
            datas.add("天气这么热");
            datas.add("天气这么热");
            datas.add("天气这么热");
            datas.add("天气这么热");
            datas.add("天气这么热");
            datas.add("天气这么热");
            datas.add("天气这么热");
            datas.add("天气这么热");
            datas.add("天气这么热");
            datas.add("天气这么热");
        } else if (type == TYPE_MOVIE) {
            datas.add("肖生克的救赎");
            datas.add("阿甘正传");
            datas.add("肖生克的救赎");
            datas.add("阿甘正传");
            datas.add("肖生克的救赎");
            datas.add("阿甘正传");
            datas.add("乱世佳人");
            datas.add("肖生克的救赎");
            datas.add("肖生克的救赎");
            datas.add("肖生克的救赎");
            datas.add("肖生克的救赎");
            datas.add("肖生克的救赎");
            datas.add("肖生克的救赎");
            datas.add("肖生克的救赎");
            datas.add("肖生克的救赎");
            datas.add("肖生克的救赎");
            datas.add("肖生克的救赎");
        } else if (type == TYPE_NOVEL) {
            datas.add("卡拉马佐夫兄弟");
            datas.add("罪与罚");
            datas.add("战争与和平");
            datas.add("卡拉马佐夫兄弟");
            datas.add("卡拉马佐夫兄弟");
            datas.add("卡拉马佐夫兄弟");
            datas.add("卡拉马佐夫兄弟");
            datas.add("卡拉马佐夫兄弟");
            datas.add("卡拉马佐夫兄弟");
            datas.add("卡拉马佐夫兄弟");
            datas.add("卡拉马佐夫兄弟");
            datas.add("卡拉马佐夫兄弟");
            datas.add("卡拉马佐夫兄弟");
            datas.add("卡拉马佐夫兄弟");
            datas.add("卡拉马佐夫兄弟");
            datas.add("卡拉马佐夫兄弟");
            datas.add("卡拉马佐夫兄弟");
            datas.add("卡拉马佐夫兄弟");
            datas.add("卡拉马佐夫兄弟");
            datas.add("卡拉马佐夫兄弟");
        }


        return datas;
    }
}
