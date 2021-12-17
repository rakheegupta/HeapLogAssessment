package com.example.samplelibrary.user;

import android.util.Log;

import com.example.samplelibrary.entities.ViewActions;
import com.example.samplelibrary.entities.ViewTypes;

import java.util.Date;

public class LogUtil {

    static String TAG = "SampleLibrary";

    public static void d(String message) {
        Log.d(TAG,message);
    }

    public static void d(String message, String action) {
        Log.d(TAG,message+"-"+action+"-"+new Date());
    }

    public static void log(ViewTypes type, ViewActions action) {
        Log.d(TAG,type+"-"+action+"-"+new Date());
    }



}

