package com.bravedroid.notesapp;

import android.app.Application;

import com.bravedroid.notesapp.repository.NoteRepositoryInterface;
import com.bravedroid.notesapp.repository.NoteRepositoryInterface.FakeNoteRepositoryInterface;

public class NotesApp extends Application {
    private NoteRepositoryInterface noteRepositoryInterface = new FakeNoteRepositoryInterface();

    public NoteRepositoryInterface getNoteRepositoryInterface() {
        return noteRepositoryInterface;
    }
}
