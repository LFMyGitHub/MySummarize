package com.example.test.mysummarize;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;
import com.example.test.httplibrary.inter.ResultCallback;
import com.example.test.httplibrary.utils.HttpClientUtils;
import com.example.test.httplibrary.utils.HttpURLConnecionUtils;
import com.example.test.httplibrary.utils.OkHttp2Utils;
import com.example.test.httplibrary.utils.OkHttp3Utils;
import com.example.test.httplibrary.utils.OkHttpEngine;
import com.example.test.httplibrary.utils.RetrofixUtils;
import com.example.test.httplibrary.utils.VolleyUtils;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        HttpClientUtils.useHttpClientGetThread("http://www.people.com.cn/");
        HttpURLConnecionUtils.sendRequestWithHttpURLConnection("http://apis.baidu.com/txapi/weixin/wxhot?num=10&page=1&word=%E7%9B%97%E5%A2%93%E7%AC%94%E8%AE%B0");
        VolleyUtils.creatRequestQueue(this, "http://www.people.com.cn/");

        RequestQueue mQueue = Volley.newRequestQueue(getApplicationContext());
        ImageLoader imageLoader = new ImageLoader(mQueue, new ImageLoader.ImageCache() {
            @Override
            public Bitmap getBitmap(String url) {
                return null;
            }

            @Override
            public void putBitmap(String url, Bitmap bitmap) {

            }
        });
        NetworkImageView nv_image = findViewById(R.id.nv_image);
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
        OkHttp2Utils.getAsynHttpCache(this, "http://www.people.com.cn/");
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
