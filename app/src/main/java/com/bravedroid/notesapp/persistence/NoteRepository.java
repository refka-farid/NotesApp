package com.bravedroid.notesapp.persistence;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.bravedroid.notesapp.async.DeleteAsyncTask;
import com.bravedroid.notesapp.async.InsertAsyncTask;
import com.bravedroid.notesapp.async.UpdateAsyncTask;
import com.bravedroid.notesapp.models.Note;

import java.util.List;

public class NoteRepository {
    private NoteDataBase mNoteDatabase;

    public NoteRepository(Context context) {
        mNoteDatabase = NoteDataBase.getInstance(context);
    }

    public void insertNoteTask(Note note) {
        new InsertAsyncTask(mNoteDatabase.getNoteDao()).execute(note);
    }

    public void updateNote(Note note) {
        new UpdateAsyncTask(mNoteDatabase.getNoteDao()).execute(note);
    }

    public LiveData<List<Note>> retreiveNoteTask() {
        return mNoteDatabase.getNoteDao().getNotes();
    }

    public void deleteNote(Note note) {
        new DeleteAsyncTask(mNoteDatabase.getNoteDao()).execute(note);
    }
}
