package com.example.room20.Db;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class NoteRepositories {
    DAO mNoteDao;
    LiveData<List<Note>> dataSet;

    public NoteRepositories (Application application){
        NoteDatabase db = NoteDatabase.getDatabase(application);
        mNoteDao = db.noteDao();
        dataSet = mNoteDao.getAllNote();
    }

    public LiveData<List<Note>> getDataSet(){
        return  dataSet;
    }

    public void insert(Note note){
    NoteDatabase.databaseWriterExecutor.execute(()->{
        mNoteDao.insert(note);
    });
    }
}
