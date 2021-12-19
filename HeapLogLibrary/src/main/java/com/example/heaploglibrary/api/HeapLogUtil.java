package com.example.heaploglibrary.api;

import android.app.Application;

import com.example.heaploglibrary.database.repository.Event;
import com.example.heaploglibrary.database.repository.EventRepository;
import com.example.heaploglibrary.entities.ViewActions;
import com.example.heaploglibrary.entities.ViewTypes;

public class HeapLogUtil {

    private static EventRepository eventRepository;

    public static void init(Application application) {
        eventRepository = EventRepository.createInstance(application);
    }

    public static void log(ViewTypes type, ViewActions action) {
        eventRepository.logToDatabase(new Event(type,action,System.currentTimeMillis()));
    }

}

