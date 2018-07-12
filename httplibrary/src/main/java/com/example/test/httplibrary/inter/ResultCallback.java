package com.example.test.httplibrary.inter;

import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

/**
 * 【类功能说明】
 * ...
 * File: ResultCallback.java
 * @author longfeng
 * Vesion: 3.2.0
 * Create: 2018/7/12
 * Changes (from 2018/7/12)
 * -------------------------------------------------------
 * 2018/7/12:创建ResultCallback.java(longfeng)
 * -------------------------------------------------------
 */
public abstract class ResultCallback<T> {
    public abstract void onError(Request request, Exception e);
    public abstract void onResponse(Response response);
}
