package com.wj.study.view;

import android.content.Context;
import android.graphics.Canvas;
import android.view.View;

/**
 * Author：王江 on 2017/6/18 21:01
 * Description:
 */

public class ShadowView extends View {
    public ShadowView(Context context) {
        super(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //设置默认的宽高度
        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);
        if (widthSpecMode == MeasureSpec.AT_MOST || heightSpecMode == MeasureSpec.AT_MOST) {
            final int defaultWidth = 240;
            final int defaultHeight = 240;
            if (widthSpecMode == MeasureSpec.AT_MOST)
                setMeasuredDimension(defaultWidth, heightSpecSize);
            if (heightSpecMode == MeasureSpec.AT_MOST)
                setMeasuredDimension(widthSpecSize, defaultHeight);
            if (widthSpecMode == MeasureSpec.AT_MOST && heightSpecMode == MeasureSpec.AT_MOST)
                setMeasuredDimension(defaultWidth, defaultHeight);
        }
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);



    }
}
