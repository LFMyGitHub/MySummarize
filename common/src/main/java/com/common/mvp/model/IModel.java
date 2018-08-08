package com.common.mvp.model;

import com.common.R;

/**
 * 【类功能说明】
 * ...
 * File: IModel.java
 * @author longfeng
 * Vesion: 3.2.0
 * Create: 2018/8/8
 * Changes (from 2018/8/8)
 * -------------------------------------------------------
 * 2018/8/8:创建IModel.java(longfeng)
 * -------------------------------------------------------
 */
public interface IModel<R> {

    /**
     * 界面销毁
     */
    void onDestroy();

    /**
     * 获得数据管理对象
     * @return
     */
    R getRespositoryManager();
}
