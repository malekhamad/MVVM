package com.example.livedata;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Entity;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = Subject.class , version = 1)
public abstract class SubjectRoomDatabase extends RoomDatabase {

    public abstract SubjectDao subjectDao();

    private static SubjectRoomDatabase subjectRoomDatabase ;


    public static synchronized SubjectRoomDatabase getInstance(Context context){
        if(subjectRoomDatabase == null){
          subjectRoomDatabase =    // to build database . . . .;
                  subjectRoomDatabase = Room.databaseBuilder(context.getApplicationContext(),SubjectRoomDatabase.class,"subject_table")
                          .build();
        }
        return subjectRoomDatabase;
    }




}
