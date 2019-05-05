package com.bravedroid.notesapp.repository;

import com.bravedroid.notesapp.models.Note;

import java.util.ArrayList;
import java.util.List;

public interface NoteRepository {
    List<Note> getNotes();

    class FakeNoteRepository implements NoteRepository {

        private List<Note> mNotes = new ArrayList<>();

        @Override
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

    }
}
