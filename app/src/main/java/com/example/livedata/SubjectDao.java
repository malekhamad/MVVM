package com.example.livedata;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface SubjectDao {

    // insert Subject . . .;
    @Insert
    void insert(Subject subject);

    // get all Subjects . . . ;
    @Query("SELECT * FROM Subject")
    LiveData<List<Subject>> list();

    // get subject where id is equal  . . . .;
    @Query("SELECT * FROM Subject WHERE id =:subjectId")
    LiveData<Subject> getSubject(int subjectId);

    // update subject . . . ;
    @Update
    void updateSubject(Subject subject);

    // delete subject . . . ;
    @Delete
    void deleteSubject(Subject subject);
}
