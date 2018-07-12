package com.example.test.mysummarize;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.test.httplibrary.utils.HttpClientUtils;
import com.example.test.httplibrary.utils.HttpURLConnecionUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        HttpClientUtils httpClientUtils = new HttpClientUtils();
        httpClientUtils.useHttpClientGetThread();

        HttpURLConnecionUtils httpURLConnecionUtils = new HttpURLConnecionUtils();
        //httpURLConnecionUtils.useHttpUrlConnectionGetThread();
        httpURLConnecionUtils.sendRequestWithHttpURLConnection();
    }
}
