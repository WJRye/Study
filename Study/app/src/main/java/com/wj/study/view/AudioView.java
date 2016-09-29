
package com.wj.study.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * <br>
 * <b> Class: </b> DisplayAudioView<br>
 * <b> Description: </b> 根据声贝，显示声音波动图
 *
 * @author wangjiang
 * @version 1.0 <b> Date: </b>2015-9-12
 */

public class AudioView extends View {

    private int mBaselineColor = Color.DKGRAY;
    private int mLineColor = Color.DKGRAY;
    private float mBaselineWidth = 1f;
    private float mLineWidth = 1f;
    private int mWidth;
    private float mY = 0f;
    /**
     * <br>
     * <b>Type: </b> boolean <br>
     * <b> Description: </b> 是否初始化
     */
    private boolean mIsInit = false;
    /**
     * <br>
     * <b>Type: </b> int <br>
     * <b> Description: </b> 声贝的大小
     */
    private int mDB;
    private float mDivider = 3f;

    private Paint mLinePaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
    private Paint mBaselinePaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
    private LinePoint mBaseLinePoint = new LinePoint();
    private List<LinePoint> mPoints = new ArrayList<>();
    private List<LinePoint> mRemovePoints = new ArrayList<>();
    ;


    public AudioView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @TargetApi(21)
    public AudioView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public AudioView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AudioView(Context context) {
        super(context);
    }

    /**
     * <br>
     * <b> Description: </b> 设置声音波动线的颜色值，默认为灰色
     *
     * @param color 颜色值
     */
    public void setLineColor(int color) {
        mLineColor = color;
    }

    /**
     * <br>
     * <b> Description: </b> 设置基线的颜色值，默认为灰色
     *
     * @param color 颜色值
     */
    public void setBaselineColor(int color) {
        mBaselineColor = color;
    }

    /**
     * <br>
     * <b> Description: </b> 设置画声波线的宽度
     *
     * @param width 画声波线的宽度
     * @return void 返回类型
     */
    public void setLineStrokeWidth(float width) {
        if (width > 0)
            mLineWidth = width;
    }

    /**
     * <br>
     * <b> Description: </b> 设置画基线的宽度
     *
     * @param width 画基线的宽度
     * @return void 返回类型
     */
    public void setBaselineStrokeWidth(float width) {
        if (width > 0)
            mBaselineWidth = width;
    }


    /**
     * @param divider 线与线之间的距离，这个距离是相对于线的宽度的
     */
    public void setDivider(float divider) {
        this.mDivider = divider;
    }


    private void displayAudioView(Canvas canvas) {
        // 画基线
        canvas.drawLine(mBaseLinePoint.startX, mBaseLinePoint.startY, mBaseLinePoint.stopX, mBaseLinePoint.stopY,
                mBaselinePaint);

        int size = mPoints.size();
        float startX = mWidth - size * mDivider;

        // 计算 纵坐标y的值，上下两个值
        LinePoint linePoint = new LinePoint();
        linePoint.startY = mY - mDB;
        linePoint.stopY = mY + mDB;
        mPoints.add(linePoint);

        LinePoint point = null;
        for (int i = 0; i < size; i++) {
            point = mPoints.get(i);
            canvas.drawLine(startX + i * mDivider, point.startY, startX + i
                    * mDivider, point.stopY, mLinePaint);
            if (startX + i * mDivider <= 0f)
                mRemovePoints.add(point);
        }
        mPoints.removeAll(mRemovePoints);
        mRemovePoints.clear();
    }

    public void invalidateView(int db) {
        mDB = db;
        postInvalidate();
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
            final int defaultWidth = 360;
            final int defaultHeight = 360;
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
        if (!mIsInit) {
            mIsInit = true;
            init();
        } else {
            displayAudioView(canvas);
        }
    }

    /**
     * <b> Description: </b> 初始化值
     */
    private void init() {

        mWidth = getWidth() - getPaddingLeft() - getPaddingRight();

        mBaseLinePoint.startX = getPaddingLeft();
        mBaseLinePoint.stopX = getWidth() - getPaddingRight();
        mBaseLinePoint.startY = getHeight() / 2;
        mBaseLinePoint.stopY = getHeight() / 2;

        mY = getHeight() / 2;

        // 默认声音波动线的颜色
        mLinePaint.setColor(mLineColor);
        mLinePaint.setStrokeWidth(mLineWidth);
        mLinePaint.setStyle(Paint.Style.STROKE);
        mLinePaint.setStrokeCap(Paint.Cap.SQUARE);

        // 默认基线的颜色
        mBaselinePaint.setColor(mBaselineColor);
        mBaselinePaint.setStrokeWidth(mBaselineWidth);
        mBaselinePaint.setStrokeCap(Paint.Cap.SQUARE);
        // 保持屏幕高亮
        setKeepScreenOn(true);

    }

    private class LinePoint {
        private float startX;
        private float startY;
        private float stopX;
        private float stopY;
    }

}
