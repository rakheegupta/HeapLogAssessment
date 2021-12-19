package com.example.samplelibrary.database.repository;

import android.app.Application;
import android.content.Context;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EventRepository {

    private final EventDao eventDao;
    private EventDatabase eventDatabase;
    private static EventRepository INSTANCE;

    ExecutorService executorService = Executors.newSingleThreadExecutor();

    private List<Event> events;
    UploadWorker uploadWorker;

    private EventRepository(Context context) {
        eventDatabase = EventDatabase.getDatabase((Application)context);
        eventDao = eventDatabase.eventDao();
        getAllEvents();
        uploadWorker = new UploadWorker(context,null);
    }

    public static EventRepository getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (EventRepository.class) {
                if (INSTANCE == null) {
                    INSTANCE = new EventRepository(context);
                }
            }
        }
        return INSTANCE;
    }

    public void insert(Event event) {
        executorService.execute(new Runnable() {
           public void run(){
               long result = eventDao.insertEvent(event);
               System.out.println("insert result "+result);

               //print elements in the database
               List<Event> events = eventDao.getAllEvents();
               if(events != null)
                for(Event event : events){
                   System.out.println(event.toString());
                }
           }
       });

//        AsyncTask.execute(new Runnable() {
//            @Override
//            public void run() {
//                eventDao.insertEvent(event);
//            };
//        });


    }

    public void setDatabase(EventDatabase database) {
        eventDatabase = database;
    }

    public void printAllEvents() {
        executorService.execute(new Runnable() {
            public void run(){
                //print elements in the database
                List<Event> events = eventDao.getAllEvents();
                if(events != null)
                    for(Event event : events){
                        System.out.println(event.toString());
                    }
            }
        });
    }

    public void getAllEvents() {
        executorService.execute(new Runnable() {
            public void run(){
                //print elements in the database
                events = eventDao.getAllEvents();
                if(events != null)
                    for(Event event : events){
                        System.out.println(event.toString());
                    }
                uploadWorker.setEvents(getEvents());
            }
        });
    }

    List<Event> getEvents() {
        return events;
    }

}
