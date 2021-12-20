package com.example.heaploglibrary.database.repository;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.heaploglibrary.entities.ViewActions;
import com.example.heaploglibrary.entities.ViewIdentifiers;

@Entity(tableName = "event_table")
public class Event {
    @ColumnInfo
    @PrimaryKey(autoGenerate = true)
    Long id;

    @ColumnInfo
    ViewIdentifiers viewIdentifier;

    @ColumnInfo
    ViewActions viewAction;

    @ColumnInfo
    Long actionDate;

    Event() {

    }

    public Event(ViewIdentifiers type, ViewActions action, long date) {
        viewIdentifier = type;
        viewAction = action;
        actionDate = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ViewIdentifiers getViewIdentifier() {
        return viewIdentifier;
    }

    public void setViewIdentifier(ViewIdentifiers viewIdentifier) {
        this.viewIdentifier = viewIdentifier;
    }

    public ViewActions getViewAction() {
        return viewAction;
    }

    public void setViewAction(ViewActions _viewAction) {
        viewAction = _viewAction;
    }

    public Long getActionDate() {
        return actionDate;
    }

    public void setActionDate(long actionDate) {
        this.actionDate = actionDate;
    }

    @NonNull
    public String toString() {
        return id+","+ viewIdentifier +","+viewAction+","+actionDate ;
    }
}
