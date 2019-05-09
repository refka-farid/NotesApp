package com.bravedroid.notesapp.persistence;

import android.content.Context;

public class NoteRepository {
    private NoteDataBase mNoteDatabase;

    public NoteRepository(Context context) {
        mNoteDatabase = NoteDataBase.getInstance(context);
    }
}
