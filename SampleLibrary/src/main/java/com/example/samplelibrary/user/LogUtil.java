package com.example.samplelibrary.user;

import android.app.Application;
import android.util.Log;

import com.example.samplelibrary.database.client.DatabaseClient;
import com.example.samplelibrary.database.repository.Event;
import com.example.samplelibrary.entities.ViewActions;
import com.example.samplelibrary.entities.ViewTypes;

import java.util.Date;

public class LogUtil {

    private static Application application;
    static DatabaseClient client;

    public static void init(Application app) {
        application = app;
        client = new DatabaseClient(application);;
    }

    static String TAG = "SampleLibrary";

    public static void d(String message) {
        Log.d(TAG,message);
    }

    public static void d(String message, String action) {
        Log.d(TAG,message+"-"+action+"-"+new Date());
    }

    public static void log(ViewTypes type, ViewActions action) {
        //Log.d(TAG,type+"-"+action+"-"+new Date());
        client.logToDatabase(new Event(type,action,new Date().getTime()));
        client.printEvents();
    }

}

