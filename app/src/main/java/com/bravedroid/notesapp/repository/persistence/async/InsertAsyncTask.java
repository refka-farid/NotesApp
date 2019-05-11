package com.bravedroid.notesapp.repository.persistence.async;

import android.os.AsyncTask;
import android.util.Log;

import com.bravedroid.notesapp.repository.models.Note;
import com.bravedroid.notesapp.repository.persistence.NoteDao;

public class InsertAsyncTask extends AsyncTask<Note, Void, Void> {
    private static final String TAG = "InsertAsyncTask";

    private NoteDao mNoteDao;

    public InsertAsyncTask(NoteDao dao) {
        mNoteDao = dao;
    }

    @Override
    protected Void doInBackground(Note... notes) {
        Log.d(TAG, "doInBackground: "+ Thread.currentThread().getName());
        mNoteDao.insertNotes(notes);
        return null;
    }
}
