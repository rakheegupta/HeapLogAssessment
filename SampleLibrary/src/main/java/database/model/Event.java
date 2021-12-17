package database.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "event_table")
public class Event {
    @ColumnInfo
    @PrimaryKey(autoGenerate = true)
    long id;

    @ColumnInfo
    String ViewType;

    @ColumnInfo
    String ViewAction;

    @ColumnInfo
    long actionDate;

}
