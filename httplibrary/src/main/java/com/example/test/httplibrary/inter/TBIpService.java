package com.example.test.httplibrary.inter;

import com.example.test.httplibrary.bean.TBRoot;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * 【类功能说明】
 * ...
 * File: TBIpService.java
 * Author: longfeng
 * Vesion: 3.2.0
 * Create: 2018/7/13
 * Changes (from 2018/7/13)
 * -------------------------------------------------------
 * 2018/7/13:创建TBIpService.java(longfeng)
 * -------------------------------------------------------
 */
public interface TBIpService {
    /**
     * GET方法请求淘宝ip地址库
     * @param ip
     * @return
     */
    @GET("getIpInfo.php")
    Call<TBRoot> getTBIpMsg(@Query("ip")String ip);
}
