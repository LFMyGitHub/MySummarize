package com.example.test.httplibrary.utils;

import android.util.Log;

import com.example.test.httplibrary.bean.TBRoot;
import com.example.test.httplibrary.inter.TBIpService;
import com.google.gson.GsonBuilder;

import java.text.DateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * 【类功能说明】
 * ...
 * File: RetrofixUtils.java
 *
 * @author longfeng
 * Vesion: 3.2.0
 * Create: 2018/7/13
 * Changes (from 2018/7/13)
 * -------------------------------------------------------
 * 2018/7/13:创建RetrofixUtils.java(longfeng)
 * -------------------------------------------------------
 */
public class RetrofixUtils {

    /**
     * 根据IP地址获取淘宝ip库
     *
     * @param ip
     */
    public static void getTBIpInfomation(String ip) {
        String url = "http://ip.taobao.com/service/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                //增加返回值为String的支持
                .addConverterFactory(ScalarsConverterFactory.create())
                //解决Gson解析Date数据格式的问题
                .addConverterFactory(GsonConverterFactory.create(
                        new GsonBuilder().registerTypeAdapter(Date.class, new MyDateDeserializer())
                                .setDateFormat(DateFormat.LONG).create())
                )
                .build();
        TBIpService tbIpService = retrofit.create(TBIpService.class);
        Call<TBRoot> call = tbIpService.getTBIpMsg(ip);
        call.enqueue(new Callback<TBRoot>() {
            @Override
            public void onResponse(Call<TBRoot> call, Response<TBRoot> response) {
                String country = response.body().getData().getCountry();
                Log.i("TAG", "Retrofix请求淘宝ip库成功：" + country);
            }

            @Override
            public void onFailure(Call<TBRoot> call, Throwable t) {
                Log.i("TAG", "Retrofix请求淘宝ip库失败：" + t.getMessage().toString());
            }
        });
    }
}
