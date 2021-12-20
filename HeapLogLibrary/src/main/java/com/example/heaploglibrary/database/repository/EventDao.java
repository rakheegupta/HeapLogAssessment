package com.example.heaploglibrary.database.repository;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.heaploglibrary.entities.ViewIdentifiers;

import java.util.List;

@Dao
interface EventDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long insertEvent(Event event);

    @Query("select * from event_table")
    List<Event> getAllEvents();

    // TODO: LIMIT 5 for testing, change to higher number like 100 or more.
    @Query("select * from event_table LIMIT 5")
    List<Event> getEventsToUpload();

    @Query("delete from event_table where id in (:list)")
    int deleteEvents(long[] list);

    @Query("select * from event_table where ViewType = :viewType")
    LiveData<List<Event>> getByViewType(ViewIdentifiers viewType);

    @Query("select * from event_table where ViewAction = :viewAction")
    LiveData<List<Event>> getByViewAction(ViewIdentifiers viewAction);

    @Query("select * from event_table where actionDate = :date")
    LiveData<List<Event>> getByDate(Long date);


}
