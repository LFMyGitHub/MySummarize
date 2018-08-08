package com.common.mvp.presenter;

import com.common.mvp.model.BaseModel;
import com.common.mvp.view.IView;
import com.common.rxbus.RxBusManager;
import com.common.utils.CommonLogger;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * 【类功能说明】
 * ...
 * File: RxBasePresenter.java
 *
 * @author longfeng
 * Vesion: 3.2.0
 * Create: 2018/8/8
 * Changes (from 2018/8/8)
 * -------------------------------------------------------
 * 2018/8/8:创建RxBasePresenter.java(longfeng)
 * -------------------------------------------------------
 */
public class RxBasePresenter<V extends IView, M extends BaseModel> extends BasePresenter<V, M> {

    public RxBasePresenter(V iView, M baseModel) {
        super(iView, baseModel);
    }

    public <T> void registerEvent(Class<T> type, Consumer<T> consumer) {
        Disposable disposable = RxBusManager.getInstance().registerEvent(type, consumer, new Consumer<Throwable>() {
            @Override
            public void accept(@NonNull Throwable throwable) throws Exception {
                CommonLogger.e("rx事件出错啦" + throwable.getMessage());
            }
        });
        addDispose(disposable);
    }
}
