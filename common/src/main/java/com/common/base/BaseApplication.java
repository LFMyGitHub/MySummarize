package com.common.base;

import android.app.Application;
import android.util.Log;

import com.alibaba.android.arouter.launcher.ARouter;
import com.common.utils.UIUtils;

/**
 * 【类功能说明】
 * ...
 * File: BaseApplication.java
 *
 * @author longfeng
 * Vesion: 3.2.0
 * Create: 2018/8/6
 * Changes (from 2018/8/6)
 * -------------------------------------------------------
 * 2018/8/6:创建BaseApplication.java(longfeng)
 * -------------------------------------------------------
 */
public class BaseApplication extends Application {
    private static BaseApplication mBaseApplication;

    public static BaseApplication getInstance() {
        return mBaseApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mBaseApplication = this;
        initRouter(mBaseApplication);
    }

    /**
     * 初始化Router
     * @param baseApplication
     */
    private void initRouter(BaseApplication baseApplication){
        Log.e("TAG","初始化Route");
        if(UIUtils.idApkInDebug(baseApplication)){
            Log.e("TAG","Debug模式");
            ARouter.openLog();
            ARouter.openDebug();
        }
        ARouter.init(baseApplication);
    }
}
