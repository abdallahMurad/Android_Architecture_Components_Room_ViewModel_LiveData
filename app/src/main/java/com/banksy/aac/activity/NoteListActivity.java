package com.banksy.aac.activity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.banksy.aac.R;
import com.banksy.aac.adapter.NotesAdapter;
import com.banksy.aac.model.Note;
import com.banksy.aac.view_model.NotesListViewModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NoteListActivity extends AppCompatActivity {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.fab)
    FloatingActionButton fab;

    private NotesListViewModel notesListViewModelg;
    private NotesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(NoteListActivity.this, AddNoteActivity.class));
            }
        });


        adapter = new NotesAdapter(new ArrayList<Note>(), new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
//                Note note = (Note) view.getTag();
//                notesListViewModel.deleteNote(note);
//                Toast.makeText(NoteListActivity.this, note.getNoteTitle() + "->Just deleted", Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        notesListViewModel = ViewModelProviders.of(this).get(NotesListViewModel.class);
        notesListViewModel.getNoteList().observe(NoteListActivity.this, new Observer<List<Note>>() {
            @Override
            public void onChanged(@Nullable List<Note> notes) {
                adapter.addNotes(notes);
            }
        });
    }

}
