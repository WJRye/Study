package com.wj.study.view;

import android.content.Context;
import android.support.v4.widget.DrawerLayout;
import android.util.AttributeSet;

/**
 * Author：王江 on 2016/8/23 19:19
 * Description:
 */
public class NoBugDrawerLayout extends DrawerLayout {
    public NoBugDrawerLayout(Context context) {
        super(context);
    }

    public NoBugDrawerLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NoBugDrawerLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        widthMeasureSpec = MeasureSpec.makeMeasureSpec(
                MeasureSpec.getSize(widthMeasureSpec), MeasureSpec.EXACTLY);
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(
                MeasureSpec.getSize(heightMeasureSpec), MeasureSpec.EXACTLY);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
