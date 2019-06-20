package com.example.livedata;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.util.TableInfo;

@Entity
public class Subject {

    @PrimaryKey(autoGenerate = true)
    private int id ;

    public void setId(int id) {
        this.id = id;
    }

    @ColumnInfo(name = "title")
    @NonNull
    private String title ;

    @ColumnInfo(name = "description")
    @NonNull
    private String description ;

    public Subject(@NonNull String title, @NonNull String description) {
        this.title = title;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    @NonNull
    public String getTitle() {
        return title;
    }

    @NonNull
    public String getDescription() {
        return description;
    }
}
