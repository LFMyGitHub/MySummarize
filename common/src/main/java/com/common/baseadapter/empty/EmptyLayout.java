package com.common.baseadapter.empty;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.common.R;

/**
 * 【类功能说明】
 * 网络加载失败/无数据/正在加载页面
 * File: EmptyLayout.java
 *
 * @author longfeng
 * Vesion: 3.2.0
 * Create: 2018/8/8
 * Changes (from 2018/8/8)
 * -------------------------------------------------------
 * 2018/8/8:创建EmptyLayout.java(longfeng)
 * -------------------------------------------------------
 */
public class EmptyLayout extends FrameLayout implements View.OnClickListener {
    public static final int STATUS_LOADING = 0;
    public static final int STATUS_NO_NET = 2;
    public static final int STATUS_NO_DATA = 3;
    public static final int STATUS_HIDE = 4;
    private int CURRENT_STATUS = STATUS_HIDE;

    private RelativeLayout mErrorLayout, mEmptyLayout, mLoadingLayout;
    private ImageView mLoadingImage;
    private View mContentView;

    public EmptyLayout(@NonNull Context context) {
        this(context, null);
    }

    public EmptyLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public EmptyLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs);
    }

    private void initView(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.empty_layout);
        View view = View.inflate(context, R.layout.empty_layout, this);
        mErrorLayout = (RelativeLayout) view.findViewById(R.id.rl_empty_layout_error);
        mEmptyLayout = (RelativeLayout) view.findViewById(R.id.rl_empty_layout_empty);
        mLoadingLayout = (RelativeLayout) view.findViewById(R.id.rl_empty_layout_loading);
        mLoadingImage = (ImageView) view.findViewById(R.id.iv_empty_loading_image);
        view.findViewById(R.id.rl_empty_layout_error).setOnClickListener(this);
        view.findViewById(R.id.rl_empty_layout_empty).setOnClickListener(this);
        typedArray.recycle();
        updateViewVisible();
        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });
    }

    private void updateViewVisible() {
        if (CURRENT_STATUS != STATUS_HIDE) {
            if (getVisibility() == GONE) {
                setVisibility(VISIBLE);
            }
        } else {
            if (getVisibility() == VISIBLE) {
                setVisibility(GONE);
                if (mContentView != null) {
                    mContentView.setVisibility(VISIBLE);
                }
            }
            return;
        }
        switch (CURRENT_STATUS) {
            case STATUS_HIDE:
                mContentView.setVisibility(VISIBLE);
                break;
            case STATUS_NO_NET:
                mErrorLayout.setVisibility(VISIBLE);
                mLoadingLayout.setVisibility(GONE);
                mEmptyLayout.setVisibility(GONE);
                mContentView.setVisibility(GONE);
                break;
            case STATUS_NO_DATA:
                mErrorLayout.setVisibility(GONE);
                mLoadingLayout.setVisibility(GONE);
                mEmptyLayout.setVisibility(VISIBLE);
                mContentView.setVisibility(GONE);
                break;
            case STATUS_LOADING:
                Glide.with(getContext()).load(R.drawable.loading_animation).into(mLoadingImage);
                mErrorLayout.setVisibility(GONE);
                mEmptyLayout.setVisibility(GONE);
                mLoadingLayout.setVisibility(VISIBLE);
                mContentView.setVisibility(GONE);
                break;
            default:
                break;
        }
    }

    public int getCurrentStatus() {
        return CURRENT_STATUS;
    }

    public void setCurrentStatus(int currentStatus) {
        this.CURRENT_STATUS = currentStatus;
        updateViewVisible();
    }

    @Override
    public void onClick(View view) {
        if (mOnRetryListener != null) {
            mOnRetryListener.onRetry();
        }
    }

    public void setContentView(View view) {
        this.mContentView = view;
    }

    private OnRetryListener mOnRetryListener;

    public interface OnRetryListener {
        void onRetry();
    }

    public void setOnRetryListener(OnRetryListener onRetryListener) {
        this.mOnRetryListener = onRetryListener;
    }
}
