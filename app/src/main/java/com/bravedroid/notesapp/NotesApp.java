package com.bravedroid.notesapp;

import android.app.Application;

import com.bravedroid.notesapp.presentation.NoteRepository;
import com.bravedroid.notesapp.repository.NoteRepositoryImpl;

public class NotesApp extends Application {
    private NoteRepository noteRepository;

    @Override
    public void onCreate() {
        super.onCreate();
        noteRepository = new NoteRepositoryImpl(this);
//        noteRepository = new NoteRepository.NoteRepositoryImplFake();
    }

    public NoteRepository getNoteRepository() {
        return noteRepository;
    }

}
