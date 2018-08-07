package com.mysummarize.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.Nullable;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;
import com.httplibrary.inter.ResultCallback;
import com.httplibrary.utils.HttpClientUtils;
import com.httplibrary.utils.HttpURLConnecionUtils;
import com.httplibrary.utils.OkHttp2Utils;
import com.httplibrary.utils.OkHttp3Utils;
import com.httplibrary.utils.OkHttpEngine;
import com.httplibrary.utils.RetrofixUtils;
import com.httplibrary.utils.VolleyUtils;
import com.mysummarize.R;
import com.mysummarize.R;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

/**
 * @author Administrator
 * @创建时间 2018 07 2018/7/16/016 19:47
 * @描述
 */
public class TestUtils {

    /**
     * 网络请求测试
     * @param context
     * @param nv_image
     */
    public static void testHttp(Context context, @Nullable NetworkImageView nv_image){
        HttpClientUtils.useHttpClientGetThread("http://www.people.com.cn/");
        HttpURLConnecionUtils.sendRequestWithHttpURLConnection("http://apis.baidu.com/txapi/weixin/wxhot?num=10&page=1&word=%E7%9B%97%E5%A2%93%E7%AC%94%E8%AE%B0");
        VolleyUtils.creatRequestQueue(context, "http://www.people.com.cn/");

        RequestQueue mQueue = Volley.newRequestQueue(context);
        ImageLoader imageLoader = new ImageLoader(mQueue, new ImageLoader.ImageCache() {
            @Override
            public Bitmap getBitmap(String url) {
                return null;
            }

            @Override
            public void putBitmap(String url, Bitmap bitmap) {

            }
        });

        nv_image.setDefaultImageResId(R.drawable.ic_launcher_foreground);
        nv_image.setErrorImageResId(R.drawable.ic_launcher_background);
        nv_image.setImageUrl("http://img5.mtime.cn/mg/2016/12/26/164311.99230575.jpg",
                imageLoader);

        OkHttp2Utils.getAsynHttp("http://www.people.com.cn/");
        try{
            //主线程中不要进行网络请求操作，需另开线程
            OkHttp2Utils.getSyncHttp("http://www.people.com.cn/");
        }catch (Exception e){
            e.printStackTrace();
        }
        OkHttp2Utils.getAsynHttpCache(context, "http://www.people.com.cn/");
        OkHttp2Utils.cancel("http://www.people.com.cn/");
        OkHttpEngine.getInstance().getAsynHttp("http://www.people.com.cn/", new ResultCallback() {
            @Override
            public void onError(Request request, Exception e) {
            }

            @Override
            public void onResponse(Response response) {
                Log.i("TAG", "OkHttpEngine请求成功");
            }
        });
        OkHttp3Utils.getAsynHttp("http://www.people.com.cn/");
        OkHttp3Utils.postAsynFile("https://github.com/LFMyGitHub/MySummarize/tree/master/raw");
        OkHttp3Utils.downAsynFile("http://img5.mtime.cn/mg/2016/12/26/164311.99230575.jpg");

        RetrofixUtils.getTBIpInfomation("116.236.185.64");
    }
}
