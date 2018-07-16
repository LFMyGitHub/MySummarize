package com.example.customizeviewlibrary.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.customizeviewlibrary.R;
import com.example.customizeviewlibrary.RingView;

public class RingActivity extends AppCompatActivity {
    RingView mRingView;
    TextView tv1, tv2, tv3, tv4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ring);

        mRingView = findViewById(R.id.superview);
        mRingView.setSelect((int)(360 * (10 / 100f)));

        tv1 = findViewById(R.id.tv1);
        tv1.setText(getString(R.string.str1, "19"));
        tv2 = findViewById(R.id.tv2);
        tv2.setText(getString(R.string.str2, "21"));
        tv3 = findViewById(R.id.tv3);
        tv3.setText(getString(R.string.str3, "555555"));
        tv1 = findViewById(R.id.tv3);
    }
}
