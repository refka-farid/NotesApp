package com.bravedroid.notesapp;

import android.app.Application;

import com.bravedroid.notesapp.repository.NoteRepository;
import com.bravedroid.notesapp.repository.NoteRepository.FakeNoteRepository;

public class NotesApp extends Application {
    private NoteRepository noteRepository = new FakeNoteRepository();

    public NoteRepository getNoteRepository() {
        return noteRepository;
    }
}
