package com.common.repository;

import org.greenrobot.greendao.AbstractDaoSession;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Retrofit;

/**
 * 【类功能说明】
 * 数据操作实现类
 * File: BaseRepositoryManager.java
 *
 * @author longfeng
 * Vesion: 3.2.0
 * Create: 2018/8/8
 * Changes (from 2018/8/8)
 * -------------------------------------------------------
 * 2018/8/8:创建BaseRepositoryManager.java(longfeng)
 * -------------------------------------------------------
 */
public class BaseRepositoryManager<D extends AbstractDaoSession> implements IRepositoryManager<D> {
    protected Retrofit mRetrofit;
    private D mDaoSession;
    private Map<String, Object> mStringRetrofitMap;

    public BaseRepositoryManager(Retrofit retrofit, D abstractDaoSession) {
        this.mRetrofit = retrofit;
        this.mDaoSession = abstractDaoSession;
        mStringRetrofitMap = new HashMap<>();
    }

    @Override
    public <T> T getApi(Class<T> retrofitClass) {
        T result = null;
        if (mStringRetrofitMap != null) {
            synchronized (mStringRetrofitMap) {
                result = (T) mStringRetrofitMap.get(retrofitClass.getName());
                if (result == null) {
                    result = mRetrofit.create(retrofitClass);
                    mStringRetrofitMap.put(retrofitClass.getName(), result);
                }
            }
        }
        return result;
    }

    @Override
    public void clearApi(Class retrofitClass) {
        if (mStringRetrofitMap != null) {
            synchronized (mStringRetrofitMap) {
                if (mStringRetrofitMap.containsKey(retrofitClass.getName())) {
                    mStringRetrofitMap.remove(retrofitClass.getName());
                }
            }
        }
    }

    @Override
    public D getDaoSession() {
        return mDaoSession;
    }

    @Override
    public void clearAllCache() {
        if (mStringRetrofitMap != null) {
            synchronized (mStringRetrofitMap) {
                mStringRetrofitMap.clear();
            }
        }
    }
}
