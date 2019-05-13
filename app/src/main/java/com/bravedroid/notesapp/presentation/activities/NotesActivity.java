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

    // vars
    private List<Note> mNotes = new ArrayList<>();
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
        mNoteRepository.retrieveNoteTask().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes) {
                if (mNotes.size() > 0) {
                    mNotes.clear();
                }
                if (notes != null) {
                    mNotes.addAll(notes);
                }
                mNotesRecyclerAdapter.notifyDataSetChanged();
            }
        });
    }

    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.notes_toolbar);
        setSupportActionBar(toolbar);
        setTitle("Notes");
    }

    private void initRecyclerView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new VerticalSpacingItemDecorator(10));
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(mRecyclerView);
        mNotesRecyclerAdapter = new NotesRecyclerAdapter(mNotes, (int position) -> {
            Log.d(TAG, "onNoteClick: clicked" + position);
            Intent intent = new Intent(NotesActivity.this, NoteDetailActivity.class);
            intent.putExtra("selected_note", mNotes.get(position));
            startActivity(intent);
        });
        mRecyclerView.setAdapter(mNotesRecyclerAdapter);
    }

    private void deleteNote(Note note) {
        mNotes.remove(note);
        mNotesRecyclerAdapter.notifyDataSetChanged();
        mNoteRepository.deleteNote(note);
    }

    private ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            deleteNote(mNotes.get(viewHolder.getAdapterPosition()));
        }
    };
}
