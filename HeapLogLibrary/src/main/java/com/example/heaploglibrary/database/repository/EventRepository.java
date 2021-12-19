package com.example.heaploglibrary.database.repository;

import android.app.Application;
import android.content.Context;

import androidx.work.Constraints;
import androidx.work.NetworkType;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class EventRepository {

    private final EventDao eventDao;
    private static EventRepository INSTANCE;
    WorkManager uploadWorkManager;
    ExecutorService executorService = Executors.newSingleThreadExecutor();

    public static EventRepository createInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (EventRepository.class) {
                if (INSTANCE == null) {
                    INSTANCE = new EventRepository(context);
                }
            }
        }
        return INSTANCE;
    }

    private EventRepository(Context context) {
        EventDatabase eventDatabase = EventDatabase.getDatabase((Application) context);
        eventDao = eventDatabase.eventDao();
        initWorkManager((Application) context);
    }

    private void initWorkManager(Application application){
        uploadWorkManager = WorkManager.getInstance(application);
        scheduleUploadOfEvents();
    }

    //Auto-schedule upload events
    private void scheduleUploadOfEvents() {
        // Create the Constraints
        Constraints constraints = new Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build();

        PeriodicWorkRequest periodicUploadRequest =
                new PeriodicWorkRequest.Builder(UploadWorker.class,
                        1,
                        TimeUnit.MINUTES) // TODO: SECONDS for testing, change to MINUTES
                        .setConstraints(constraints)
                        .build();
        uploadWorkManager.enqueue(periodicUploadRequest);
    }



    public void logToDatabase(Event event) {
        executorService.execute(new Runnable() {
            public void run(){
                long result = eventDao.insertEvent(event);
                System.out.println("insert result "+result);
            }
        });
    }



    static EventRepository getInstance() {
        return INSTANCE;
    }

    List<Event> getEventsToUpload() {
        return eventDao.getEventsToUpload();
    }

    int deleteEvents(long[] ids) {
        return eventDao.deleteEvents(ids);
    }
}
