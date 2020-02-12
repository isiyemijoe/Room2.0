package com.example.room20.Db;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Note note);

    @Delete
    void  delete(Note note);

    @Query("SELECT * FROM NOTE_TABLE ORDER BY note_id DESC")
    LiveData<List<Note>> getAllNote();
}
