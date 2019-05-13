package com.bravedroid.notesapp.repository;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.bravedroid.notesapp.presentation.NoteRepository;
import com.bravedroid.notesapp.repository.models.Note;
import com.bravedroid.notesapp.repository.persistence.NoteDataBase;
import com.bravedroid.notesapp.repository.persistence.async.DeleteAsyncTask;
import com.bravedroid.notesapp.repository.persistence.async.InsertAsyncTask;
import com.bravedroid.notesapp.repository.persistence.async.UpdateAsyncTask;
import com.bravedroid.notesapp.repository.persistence.async.WorkerAsyncTask;

import java.util.List;

public class NoteRepositoryImpl implements NoteRepository {
    private NoteDataBase mNoteDatabase;

    public NoteRepositoryImpl(Context context) {
        mNoteDatabase = NoteDataBase.getInstance(context);
    }

    @Override
    public void insertNoteTask(Note note) {
        new InsertAsyncTask(mNoteDatabase.getNoteDao()).execute(note);
    }

    @Override
    public LiveData<List<Note>> retrieveNoteTask() {
        return mNoteDatabase.getNoteDao().getNotes();
    }

    @Override
    public void updateNote(Note note) {
        //new UpdateAsyncTask(mNoteDatabase.getNoteDao()).execute(note);
        new WorkerAsyncTask(() -> mNoteDatabase.getNoteDao().update(note));
    }

    @Override
    public void deleteNote(Note note) {
        new DeleteAsyncTask(mNoteDatabase.getNoteDao()).execute(note);
        new WorkerAsyncTask(() -> mNoteDatabase.getNoteDao().delete(note)).execute();
    }
}
