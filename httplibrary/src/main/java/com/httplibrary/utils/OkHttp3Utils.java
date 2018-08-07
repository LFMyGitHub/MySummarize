package com.httplibrary.utils;

import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * 【类功能说明】
 * ...
 * File: OkHttp3Utils.java
 * @author longfeng
 * Vesion: 3.2.0
 * Create: 2018/7/12
 * Changes (from 2018/7/12)
 * -------------------------------------------------------
 * 2018/7/12:创建OkHttp3Utils.java(longfeng)
 * -------------------------------------------------------
 */
public class OkHttp3Utils {

    /**
     * 异步GET请求
     * @param path
     */
    public static void getAsynHttp(String path) {
        OkHttpClient mOkHttpClient = new OkHttpClient();
        Request.Builder requestBuilder = new Request.Builder().url(path);
        //可以省略，默认是GET请求
        requestBuilder.method("GET", null);
        Request request = requestBuilder.build();
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (null != response.cacheResponse()) {
                    String str = response.cacheResponse().toString();
                    Log.i("TAG", "OkHttp3 Cache" + str);
                } else {
                    response.body().string();
                    String str = response.networkResponse().toString();
                    Log.i("TAG", "OkHttp3 Network" + str);
                }
            }
        });
    }

    /**
     * 异步POST请求
     * @param path
     */
    private void postAsynHttp(String path) {
        OkHttpClient mOkHttpClient=new OkHttpClient();
        RequestBody formBody = new FormBody.Builder()
                .add("size", "10")
                .build();
        Request request = new Request.Builder()
                .url(path)
                .post(formBody)
                .build();
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String str = response.body().string();
                Log.i("TAG", str);
            }
        });
    }

    public static final MediaType MEDIA_TYPE_MARKDOWN = MediaType.parse("text/x-markdown; charset=utf-8");

    /**
     * 异步上传文件
     * 同步上传文件：mOkHttpClient.newCall(request).execute()
     * @param path
     */
    public static void postAsynFile(String path) {
        OkHttpClient mOkHttpClient=new OkHttpClient();
        File file = new File("/sdcard/upload.txt");
        Request request = new Request.Builder()
                .url(path)
                .post(RequestBody.create(MEDIA_TYPE_MARKDOWN, file))
                .build();
        mOkHttpClient.sslSocketFactory();
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                //好像是https证书问题
                Log.i("TAG","OkHttp3异步上传文件失败"+e.getMessage().toString());
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.i("TAG","OkHttp3异步上传文件成功"+response.body().string());
            }
        });
    }

    /**
     * 异步下载文件
     * @param path
     */
    public static void downAsynFile(String path) {
        OkHttpClient mOkHttpClient = new OkHttpClient();
        String url = path;
        Request request = new Request.Builder().url(url).build();
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }
            @Override
            public void onResponse(Call call, Response response) {
                InputStream inputStream = response.body().byteStream();
                FileOutputStream fileOutputStream = null;
                try {
                    fileOutputStream = new FileOutputStream(new File("/sdcard/download.jpg"));
                    byte[] buffer = new byte[2048];
                    int len = 0;
                    while ((len = inputStream.read(buffer)) != -1) {
                        fileOutputStream.write(buffer, 0, len);
                    }
                    fileOutputStream.flush();
                    Log.d("TAG", "OkHttp3文件下载成功");
                } catch (IOException e) {
                    Log.i("TAG", "OkHttp3 IOException");
                    e.printStackTrace();
                }
            }
        });
    }


    private static final MediaType MEDIA_TYPE_PNG = MediaType.parse("image/png");

    /**
     * 异步上传Multipart文件
     * 上传文件同时还需要传其他类型的字段
     * @param path
     */
    private void sendMultipart(String path){
        OkHttpClient mOkHttpClient = new OkHttpClient();
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("title", "upload")
                .addFormDataPart("image", "download.jpg",
                        RequestBody.create(MEDIA_TYPE_PNG, new File("/sdcard/download.jpg")))
                .build();
        Request request = new Request.Builder()
                .header("Authorization", "Client-ID " + "...")
                .url("https://api.imgur.com/3/image")
                .post(requestBody)
                .build();
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.i("TAG", response.body().string());
            }
        });
    }
}
