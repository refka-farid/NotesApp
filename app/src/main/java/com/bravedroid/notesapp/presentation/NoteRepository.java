package com.bravedroid.notesapp.presentation;

import androidx.lifecycle.LiveData;

import com.bravedroid.notesapp.repository.models.Note;

import java.util.ArrayList;
import java.util.List;

public interface NoteRepository {
    void insertNoteTask(Note note);

    LiveData<List<Note>> retrieveNoteTask();

    void updateNote(Note note);

    void deleteNote(Note note);

    class NoteRepositoryImplFake implements NoteRepository {

        private List<Note> mNotes = new ArrayList<>();

        @Deprecated
        public List<Note> getNotes() {
            if (mNotes.isEmpty()) {
                for (int i = 0; i < 1000; i++) {
                    Note note = new Note();
                    note.setTitle("title #" + i);
                    note.setContent("content #" + i);
                    note.setTimestamp("jan 2019");
                    mNotes.add(note);
                }
            }
            return mNotes;
        }

        @Override
        public void insertNoteTask(Note note) {
            throw new NotImplementedException();
        }

        @Override
        public LiveData<List<Note>> retrieveNoteTask() {
            throw new NotImplementedException();
        }

        @Override
        public void updateNote(Note note) {
            throw new NotImplementedException();
        }

        @Override
        public void deleteNote(Note note) {
            throw new NotImplementedException();
        }

        private class NotImplementedException extends RuntimeException {
        }
    }
}
