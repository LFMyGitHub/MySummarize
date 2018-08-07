package com.httplibrary.utils;

import android.text.TextUtils;
import android.util.Log;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * 【类功能说明】
 * ...
 * File: HttpURLConnecionUtils.java
 *
 * @author longfeng
 * Vesion: 3.2.0
 * Create: 2018/7/11
 * Changes (from 2018/7/11)
 * -------------------------------------------------------
 * 2018/7/11:创建HttpURLConnecionUtils.java(longfeng)
 * -------------------------------------------------------
 */
public class HttpURLConnecionUtils {
    public HttpURLConnection getHttpURLConnection(String url) {
        HttpURLConnection httpURLConnection = null;
        try {
            URL url1 = new URL(url);
            httpURLConnection = (HttpURLConnection) url1.openConnection();
            //设置链接超时时间
            httpURLConnection.setConnectTimeout(15000);
            //设置读取超时时间
            httpURLConnection.setReadTimeout(15000);
            //设置请求参数
            httpURLConnection.setRequestMethod("POST");
            //添加Header
            httpURLConnection.setRequestProperty("Connection", "Keep-Alive");
            //接受输入流
            httpURLConnection.setDoInput(true);
            //传递参数时需要开启
            httpURLConnection.setDoOutput(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return httpURLConnection;
    }

    public static void postParams(OutputStream outputStream, List<NameValuePair> pairList) throws IOException {
        StringBuffer stringBuffer = new StringBuffer();
        for (NameValuePair nameValuePair : pairList) {
            if (!TextUtils.isEmpty(stringBuffer)) {
                stringBuffer.append("&");
            }
            stringBuffer.append(URLEncoder.encode(nameValuePair.getName(), "UTF-8"));
            stringBuffer.append("=");
            stringBuffer.append(URLEncoder.encode(nameValuePair.getValue(), "UTF-8"));
        }
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
        bufferedWriter.write(stringBuffer.toString());
        bufferedWriter.flush();
        bufferedWriter.close();
    }

    private void useHttpURLConnectionPost(String url) {
        InputStream inputStream = null;
        HttpURLConnection httpURLConnection = getHttpURLConnection(url);
        try {
            List<NameValuePair> nameValuePairList = new ArrayList<>();
            //要传递的参数
            nameValuePairList.add(new BasicNameValuePair("username", "jack"));
            nameValuePairList.add(new BasicNameValuePair("password", "1234"));
            postParams(httpURLConnection.getOutputStream(), nameValuePairList);
            httpURLConnection.connect();
            inputStream = httpURLConnection.getInputStream();
            int code = httpURLConnection.getResponseCode();
            String respose = converStreamToString(inputStream);
            Log.i("TAG", "HttpURLConnection请求码状态：" + code + "\n请求码结果：\n" + respose);
            inputStream.close();
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

    public static void sendRequestWithHttpURLConnection(final String path) {
        //开启线程来发起网络请求
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                BufferedReader reader = null;
                try {
                    URL url = new URL(path);
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    InputStream in = connection.getInputStream();
                    //下面对获取到的输入流进行读取
                    reader = new BufferedReader(new InputStreamReader(in));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    Log.i("TAG", "HttpURLConnection请求：" + response.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (reader != null) {
                        try {
                            reader.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (connection != null) {
                        connection.disconnect();
                    }
                }
            }
        }).start();
    }

    public void useHttpUrlConnectionGetThread() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                useHttpURLConnectionPost("http://www.baidu.com");
            }
        }).start();
    }
}
