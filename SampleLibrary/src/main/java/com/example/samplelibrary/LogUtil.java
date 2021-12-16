package com.example.samplelibrary;

import android.util.Log;

public class LogUtil {

    static String TAG = "SampleLibrary";

    public static void d(String message) {
        Log.d(TAG,message);
    }
}
