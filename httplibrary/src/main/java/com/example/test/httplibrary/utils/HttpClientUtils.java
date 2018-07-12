package com.example.test.httplibrary.utils;

import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.w3c.dom.Entity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * 【类功能说明】
 * ...
 * File: HttpClientUtils.java
 *
 * @author longfeng
 * Vesion: 3.2.0
 * Create: 2018/7/11
 * Changes (from 2018/7/11)
 * -------------------------------------------------------
 * 2018/7/11:创建HttpClientUtils.java(longfeng)
 * -------------------------------------------------------
 */
public class HttpClientUtils {

    /**
     * 创建HttpClient
     *
     * @return
     */
    private HttpClient creatHttpClient() {
        HttpParams httpParams = new BasicHttpParams();
        //设置连接超时
        HttpConnectionParams.setConnectionTimeout(httpParams, 15000);
        //设置请求超时
        HttpConnectionParams.setSoTimeout(httpParams, 15000);
        HttpConnectionParams.setTcpNoDelay(httpParams, true);
        HttpProtocolParams.setVersion(httpParams, HttpVersion.HTTP_1_1);
        HttpProtocolParams.setContentCharset(httpParams, HTTP.UTF_8);
        //持续握手
        HttpProtocolParams.setUseExpectContinue(httpParams, true);
        HttpClient httpClient = new DefaultHttpClient(httpParams);
        return httpClient;
    }

    /**
     * HttpClient请求url
     *
     * @param url
     */
    public void useHttpClientGet(String url) {
        HttpGet httpGet = new HttpGet(url);
        httpGet.addHeader("Connection", "Keep-Alive");
        try {
            HttpClient httpClient = creatHttpClient();
            HttpResponse httpResponse = httpClient.execute(httpGet);
            HttpEntity httpEntity = httpResponse.getEntity();
            int code = httpResponse.getStatusLine().getStatusCode();
            if (null != httpEntity) {
                InputStream inputStream = httpEntity.getContent();
                String respose = converStreamToString(inputStream);
                Log.i("TAG","HttpClient请求码状态：" + code + "\n请求码结果：\n" + respose);
                inputStream.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String converStreamToString(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuffer stringBuffer = new StringBuffer();
        String line = null;
        String respose = stringBuffer.toString();
        while ((line = bufferedReader.readLine()) != null) {
            stringBuffer.append(line + "\n");
        }
        return respose;
    }

    private void useHttpClientPost(String url) {
        HttpPost mHttpPost = new HttpPost(url);
        mHttpPost.addHeader("Connection", "Keep-Alive");
        try {
            HttpClient mHttpClient = creatHttpClient();
            List<NameValuePair> postParams = new ArrayList<>();
            //要传递的参数
            postParams.add(new BasicNameValuePair("username", "moon"));
            postParams.add(new BasicNameValuePair("password", "123"));
            mHttpPost.setEntity(new UrlEncodedFormEntity(postParams));
            HttpResponse mHttpResponse = mHttpClient.execute(mHttpPost);
            HttpEntity mHttpEntity = mHttpResponse.getEntity();
            int code = mHttpResponse.getStatusLine().getStatusCode();
            if (null != mHttpEntity) {
                InputStream mInputStream = mHttpEntity.getContent();
                String respose = converStreamToString(mInputStream);
                Log.i("TAG","请求状态码:" + code + "\n请求结果:\n" + respose);
                mInputStream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void useHttpClientGetThread() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpClientUtils httpClientUtils = new HttpClientUtils();
                httpClientUtils.useHttpClientGet("http://www.people.com.cn/");
            }
        }).start();
    }
}
