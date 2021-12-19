package com.example.samplelibrary.database.client;

import android.app.Application;

import com.example.samplelibrary.database.repository.Event;
import com.example.samplelibrary.database.repository.EventManager;

public class DatabaseClient {

    EventManager eventManager;

    public void logToDatabase(Event event) {
        eventManager.insert(event);
    }

    public DatabaseClient(Application application) {
        eventManager = new EventManager();
        eventManager.initRepository(application);
    }

    public void printEvents() {
        eventManager.printEvents();
    }

}
