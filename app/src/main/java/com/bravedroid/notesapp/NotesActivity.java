package com.bravedroid.notesapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bravedroid.notesapp.adapters.NotesRecyclerAdapter;
import com.bravedroid.notesapp.models.Note;
import com.bravedroid.notesapp.repository.NoteRepository;
import com.bravedroid.notesapp.util.VerticalSpacingItemDecorator;

import java.util.List;

public class NotesActivity extends AppCompatActivity implements NotesRecyclerAdapter.OnNoteListener {
    final static String TAG = "NotesActivity";
    private NoteRepository mNoteRepository;
    // ui component
    private RecyclerView mRecyclerView;
    // vars
    private List<Note> mNotes;
    private NotesRecyclerAdapter mNotesRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);
        mRecyclerView = findViewById(R.id.recyclerView);
        /*
        Note note = new Note("some title", "some content", "timestamp");
        Log.d(TAG, "**************on create : my note " + note.toString());
        */
        initToolbar();
        initRecyclerView();
    }

    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.notes_toolbar);
        setSupportActionBar(toolbar);
        setTitle("Notes");
    }

    private void initRecyclerView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new VerticalSpacingItemDecorator(10));
        mNoteRepository = ((NotesApp) getApplication()).getNoteRepository();
        mNotes = mNoteRepository.getNotes();
        mNotesRecyclerAdapter = new NotesRecyclerAdapter(mNotes, this);
        mRecyclerView.setAdapter(mNotesRecyclerAdapter);
    }

    // TODO: 04/05/2019 change to lambda syntax
    @Override
    public void onNoteClick(int position) {
        Log.d(TAG, "onNoteClick: clicked" + position);
        Intent intent = new Intent(this, NoteDetailActivity.class);
        intent.putExtra("selected_note", mNotes.get(position));
        startActivity(intent);
    }
}
