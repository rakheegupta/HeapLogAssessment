package com.example.samplelibrary;

import android.util.Log;

import java.util.Date;

public class LogUtil {

    static String TAG = "SampleLibrary";

    public static void d(String message) {
        Log.d(TAG,message);
    }

    public static void d(String message, Date date) {
        Log.d(TAG,message+"-"+date.toString());
    }
}
