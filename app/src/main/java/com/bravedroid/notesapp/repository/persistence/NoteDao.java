package com.bravedroid.notesapp.repository.persistence;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.bravedroid.notesapp.repository.models.Note;

import java.util.List;

@Dao
public interface NoteDao {
    @Insert
    long[] insertNotes(Note... notes);

    @Query("SELECT * FROM notes")
    LiveData<List<Note>> getNotes();

    @Query("SELECT * FROM notes")
    List<Note> getNotes1();

    /*
    @Query("SELECT * FROM notes WHERE title LIKE :title")
    List<Note> getNotesWithCustomQuery(String title);
    */

    @Delete
    int delete(Note... notes);

    @Query("DELETE FROM notes")
    int deleteAll();

    @Update
    int update(Note... notes);
}
