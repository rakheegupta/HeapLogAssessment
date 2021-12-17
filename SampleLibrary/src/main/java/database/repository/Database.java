package database.repository;

import database.model.Event;
import androidx.room.RoomDatabase;
import androidx.room.Database;

@Database(entities = {Event.class}, version =1, exportSchema = false)
abstract class EventDatabase extends RoomDatabase {

    public static final String NAME = "EventDataBase";
}
