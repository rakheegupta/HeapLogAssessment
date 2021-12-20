package com.example.heaploglibrary.database.repository;

import android.app.Application;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Event.class}, version =4, exportSchema = false)
public abstract class EventDatabase extends RoomDatabase {

    public static final String NAME = "EventDataBase";
    public abstract EventDao eventDao();
    private static EventDatabase DB_INSTANCE;

    static EventDatabase getDatabase(Application application) {
        if(DB_INSTANCE == null) {
            synchronized (EventDatabase.class) {
                if(DB_INSTANCE == null)
                    DB_INSTANCE = Room.databaseBuilder(
                        application.getApplicationContext(),
                        EventDatabase.class,
                        NAME)
                        .fallbackToDestructiveMigration()
                        .build();
            }
        }
        return DB_INSTANCE;
    }

}
