package com.example.room20;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.room20.Db.Note;
import com.example.room20.Db.NoteRepositories;

import java.util.List;

public class NoteViewModel extends AndroidViewModel {

    private NoteRepositories noteRepo;
    LiveData<List<Note>> allNote;
    public NoteViewModel(Application application) {
        super(application);
        noteRepo = new NoteRepositories(application);
        allNote = noteRepo.getDataSet();
    }

    public LiveData<List<Note>> getAllNote(){
        return allNote;
    }

    void insert(Note note){
        noteRepo.insert(note);
    }
}
