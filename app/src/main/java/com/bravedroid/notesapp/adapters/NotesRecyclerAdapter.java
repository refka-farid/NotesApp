package com.bravedroid.notesapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bravedroid.notesapp.R;
import com.bravedroid.notesapp.models.Note;

import java.util.List;

import static com.bravedroid.notesapp.adapters.NotesRecyclerAdapter.NoteViewHolder;

public class NotesRecyclerAdapter extends RecyclerView.Adapter<NoteViewHolder> {
    private List<Note> mNotes;
    private OnNoteListener mOnNoteListener;

    public NotesRecyclerAdapter(List<Note> notes, OnNoteListener onNoteListener) {
        this.mNotes = notes;
        this.mOnNoteListener = onNoteListener;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.layout_note_list_item, viewGroup, false);
        return new NoteViewHolder(itemView, mOnNoteListener);
    }


    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder noteViewHolder, int position) {
        noteViewHolder.timestampTV.setText(mNotes.get(position).getTimestamp());
        noteViewHolder.titleTV.setText(mNotes.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return mNotes == null ? 0 : mNotes.size();
    }

    public static class NoteViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView titleTV, timestampTV;
        private OnNoteListener onNoteListener;

        NoteViewHolder(@NonNull View itemView, OnNoteListener onNoteListener) {
            super(itemView);
            titleTV = itemView.findViewById(R.id.note_title);
            timestampTV = itemView.findViewById(R.id.note_timestamp);
            this.onNoteListener = onNoteListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onNoteListener.onNoteClick(getAdapterPosition());
        }
    }

    @FunctionalInterface
    public interface OnNoteListener {
        void onNoteClick(int position);
    }
}
