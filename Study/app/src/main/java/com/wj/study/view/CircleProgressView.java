package com.wj.study.view;

import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Author：王江 on 2016/7/6 17:33
 * Description: 无进度圆形进度条
 */
public class CircleProgressView extends View {
    /**
     * 圆的度数
     */
    private static final float ANGEL_360 = 360f;
    private static final float ANGEL_180 = 180f;
    private static final float ANGEL_330 = 330f;
    public static final int TYPE_ONE = 1;
    public static final int TYPE_TWO = 2;
    public static final int TYPE_THREE = 3;
    private Paint mCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
    private Paint mArcPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);

    /**
     * 画圆的颜色
     */
    private int mCirclePaintColor = Color.parseColor("#480eb87f");
    /**
     * 画弧的颜色
     */
    private int mArcPaintColor = Color.parseColor("#00C18B");

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
    private float mStartAngle = 0f;
    private float mSweepAngle = 0f;
    /**
     * 是否初始化
     */
    private boolean mIsInit = false;
    /**
     * 是否显示
     */
    private boolean mIsShow = false;
    /**
     * 动画
     */
    private ValueAnimator mAnimator = ValueAnimator.ofFloat(0f, ANGEL_360);


    public CircleProgressView(Context context) {
        super(context);
    }

    public CircleProgressView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CircleProgressView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(21)
    public CircleProgressView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
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
        if (mIsShow) {
            canvas.drawCircle(mCircleX, mCircleY, mCircleRadius, mCirclePaint);
            canvas.drawArc(mOval, mStartAngle, mSweepAngle, false, mArcPaint);
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
     * 显示无进度圆形进度条
     */
    public synchronized void show(final int type) {
        setVisibility(View.VISIBLE);
        mIsShow = true;
        if (mAnimator.isRunning())
            mAnimator.cancel();
        mAnimator.setTarget(this);
        mAnimator.setDuration(1500);
        mAnimator.setRepeatCount(ValueAnimator.INFINITE);
        mAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float angel = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                switch (type) {
                    case TYPE_ONE:
                        mStartAngle = angel;
                        mSweepAngle = angel;
                        break;
                    case TYPE_TWO:
                        mStartAngle = angel;
                        mSweepAngle = ANGEL_330;
                        break;
                    case TYPE_THREE:
                        if (Float.compare(angel, 0f) >= 0 && Float.compare(angel, ANGEL_180) <= 0) {
                            mStartAngle = 0f;
                            mSweepAngle = angel;
                        } else if (Float.compare(angel, ANGEL_180) > 0 && Float.compare(angel, ANGEL_360) <= 0) {
                            mStartAngle = ANGEL_180;
                            mSweepAngle = angel - ANGEL_180;
                        }
                        break;
                    default:
                        break;

                }
                postInvalidate();
            }
        });
        mAnimator.start();
    }

    /**
     * 取消无进度圆形进度条
     */
    public void dismiss() {

        mIsShow = false;
        if (mAnimator.isRunning())
            mAnimator.cancel();

        postInvalidate();

        setVisibility(View.GONE);


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
