package com.example.heaploglibrary.database.repository;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.heaploglibrary.entities.ViewActions;
import com.example.heaploglibrary.entities.ViewTypes;

@Entity(tableName = "event_table")
public class Event {
    @ColumnInfo
    @PrimaryKey(autoGenerate = true)
    Long id;

    @ColumnInfo
    ViewTypes viewType;

    @ColumnInfo
    ViewActions viewAction;

    @ColumnInfo
    Long actionDate;

    Event() {

    }

    public Event(ViewTypes type, ViewActions action, long date) {
        viewType = type;
        viewAction = action;
        actionDate = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ViewTypes getViewType() {
        return viewType;
    }

    public void setViewType(ViewTypes _viewType) {
        viewType = _viewType;
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
        return id+","+viewType+","+viewAction+","+actionDate ;
    }
}
