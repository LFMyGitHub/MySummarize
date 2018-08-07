package com.httplibrary.utils;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

/**
 * 【类功能说明】
 * ...
 * File: VolleyUtils.java
 *
 * @author longfeng
 * Vesion: 3.2.0
 * Create: 2018/7/12
 * Changes (from 2018/7/12)
 * -------------------------------------------------------
 * 2018/7/12:创建VolleyUtils.java(longfeng)
 * -------------------------------------------------------
 */
public class VolleyUtils {

    /**
     * 创建网络请求
     *
     * @param context
     */
    public static void creatRequestQueue(Context context, String path) {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        /**
         * StringRequest：返回String
         * JsonRequest：返回json
         * ImageRequest：返回图片
         * ImageLoader：内部使用ImageRequest实现，构造器可以传入一个ImageCache缓存形参，
         * 实现了图片缓存功能，同时还可以过滤重复链接，避免重复请求。(会先显示默认图片)
         */
        StringRequest stringRequest = new StringRequest(Request.Method.GET, path,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i("TAG", "Volley请求：" + response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("TAG", error.getMessage(), error);
            }
        });
        //将请求添加到队列中
        requestQueue.add(stringRequest);
    }
}
