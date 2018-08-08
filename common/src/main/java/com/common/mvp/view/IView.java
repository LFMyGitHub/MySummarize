package com.common.mvp.view;

import com.common.baseadapter.empty.EmptyLayout;
import com.trello.rxlifecycle2.LifecycleTransformer;

/**
 * 【类功能说明】
 * ...
 * File: IView.java
 * @author longfeng
 * Vesion: 3.2.0
 * Create: 2018/8/8
 * Changes (from 2018/8/8)
 * -------------------------------------------------------
 * 2018/8/8:创建IView.java(longfeng)
 * -------------------------------------------------------
 */
public interface IView<T> {
    /**
     * 显示加载
     * @param loadMessage
     */
    void showLoading(String loadMessage);

    /**
     * 隐藏加载
     */
    void hideLoading();

    /**
     * 显示信息(网络加载失败/无数据/正在加载)
     * @param message
     * @param listener
     */
    void showError(String message, EmptyLayout.OnRetryListener listener);

    /**
     * 展示空布局
     */
    void showEmptyView();

    /**
     * 更新数据
     * @param t
     */
    void updateData(T t);

    /**
     * 绑定事件
     * @param <Y>
     * @return
     */
    <Y> LifecycleTransformer<Y> bindLife();
}
