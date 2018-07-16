package com.example.customizeviewlibrary;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.SweepGradient;
import android.util.AttributeSet;
import android.view.View;

/**
 * 【类功能说明】
 * 可变圆环
 * File: SuperCircleView.java
 * @author longfeng
 * Vesion: 3.2.0
 * Create: 2018/7/16
 * Changes (from 2018/7/16)
 * -------------------------------------------------------
 * 2018/7/16:创建SuperCircleView.java(longfeng)
 * -------------------------------------------------------
 */
public class RingView extends View {
    /**
     * 小圆半径、圆环宽度、圆环段数、段数角度(长度)、小圆颜色、大圆颜色、圆环颜色、圆环渐变区域、
     * 圆环渐变色、是否显示分段区域、每一段的角度
     */
    private int mSmallCircleRadius;
    private float mRingWidth;
    private int mSegmentation;
    private int mSegmentationAngle;
    private int mMinCircleColor;
    private int mMaxCircleColor;
    private int mRingNormalColor;
    private int mRingColorSelect = 0;
    private int color[] = new int[3];
    private boolean mIsShowSegmentation;
    private float mRingAngleWidth;

    /**
     * 画笔、view的宽、高、宽的中心点、高的中心点、圆环的矩形区域
     */
    private Paint mPaint;
    private int mViewWidth;
    private int mViewHeight;
    private int mViewCenterX;
    private int mViewCenterY;
    private RectF mRectF;

    public RingView(Context context) {
        this(context, null);
    }

    public RingView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.SuperCircleView);
        mSmallCircleRadius = typedArray.getInteger(R.styleable.SuperCircleView_small_circle_radius, 400);
        mRingWidth = typedArray.getFloat(R.styleable.SuperCircleView_ring_width, 20);
        mSegmentation = typedArray.getInteger(R.styleable.SuperCircleView_segmentation, 7);
        mSegmentationAngle = typedArray.getInteger(R.styleable.SuperCircleView_segmentation_angle, 3);
        mMinCircleColor = typedArray.getColor(R.styleable.SuperCircleView_min_circle_color,
                context.getResources().getColor(R.color.white));
        mMaxCircleColor = typedArray.getColor(R.styleable.SuperCircleView_max_circle_color,
                context.getResources().getColor(R.color.white));
        mRingNormalColor = typedArray.getColor(R.styleable.SuperCircleView_ring_normal_color,
                context.getResources().getColor(R.color.game_green));
        mRingColorSelect = typedArray.getInteger(R.styleable.SuperCircleView_ring_color_select, 0);
        mIsShowSegmentation = typedArray.getBoolean(R.styleable.SuperCircleView_is_show_segmentation, false);
        typedArray.recycle();

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setAntiAlias(true);
        //解决自定义View的onDraw方法不被执行问题
        this.setWillNotDraw(false);
        color[0] = Color.parseColor("#07d6b5");
        color[1] = Color.parseColor("#07d6b5");
        color[2] = Color.parseColor("#07d6b5");
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        mViewWidth = getMeasuredWidth();
        mViewHeight = getMeasuredHeight();
        mViewCenterX = mViewWidth / 2;
        mViewCenterY = mViewHeight / 2;
        mRectF = new RectF(mViewCenterX - mSmallCircleRadius - mRingWidth / 2,
                mViewCenterY - mSmallCircleRadius - mRingWidth / 2,
                mViewCenterX + mSmallCircleRadius + mRingWidth / 2,
                mViewCenterY + mSmallCircleRadius + mRingWidth / 2);
        mRingAngleWidth = (360 - mSegmentation * mSegmentationAngle) / mSegmentation;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        /**
         * 显示彩色断大于总共的段数是错误的
         */
        if (mIsShowSegmentation && mRingColorSelect > mSegmentation) {
            return;
        }
        mPaint.setColor(mMaxCircleColor);
        canvas.drawCircle(mViewCenterX, mViewCenterY, mSmallCircleRadius + mRingWidth + 20, mPaint);
        mPaint.setColor(mMinCircleColor);
        canvas.drawCircle(mViewCenterX, mViewCenterY, mSmallCircleRadius, mPaint);
        //画默认圆环
        drawNormalRing(canvas);
        //画彩色圆环
        drawColorRing(canvas);
    }

    /**
     * 画彩色圆环
     *
     * @param canvas
     */
    private void drawColorRing(Canvas canvas) {
        Paint ringColorPaint = new Paint(mPaint);
        ringColorPaint.setStyle(Paint.Style.STROKE);
        ringColorPaint.setStrokeWidth(mRingWidth);
        ringColorPaint.setShader(new SweepGradient(mViewCenterX, mViewCenterX, color, null));

        if (!mIsShowSegmentation) {
            canvas.drawArc(mRectF, 270, mRingColorSelect, false, ringColorPaint);
            return;
        }

        if (mSegmentation == mRingColorSelect && mRingColorSelect != 0 && mSegmentation != 0) {
            canvas.drawArc(mRectF, 270, 360, false, ringColorPaint);
        } else {
            canvas.drawArc(mRectF, 270, mRingAngleWidth * mRingColorSelect + mSegmentationAngle * mRingColorSelect, false, ringColorPaint);
        }

        ringColorPaint.setShader(null);
        ringColorPaint.setColor(mMaxCircleColor);
        for (int i = 0; i < mRingColorSelect; i++) {
            canvas.drawArc(mRectF, 270 + (i * mRingAngleWidth + (i) * mSegmentationAngle), mSegmentationAngle, false, ringColorPaint);
        }
    }

    /**
     * 画默认圆环
     *
     * @param canvas
     */
    private void drawNormalRing(Canvas canvas) {
        Paint ringNormalPaint = new Paint(mPaint);
        ringNormalPaint.setStyle(Paint.Style.STROKE);
        ringNormalPaint.setStrokeWidth(mRingWidth);
        ringNormalPaint.setColor(mRingNormalColor);
        canvas.drawArc(mRectF, 270, 360, false, ringNormalPaint);
        if (!mIsShowSegmentation) {
            return;
        }
        ringNormalPaint.setColor(mMaxCircleColor);
        for (int i = 0; i < mSegmentation; i++) {
            canvas.drawArc(mRectF, 270 + (i * mRingAngleWidth + (i) * mSegmentationAngle), mSegmentationAngle, false, ringNormalPaint);
        }
    }

    /**
     * 显示几段
     *
     * @param i
     */
    public void setSelect(int i) {
        this.mRingColorSelect = i;
        this.invalidate();
    }

    /**
     * 分段的总数
     *
     * @param i
     */
    public void setSelectCount(int i) {
        this.mSegmentation = i;
    }


    /**
     * 是否显示断
     *
     * @param b
     */
    public void setShowSelect(boolean b) {
        this.mIsShowSegmentation = b;
    }

    /**
     * 设置渐变颜色
     * @param color
     */
    public void setColor(int[] color) {
        this.color = color;
    }
}

