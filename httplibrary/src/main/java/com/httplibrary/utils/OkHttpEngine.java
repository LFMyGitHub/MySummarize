package com.httplibrary.utils;

import android.content.Context;
import android.os.Handler;

import com.httplibrary.inter.ResultCallback;
import com.squareup.okhttp.Cache;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * 【类功能说明】
 * 封装OkHttp2.x
 * File: OkHttpEngine.java
 * @author longfeng
 * Vesion: 3.2.0
 * Create: 2018/7/12
 * Changes (from 2018/7/12)
 * -------------------------------------------------------
 * 2018/7/12:创建OkHttpEngine.java(longfeng)
 * -------------------------------------------------------
 */
public class OkHttpEngine {
    private volatile static OkHttpEngine mInstance;
    private OkHttpClient mOkHttpClient;
    private Handler mHandler;

    /**
     * 双重检查单例模式
     * @return
     */
    public static OkHttpEngine getInstance() {
        if (mInstance == null) {
            synchronized (OkHttpEngine.class) {
                if (mInstance == null) {
                    mInstance = new OkHttpEngine();
                }
            }
        }
        return mInstance;
    }

    private OkHttpEngine() {
        mOkHttpClient = new OkHttpClient();
        mOkHttpClient.setConnectTimeout(15, TimeUnit.SECONDS);
        mOkHttpClient.setWriteTimeout(20, TimeUnit.SECONDS);
        mOkHttpClient.setReadTimeout(20, TimeUnit.SECONDS);
        mHandler = new Handler();
    }

    public OkHttpEngine setCache(Context mContext) {
        File sdcache = mContext.getExternalCacheDir();
        int cacheSize = 10 * 1024 * 1024;
        mOkHttpClient.setCache(new Cache(sdcache.getAbsoluteFile(), cacheSize));
        return mInstance;
    }

    /**
     * 异步get请求
     * @param url
     * @param callback
     */
    public void getAsynHttp(String url, ResultCallback callback) {
        final Request request = new Request.Builder()
                .url(url)
                .build();
        Call call = mOkHttpClient.newCall(request);
        dealResult(call, callback);
    }

    private void dealResult(Call call, final ResultCallback callback) {
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                sendFailedCallback(request, e, callback);
            }
            @Override
            public void onResponse(final Response response) throws IOException {
                sendSuccessCallback(response, callback);
            }
            private void sendSuccessCallback(final Response object, final ResultCallback callback) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (callback != null) {
                            callback.onResponse(object);
                        }
                    }
                });
            }
            private void sendFailedCallback(final Request request, final Exception e, final ResultCallback callback) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (callback != null){
                            callback.onError(request, e);
                        }
                    }
                });
            }
        });
    }
}
