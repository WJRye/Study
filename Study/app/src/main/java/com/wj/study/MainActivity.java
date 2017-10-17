package com.wj.study;

import android.content.Intent;
import android.graphics.Outline;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.wj.base.BaseActivity;
import com.wj.study.adapter.MainAdapter;
import com.wj.study.utils.LogUtil;


public class MainActivity extends BaseActivity {

    private final static String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private MainAdapter mAdapter;

    @Override
    public void initViews(View view) {
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        recyclerView.setClipToPadding(false);
        recyclerView.setClipChildren(false);
        recyclerView.setPadding(0, 100, 0, 0);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new MainAdapter(this);
        recyclerView.setAdapter(mAdapter);

        ImageView imageView = (ImageView) findViewById(R.id.image_view);
        imageView.setBackgroundResource(R.drawable.shadow);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            imageView.setElevation(getResources().getDimensionPixelSize(R.dimen.size_5dp));
            imageView.setTranslationZ(getResources().getDimensionPixelSize(R.dimen.size_5dp));
            ViewOutlineProvider viewOutlineProvider = new ViewOutlineProvider() {
                @Override
                public void getOutline(View view, Outline outline) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        outline.setRoundRect(0, 0, view.getWidth(), view.getHeight(), getResources().getDimensionPixelSize(R.dimen.size_2dp));
                    }
                }
            };
            imageView.setOutlineProvider(viewOutlineProvider);
        }

        imageView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(v.getContext(),"onLongClick",Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(),"onClick",Toast.LENGTH_SHORT).show();
            }
        });
//        findViewById(R.id.layout).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(v.getContext(),"setOnClickListener",Toast.LENGTH_SHORT).show();
//            }
//        });
        findViewById(R.id.layout).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction()==MotionEvent.ACTION_DOWN){
                    Toast.makeText(v.getContext(),"onTouch",Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });
        Drawable drawable = ContextCompat.getDrawable(this, R.drawable.me);
        RoundedBitmapDrawable roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(getResources(), ((BitmapDrawable) drawable).getBitmap());
        roundedBitmapDrawable.setCornerRadius(getResources().getDimensionPixelSize(R.dimen.size_2dp));
        roundedBitmapDrawable.setAntiAlias(true);
        imageView.setImageDrawable(roundedBitmapDrawable);


        TextView textView = (TextView) findViewById(R.id.text_view);
        textView.append("屏幕密度：" + getResources().getDisplayMetrics().densityDpi + "dpi");
        textView.append("\n");
        textView.append("图片宽度：" + drawable.getIntrinsicWidth());
        textView.append("\n");
        textView.append("图片高度：" + drawable.getIntrinsicHeight());

    }


    @Override
    public void onClick(View v) {
    }

    @Override
    public int getContentLayoutId() {
        return R.layout.activity_main;
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
