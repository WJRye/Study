
package com.wj.study.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.wj.study.R;


/**
 * Created by wangjiang on 17/6/5.
 */

public class ArcView extends View {

    private final float START_ANGLE = 0f;
    private final float SWEEP_ANGLE = 90f;

    private int mRadius = 0;

    private int mWidth = 0;
    private int mHeight = 0;
    private String mText = "我的名字叫Rye";
    private int mTextTopMargin = 0;
    private int mTextLeftMargin = 0;

    private Paint mCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
    private Paint mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);

    public ArcView(Context context, int width, int height) {
        super(context);
        init(width, height, getResources().getDimensionPixelSize(R.dimen.size_2dp));
    }


    public ArcView(Context context, int width, int height, int radius) {
        super(context);
        init(width, height, radius);
    }

    private void init(int width, int height, int radius) {
        this.mWidth = width;
        this.mHeight = height;
        this.mRadius = radius;
        mCirclePaint.setStyle(Paint.Style.FILL);
        mCirclePaint.setStrokeCap(Paint.Cap.ROUND);
        mTextPaint.setStrokeCap(Paint.Cap.ROUND);
        mTextPaint.setStyle(Paint.Style.FILL);
        mTextPaint.setTextSize(getResources().getDimensionPixelSize(R.dimen.textSize_18sp));
        mTextPaint.setColor(Color.BLUE);
        mCirclePaint.setColor(Color.RED);
        setLayerType(LAYER_TYPE_HARDWARE, null);
        mTextTopMargin = getResources().getDimensionPixelSize(R.dimen.px_12);
        mTextLeftMargin = getResources().getDimensionPixelSize(R.dimen.px_8);
    }


    public ArcView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ArcView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
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
        canvas.drawARGB(0, 225, 225, 255);
        // 绘制下层图
        RectF rect = new RectF(0, 0, mWidth, mHeight);
        mCirclePaint.setColor(Color.MAGENTA);
        canvas.drawRoundRect(rect, mRadius, mRadius, mCirclePaint);

        canvas.drawRect(new RectF(0, mHeight - 2 * mRadius, 2 * mRadius, mHeight), mCirclePaint);
        canvas.drawRect(new RectF(mWidth - 2 * mRadius, 0, mWidth, 2 * mRadius), mCirclePaint);

        //取绘制的图的下层部分，和下层与上层的交集部分
        mCirclePaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));

        mCirclePaint.setColor(Color.RED);
        RectF oval = new RectF(-mWidth, -mHeight, mWidth, mHeight);
        canvas.drawArc(oval, START_ANGLE, SWEEP_ANGLE, true, mCirclePaint);

        mCirclePaint.setXfermode(null);

        if (mText == null) mText = "";
        Rect bounds = new Rect();
        mTextPaint.getTextBounds(mText, 0, mText.length(), bounds);
        mTextPaint.setColor(Color.GREEN);

        Paint.FontMetrics fontMetrics = mTextPaint.getFontMetrics();
        mTextPaint.setColor(Color.BLUE);
        canvas.drawText(mText, mTextLeftMargin, mTextTopMargin + (fontMetrics.bottom - fontMetrics.top + fontMetrics.leading) / 2, mTextPaint);

        canvas.drawText(mText, (mWidth - (bounds.right + bounds.left)) / 2, (mHeight - (bounds.bottom + bounds.top)) / 2, mTextPaint);
        canvas.drawLine(0, mHeight / 2, mWidth, mHeight / 2, mTextPaint);
        canvas.drawLine(mWidth / 2, 0, mWidth / 2, mHeight, mTextPaint);
        canvas.drawLine(mWidth, 0, mWidth, mHeight, mTextPaint);
        canvas.drawLine(0, mHeight, mWidth, mHeight, mTextPaint);
    }

    public final void setText(String text) {
        mText = text;
        postInvalidate();
    }

    public final void setTextColor(int color) {
        mTextPaint.setColor(color);
        postInvalidate();
    }

    public final void setArcColor(int color) {
        mCirclePaint.setColor(color);
        postInvalidate();
    }
}
