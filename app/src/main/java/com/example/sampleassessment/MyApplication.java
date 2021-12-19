package com.example.sampleassessment;

import android.app.Application;

import com.example.samplelibrary.user.LogUtil;


public class MyApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        LogUtil.init(this);
    }


}
