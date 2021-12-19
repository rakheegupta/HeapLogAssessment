package com.example.sampleassessment;

import android.app.Application;

import com.example.heaploglibrary.api.HeapLogUtil;


public class MyApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        HeapLogUtil.init(this);
    }


}
