package com.bravedroid.notesapp.repository.persistence;

import android.content.Context;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;

import com.bravedroid.notesapp.repository.models.Note;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@RunWith(AndroidJUnit4ClassRunner.class)
public class NoteDataBaseTest {
    private NoteDataBase SUT;
    private Note my_first_note;
    private Note my_second_note;
    private Note my_third_note;
    List<Note> noteList;
    long[] insertNotes;

    @Before
    public void setUp() {
        Context appContext = ApplicationProvider.getApplicationContext();
        SUT = NoteDataBase.getInstanceForTest(appContext);
        noteList = new ArrayList<>();
        my_first_note = new Note("my first note", "1234", "2019");
    }

    @After
    public void tearDown() {
        SUT.getNoteDao().deleteAll();
    }

    @Test
    public void insertNoteTest() {
        SUT.getNoteDao().insertNotes(my_first_note);
        assertThat(SUT.getNoteDao().getNotes1().size(), is(equalTo(1)));
    }

    @Test
    public void deleteNoteTest() {
        SUT.getNoteDao().deleteAll();
        my_first_note = new Note("my first note", "1234", "2019");
        my_second_note = new Note("my second note", "8888", "2000");
        my_third_note = new Note("my third note", "2222", "1111");
        insertNotes = SUT.getNoteDao().insertNotes(my_first_note, my_second_note, my_third_note);
        SUT.getNoteDao().deleteAll();
        assertThat(SUT.getNoteDao().getNotes1().size(), is(equalTo(0)));
    }


    @Test
    public void updateNoteTest() throws InterruptedException {
        Note originNote = new Note("my first note", "1234", "2019");
        SUT.getNoteDao().insertNotes(originNote);
        Note notesToUpdate = SUT.getNoteDao().getNotes1().get(0);

        notesToUpdate.setContent("go to supermarket");
        SUT.getNoteDao().update(notesToUpdate);
        //Thread.sleep(3000);
        List<Note> notes1 = SUT.getNoteDao().getNotes1();
        assertThat(notes1.size(), is(equalTo(1)));
        assertEquals("go to supermarket", notes1.get(0).getContent());

    }

}
