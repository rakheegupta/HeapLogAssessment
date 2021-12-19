package com.example.samplelibrary.database.repository;

import android.app.Application;

import androidx.work.Constraints;
import androidx.work.NetworkType;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class EventManager {

    EventRepository eventRepository;
    WorkManager uploadWorkManager;

    public void initRepository(Application application){
        eventRepository = EventRepository.getInstance(application);
        uploadWorkManager = WorkManager.getInstance(application);
        uploadEvents();
    }

    public void insert(Event event) {
        eventRepository.insert(event);
    }

    public List<Event> getEvents(){
        return eventRepository.getEvents();
    }

    public void printEvents() {
        eventRepository.printAllEvents();

    }
    public EventRepository getEventRepository() {
        return eventRepository;
    }

    //schedule upload events
    public void uploadEvents() {

        // Create the Constraints
        Constraints constraints = new Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build();

        PeriodicWorkRequest periodicUploadRequest =
                new PeriodicWorkRequest.Builder(UploadWorker.class,
                        10,
                        TimeUnit.SECONDS)
                        .setConstraints(constraints)
                        .build();
        uploadWorkManager.enqueue(periodicUploadRequest);

        /*
        //one time executor - no constraints
        uploadWorkManager.enqueue(OneTimeWorkRequest
                .from(UploadWorker.class));

        //or one time executor with constraints
        WorkRequest uploadWorkRequest = setupWorkRequest();
        uploadWorkManager.enqueue(uploadWorkRequest);
        */

    }

}
