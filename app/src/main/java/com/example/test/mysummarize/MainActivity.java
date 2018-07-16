package com.example.test.mysummarize;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.example.customizeviewlibrary.activity.RingActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //NetworkImageView nv_image = findViewById(R.id.nv_image);
        //TestUtils.testHttp(this, nv_image);

        startActivity(new Intent(this, RingActivity.class));
    }
}
