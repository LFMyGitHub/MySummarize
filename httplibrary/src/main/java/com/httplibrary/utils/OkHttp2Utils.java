package com.httplibrary.utils;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.squareup.okhttp.Cache;
import com.squareup.okhttp.CacheControl;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 【类功能说明】
 * ...
 * File: OkHttp2Utils.java
 * @author longfeng
 * Vesion: 3.2.0
 * Create: 2018/7/12
 * Changes (from 2018/7/12)
 * -------------------------------------------------------
 * 2018/7/12:创建OkHttp2Utils.java(longfeng)
 * -------------------------------------------------------
 */
public class OkHttp2Utils {
    /**
     * 异步GET请求
     * @param path
     */
    public static void getAsynHttp(String path){
        //创建OkHttpClient对象
        OkHttpClient okHttpClient = new OkHttpClient();
        final Request request = new Request.Builder().url(path).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
            }

            @Override
            public void onResponse(Response response) throws IOException {
                String str = response.body().string();
                Log.i("TAG", "异步GET请求"+str);
            }
        });
    }

    /**
     * 同步GET请求
     * @param path
     */
    public static void getSyncHttp(String path) throws IOException {
        //创建OkHttpClient对象
        OkHttpClient okHttpClient = new OkHttpClient();
        final Request request = new Request.Builder().url(path).build();
        Call call = okHttpClient.newCall(request);
        Response response = call.execute();
        if (response.isSuccessful()){
            Log.i("TAG","同步GET请求");
        }else{
            throw new IOException("同步GET请求IOException"+response);
        }
    }

    /**
     * 异步POST请求
     * @param path
     */
    public static void postAsynHttp(String path) {
        OkHttpClient mOkHttpClient = new OkHttpClient();
        RequestBody formBody = new FormEncodingBuilder()
                .add("size", "10")
                .build();
        Request request = new Request.Builder()
                .url(path)
                .post(formBody)
                .build();
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
            }
            @Override
            public void onResponse(Response response) throws IOException {
                String str = response.body().string();
                Log.i("TAG", str);
            }
        });
    }

    /**
     * 请求缓存设置
     * 异步GET请求
     * @param path
     */
    public static void getAsynHttpCache(Context context, String path){
        //创建OkHttpClient对象
        OkHttpClient okHttpClient = new OkHttpClient();
        File file = context.getExternalCacheDir();
        int cacheSize = 10 * 1024 * 1024;
        okHttpClient.setCache(new Cache(file.getAbsoluteFile(), cacheSize));
        final Request request = new Request.Builder().url(path).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
            }

            @Override
            public void onResponse(Response response) throws IOException {
                if (null != response.cacheResponse()) {
                    String str = response.cacheResponse().toString();
                    Log.i("TAG", "缓存不为空" + str);
                } else {
                    response.body().string();
                    String str=response.networkResponse().toString();
                    Log.i("TAG", "缓存为空" + str);
                }
            }
        });
    }

    public static ScheduledExecutorService executor = new ScheduledThreadPoolExecutor(1);
    /**
     * 取消请求
     * @param path
     */
    public static void cancel(String path){
        OkHttpClient okHttpClient = new OkHttpClient();
        final Request request = new Request.Builder()
                .url(path)
                .cacheControl(CacheControl.FORCE_NETWORK)
                .build();
        Call call=null;
        call = okHttpClient.newCall(request);
        final Call finalCall = call;
        //100毫秒后取消call
        executor.schedule(new Runnable() {
            @Override public void run() {
                finalCall.cancel();
            }
        }, 1, TimeUnit.MILLISECONDS);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
            }
            @Override
            public void onResponse(final Response response) {
                if (null != response.cacheResponse()) {
                    String str = response.cacheResponse().toString();
                    Log.i("TAG", "cache---" + str);
                } else {
                    try {
                        response.body().string();
                    } catch (IOException e) {
                        Log.i("TAG", "IOException");
                        e.printStackTrace();
                    }
                    String str = response.networkResponse().toString();
                    Log.i("TAG", "network---" + str);
                }
            }
        });
        Log.i("TAG", "是否取消成功"+call.isCanceled());
    }
}
