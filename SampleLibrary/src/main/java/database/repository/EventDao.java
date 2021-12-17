package database.repository;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.samplelibrary.entities.ViewTypes;

import java.util.List;

import database.model.Event;

@Dao
interface EventDao {
    @Query("select * from event_table where ViewType = :viewType")
    LiveData<List<Event>> getByViewType(ViewTypes viewType);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long insertEvent(Event event);

    @Query("select * from event_table ORDER BY ViewType ASC")
    LiveData<List<Event>> getAllEvents();

    @Query("select * from event_table where ViewAction = :viewAction")
    LiveData<List<Event>> getByViewAction(ViewTypes viewAction);

    @Query("select * from event_table where actionDate = :date")
    LiveData<List<Event>> getByViewType(Long date);


}
