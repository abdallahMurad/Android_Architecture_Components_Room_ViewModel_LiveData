package com.banksy.aac.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import com.banksy.aac.R;
import com.banksy.aac.model.Note;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NoteViewHolder> {

    private List<Note> noteList;
    private OnLongClickListener onLongClickListener;

    public NotesAdapter(ArrayList<Note> notes, OnLongClickListener onLongClickListener) {
        this.noteList = notes;
        this.onLongClickListener = onLongClickListener;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NoteViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        Note note = noteList.get(position);
        holder.noteTitle.setText(note.getNoteTitle());
        holder.noteDesc.setText(note.getNoteDescription());
        holder.itemView.setOnLongClickListener(onLongClickListener);
    }

    public void addNotes(List<Note> notesList) {
        this.noteList = notesList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }

    public class NoteViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_note_title)
        TextView noteTitle;
        @BindView(R.id.tv_note_desc)
        TextView noteDesc;

        public NoteViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
