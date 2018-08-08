package com.common.repository;

import org.greenrobot.greendao.AbstractDaoSession;

/**
 * 【类功能说明】
 * 数据管理接口类
 * File: IRepositoryManager.java
 * @author longfeng
 * Vesion: 3.2.0
 * Create: 2018/8/8
 * Changes (from 2018/8/8)
 * -------------------------------------------------------
 * 2018/8/8:创建IRepositoryManager.java(longfeng)
 * -------------------------------------------------------
 */
public interface IRepositoryManager<D extends AbstractDaoSession> {
    /**
     * 获得Api
     * @param retrofitClass
     * @param <T>
     * @return
     */
    <T> T getApi(Class<T> retrofitClass);

    /**
     * 清除Api
     * @param retrofitClass
     * @param <T>
     */
    <T> void clearApi(Class<T> retrofitClass);

    /**
     * 获取DaoSession对象操作数据库
     * @return
     */
    D getDaoSession();

    /**
     * 清除全部缓存
     */
    void clearAllCache();
}
