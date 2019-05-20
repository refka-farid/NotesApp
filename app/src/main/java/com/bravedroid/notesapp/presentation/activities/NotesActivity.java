package com.bravedroid.notesapp.presentation.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bravedroid.notesapp.NotesApp;
import com.bravedroid.notesapp.R;
import com.bravedroid.notesapp.presentation.NoteRepository;
import com.bravedroid.notesapp.presentation.adapters.NotesRecyclerAdapter;
import com.bravedroid.notesapp.presentation.util.VerticalSpacingItemDecorator;
import com.bravedroid.notesapp.repository.models.Note;

import java.util.ArrayList;
import java.util.List;

public class NotesActivity extends AppCompatActivity {
    final static String TAG = "NotesActivity";
    // ui component
    private RecyclerView mRecyclerView;

    private NotesRecyclerAdapter mNotesRecyclerAdapter;
    private NoteRepository mNoteRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);
        mRecyclerView = findViewById(R.id.recyclerView);

        findViewById(R.id.fab).setOnClickListener(v -> {
            Intent intent = new Intent(NotesActivity.this, NoteDetailActivity.class);
            startActivity(intent);
        });
        initToolbar();
        initRecyclerView();

        mNoteRepository = ((NotesApp) getApplication()).getNoteRepository();
        retrieveNotes();
        Log.d(TAG, "onCreate: thread :" + Thread.currentThread().getName());
    }

    private void retrieveNotes() {
        mNoteRepository.retrieveNoteTask().observe(this, notes -> mNotesRecyclerAdapter.addNotes(notes));
    }

    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.notes_toolbar);
        setSupportActionBar(toolbar);
        setTitle("Notes");
    }

    private void initRecyclerView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new VerticalSpacingItemDecorator(10));

        new ItemTouchHelper(new MyItemTouchHelper(swipedNote -> deleteNote(swipedNote))).attachToRecyclerView(mRecyclerView);

        mNotesRecyclerAdapter = new NotesRecyclerAdapter((Note clickedNote) -> {
            Log.d(TAG, "onNoteClick: clicked" + clickedNote);
            Intent intent = new Intent(NotesActivity.this, NoteDetailActivity.class);
            intent.putExtra("selected_note", clickedNote);
            startActivity(intent);
        });
        mRecyclerView.setAdapter(mNotesRecyclerAdapter);
    }

    private void deleteNote(Note note) {
        mNotesRecyclerAdapter.removeNote(note);
        mNoteRepository.deleteNote(note);
    }

    private static class MyItemTouchHelper extends ItemTouchHelper.SimpleCallback {

        private OnNoteListener onNoteListener;

        public MyItemTouchHelper(OnNoteListener onNoteListener) {
            this(0, 0, onNoteListener);
        }

        private MyItemTouchHelper(int dragDirsIgnored, int swipeDirsIgnored, OnNoteListener onNoteListener) {
            super(0, ItemTouchHelper.RIGHT);
            this.onNoteListener = onNoteListener;
        }

        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            NotesRecyclerAdapter.NoteViewHolder noteViewHolder = (NotesRecyclerAdapter.NoteViewHolder) viewHolder;
            onNoteListener.onNoteSwiped(noteViewHolder.getNote(viewHolder.getAdapterPosition()));
        }

        @FunctionalInterface
        public interface OnNoteListener {
            void onNoteSwiped(Note swipedNote);
        }
    }

}
