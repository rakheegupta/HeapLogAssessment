package com.example.samplelibrary.database.repository;

import android.app.Application;

import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

import java.util.List;

public class EventManager {

    EventRepository eventRepository;
    WorkManager uploadWorkManager;

    public void initRepository(Application application){
        eventRepository = EventRepository.getInstance(application);
        uploadWorkManager = WorkManager.getInstance(application);
    }

    public void insert(Event event) {
        eventRepository.insert(event);
    }

    public List<Event> getEvents(){
        return eventRepository.getEvents();
    }

    public void printEvents() {
        eventRepository.printAllEvents();
        uploadEvents();
    }
    public EventRepository getEventRepository() {
        return eventRepository;
    }

    public void uploadEvents() {
        uploadWorkManager.enqueue(OneTimeWorkRequest
                .from(UploadWorker.class));
    }
}
