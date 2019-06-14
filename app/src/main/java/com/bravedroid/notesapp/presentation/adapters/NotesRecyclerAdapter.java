package com.bravedroid.notesapp.presentation.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bravedroid.notesapp.R;
import com.bravedroid.notesapp.presentation.util.Utility;
import com.bravedroid.notesapp.repository.models.Note;

import java.util.ArrayList;
import java.util.List;

public class NotesRecyclerAdapter extends RecyclerView.Adapter<NotesRecyclerAdapter.NoteViewHolder> {
    private List<Note> mNotes;
    private OnNoteListener mOnNoteListener;
    private final static String TAG = "NotesRecyclerAdapter";

    public NotesRecyclerAdapter(OnNoteListener onNoteListener) {
        this.mNotes = new ArrayList<>();
        this.mOnNoteListener = onNoteListener;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.layout_note_list_item, viewGroup, false);
        return new NoteViewHolder(itemView, mOnNoteListener, mNotes);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder noteViewHolder, int position) {
        try {
            String month = mNotes.get(position).getTimestamp().substring(0, 2);
            month = Utility.getMonthFromNumber(month);
            String year = mNotes.get(position).getTimestamp().substring(3);
            String timestamp = month + " " + year;
            noteViewHolder.timestampTV.setText(timestamp);
            noteViewHolder.titleTV.setText(mNotes.get(position).getTitle());
        } catch (NullPointerException e) {
            Log.d(TAG, "onBindViewHolder: " + e.getMessage());
        }
        noteViewHolder.timestampTV.setText(mNotes.get(position).getTimestamp());
        noteViewHolder.titleTV.setText(mNotes.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return mNotes == null ? 0 : mNotes.size();
    }

    public void addNotes(List<Note> notes) {
        if (notes != null) {
            if (mNotes.size() > 0) {
                mNotes.clear();
            }
            mNotes.addAll(notes);
            notifyDataSetChanged();
        }
    }

    public void removeNote(Note note) {
        mNotes.remove(note);
        notifyDataSetChanged();
    }

    @FunctionalInterface
    public interface OnNoteListener {
        void onNoteClick(Note clickedNote);
    }

    public static class NoteViewHolder extends RecyclerView.ViewHolder {
        private List<Note> mNotes;
        private TextView titleTV, timestampTV;

        NoteViewHolder(@NonNull View itemView, OnNoteListener onNoteListener, List<Note> notes) {
            super(itemView);
            titleTV = itemView.findViewById(R.id.note_title);
            timestampTV = itemView.findViewById(R.id.note_timestamp);
            this.mNotes = notes;
            itemView.setOnClickListener((View v) -> {
                //onNoteListener.onNoteClick(getAdapterPosition());
                int position = getAdapterPosition();
                Note clickedNote = notes.get(position);
                onNoteListener.onNoteClick(clickedNote);
            });
        }

        public Note getNote(int adapterPosition) {
            Note note = mNotes.get(adapterPosition);
            return note;
        }
    }
}
