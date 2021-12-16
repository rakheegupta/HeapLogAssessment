package com.example.sampleassessment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.samplelibrary.LogUtil;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LogUtil.d("Activity created");
    }
}