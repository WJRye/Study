package com.wj.study.view;

import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import java.math.BigDecimal;

/**
 * Author：王江 on 2016/7/6 11:15
 * Description: 圆形进度条
 */
public class ProgressView extends View {
    /**
     * 圆的度数
     */
    private static final float ANGEL_360 = 360f;
    private Paint mCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
    private Paint mArcPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
    private Paint mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
    private Point mTextPoint = new Point();
    /**
     * 画圆的颜色
     */
    private int mCirclePaintColor = Color.parseColor("#480eb87f");
    /**
     * 画弧的颜色
     */
    private int mArcPaintColor = Color.parseColor("#00C18B");

    private int mTextPaintColor = Color.parseColor("#00C18B");
    /**
     * 画笔的宽度
     */
    private float mPaintWidth = 10f;
    /**
     * 圆心半径
     */
    private float mCircleRadius;
    /**
     * 圆心x坐标
     */
    private float mCircleX;
    /**
     * 圆心y坐标
     */
    private float mCircleY;
    private RectF mOval = new RectF();
    private float mSweepAngle = 0f;
    /**
     * 是否初始化
     */
    private boolean mIsInit = false;

    /**
     * 是否显示百分比文本
     */
    private boolean mIsShowPercentText = false;


    public ProgressView(Context context) {
        super(context);
    }

    public ProgressView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ProgressView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(21)
    public ProgressView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
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
            final int defaultWidth = 120;
            final int defaultHeight = 120;
            if (widthSpecMode == MeasureSpec.AT_MOST)
                setMeasuredDimension(defaultWidth, heightSpecSize);
            if (heightSpecMode == MeasureSpec.AT_MOST)
                setMeasuredDimension(widthSpecSize, defaultHeight);
            if (widthSpecMode == MeasureSpec.AT_MOST && heightSpecMode == MeasureSpec.AT_MOST)
                setMeasuredDimension(defaultWidth, defaultHeight);
        }
    }

    @Override
    protected void onDraw(final Canvas canvas) {
        super.onDraw(canvas);

        if (!mIsInit) {
            init();
            mIsInit = true;
        }
        canvas.drawCircle(mCircleX, mCircleY, mCircleRadius, mCirclePaint);
        canvas.drawArc(mOval, 0, mSweepAngle, false, mArcPaint);

        if (mIsShowPercentText) {
            String text = Float.compare(mSweepAngle, ANGEL_360) <= 0 ? (int) (new BigDecimal(mSweepAngle / ANGEL_360).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue() * 100) + "%" : "100%";
            Rect bounds = new Rect();
            mTextPaint.getTextBounds(text, 0, text.length(), bounds);
            canvas.drawText(text, mTextPoint.x - (bounds.left + bounds.right) / 2, mTextPoint.y - (bounds.top + bounds.bottom) / 2, mTextPaint);
        }
    }

    /**
     * 初始化
     */
    private void init() {
        mCirclePaint.setStrokeWidth(mPaintWidth);
        mCirclePaint.setStyle(Paint.Style.STROKE);
        mCirclePaint.setStrokeCap(Paint.Cap.ROUND);
        mCirclePaint.setColor(mCirclePaintColor);
        int width = getWidth() - getPaddingLeft() - getPaddingRight();
        int height = getHeight() - getPaddingTop() - getPaddingBottom();
        mCircleRadius = 0f;
        float l, r, t, b;
        if (width >= height) {
            mCircleRadius = height / 2 - mPaintWidth / 2;
            l = getPaddingLeft() + (width - height) / 2 + mPaintWidth / 2;
            r = getPaddingLeft() + (width - height) / 2 + height - mPaintWidth / 2;
            t = getPaddingTop() + mPaintWidth / 2;
            b = getPaddingTop() + height - mPaintWidth / 2;
        } else {
            mCircleRadius = width / 2 - mPaintWidth / 2;
            l = getPaddingLeft() + mPaintWidth / 2;
            r = getPaddingLeft() + width - mPaintWidth / 2;
            t = getPaddingTop() + (height - width) / 2 + mPaintWidth / 2;
            b = getPaddingTop() + (height - width) / 2 + width - mPaintWidth / 2;
        }
        mOval.set(l, t, r, b);
        mCircleX = getWidth() / 2;
        mCircleY = getHeight() / 2;

        mArcPaint.setStrokeWidth(mPaintWidth);
        mArcPaint.setStyle(Paint.Style.STROKE);
        mArcPaint.setStrokeCap(Paint.Cap.ROUND);
        mArcPaint.setColor(mArcPaintColor);
        mSweepAngle = 0f;

        mTextPaint.setStrokeWidth(2.0f);
        mTextPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mTextPaint.setStrokeCap(Paint.Cap.ROUND);
        mTextPaint.setColor(mTextPaintColor);
        mTextPoint.set(getPaddingLeft() + width / 2, getPaddingTop() + height / 2);
    }

    /**
     * 设置圆圈的颜色
     *
     * @param color 圆圈的颜色
     */
    public void setCircleBackgroundColor(int color) {
        mCirclePaintColor = color;
    }

    /**
     * 设置进度条的颜色
     *
     * @param color 进度条的颜色
     */
    public void setProgressBackgroundColor(int color) {
        mArcPaintColor = color;
    }

    /**
     * 设置文字显示百分比的字体颜色
     *
     * @param color 字体颜色
     */
    public void setPercentTextColor(int color) {
        mTextPaintColor = color;
    }

    /**
     * 设置是否用文字显示百分比
     *
     * @param isShowPercentText 是否用文字显示百分比
     */
    public void setIsShowPercentText(boolean isShowPercentText) {
        mIsShowPercentText = isShowPercentText;
    }

    /**
     * 设置文字显示百分比的字体大小
     *
     * @param textSize 字体大小
     */
    public void setPercentTextSize(float textSize) {
        mTextPaint.setTextSize(textSize);
    }

    /**
     * 设置当前的进度，当进度是渐进的
     *
     * @param progress 当前进度的百分比，值在0到1范围内
     */
    public synchronized void setProgressGradually(float progress) {
        float angle = ANGEL_360 * progress;
        if (Float.compare(angle, ANGEL_360) > 0) {
            angle = ANGEL_360;
        }

        ValueAnimator animator = ValueAnimator.ofFloat(0f, angle);
        animator.setTarget(this);
        animator.setDuration(1500);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                mSweepAngle = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                postInvalidate();
            }
        });
        animator.start();
    }

    /**
     * 设置当前的进度
     *
     * @param progress 当前进度的百分比，值在0到1范围内
     */
    public synchronized void setProgress(float progress) {
        mSweepAngle = ANGEL_360 * progress;
        if (Float.compare(mSweepAngle, ANGEL_360) > 0) {
            mSweepAngle = ANGEL_360;
        }
        postInvalidate();
    }


    /**
     * 设置画笔的宽度
     *
     * @param paintWidth 画笔的宽度
     */
    public void setPaintWidth(float paintWidth) {
        mPaintWidth = paintWidth;
    }

}
