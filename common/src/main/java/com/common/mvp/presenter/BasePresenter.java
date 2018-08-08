package com.common.mvp.presenter;

import com.common.mvp.model.BaseModel;
import com.common.mvp.view.IView;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * 【类功能说明】
 * ...
 * File: BasePresenter.java
 * @author longfeng
 * Vesion: 3.2.0
 * Create: 2018/8/8
 * Changes (from 2018/8/8)
 * -------------------------------------------------------
 * 2018/8/8:创建BasePresenter.java(longfeng)
 * -------------------------------------------------------
 */
public class BasePresenter<V extends IView, M extends BaseModel> implements IPresenter{
    private CompositeDisposable mCompositeDisposable;
    protected V mIView;
    protected M mBaseModel;

    public BasePresenter(V iView, M baseModel) {
        this.mIView = iView;
        this.mBaseModel = baseModel;
    }

    public M getBaseModel() {
        return mBaseModel;
    }

    /**
     * 保证activity结束时取消所有正在执行的订阅
     */
    public void unDispose() {
        if (mCompositeDisposable != null) {
            if (!mCompositeDisposable.isDisposed()) {
                mCompositeDisposable.dispose();
            }
            mCompositeDisposable.clear();
        }
    }

    public CompositeDisposable getCompositeDisposable() {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        return mCompositeDisposable;
    }

    /**
     * 将所有disposable放入,集中处理
     * @param disposable
     */
    protected void addDispose(Disposable disposable) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(disposable);
    }

    @Override
    public void onDestroy() {
        unDispose();
        if(mIView != null){
            mIView = null;
        }
        if(mBaseModel != null){
            mBaseModel.onDestroy();
        }
        this.mCompositeDisposable = null;
    }
}
