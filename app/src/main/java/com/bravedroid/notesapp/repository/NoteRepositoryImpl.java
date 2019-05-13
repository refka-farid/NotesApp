package com.bravedroid.notesapp.repository;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.bravedroid.notesapp.presentation.NoteRepository;
import com.bravedroid.notesapp.repository.models.Note;
import com.bravedroid.notesapp.repository.persistence.NoteDataBase;
import com.bravedroid.notesapp.repository.persistence.async.WorkerAsyncTask;

import java.util.List;

public class NoteRepositoryImpl implements NoteRepository {
    private NoteDataBase mNoteDatabase;

    public NoteRepositoryImpl(Context context) {
        mNoteDatabase = NoteDataBase.getInstance(context);
    }

    @Override
    public void insertNoteTask(Note note) {
        new WorkerAsyncTask(() -> mNoteDatabase.getNoteDao().insertNotes(note)).execute();
    }

    @Override
    public LiveData<List<Note>> retrieveNoteTask() {
        return mNoteDatabase.getNoteDao().getNotes();
    }

    @Override
    public void updateNote(Note note) {
        new WorkerAsyncTask(() -> mNoteDatabase.getNoteDao().update(note)).execute();
    }

    @Override
    public void deleteNote(Note note) {
        new WorkerAsyncTask(() -> mNoteDatabase.getNoteDao().delete(note)).execute();
    }
}
