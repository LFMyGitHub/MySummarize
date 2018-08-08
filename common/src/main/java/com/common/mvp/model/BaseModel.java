package com.common.mvp.model;

import com.common.repository.BaseRepositoryManager;

/**
 * 【类功能说明】
 * ...
 * File: BaseModel.java
 * @author longfeng
 * Vesion: 3.2.0
 * Create: 2018/8/8
 * Changes (from 2018/8/8)
 * -------------------------------------------------------
 * 2018/8/8:创建BaseModel.java(longfeng)
 * -------------------------------------------------------
 */
public class BaseModel<R extends BaseRepositoryManager> implements IModel<R> {
    protected R mBaseRepositoryManager;

    public BaseModel(R baseRepositoryManager){
        this.mBaseRepositoryManager = baseRepositoryManager;
    }

    @Override
    public void onDestroy() {
        mBaseRepositoryManager.clearAllCache();
    }

    @Override
    public R getRespositoryManager() {
        return mBaseRepositoryManager;
    }
}
