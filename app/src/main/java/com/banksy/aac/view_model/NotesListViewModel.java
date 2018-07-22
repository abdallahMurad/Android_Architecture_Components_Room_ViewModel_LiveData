package com.banksy.aac.view_model;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.banksy.aac.database.NoteDatabase;
import com.banksy.aac.model.Note;

import java.util.List;

public class NotesListViewModel extends AndroidViewModel {
    private final LiveData<List<Note>> noteList;
    private NoteDatabase noteDatabase;


    public NotesListViewModel(@NonNull Application application) {
        super(application);
        noteDatabase = NoteDatabase.getDatabase(this.getApplication());
        noteList = noteDatabase.getNoteModelDao().getAllNotes();
    }

    public LiveData<List<Note>> getNoteList() {
        return noteList;
    }

    public void deleteNote(Note note) {
        new DeleteAsyncTask(noteDatabase).execute(note);
    }

    private class DeleteAsyncTask extends AsyncTask<Note, Void, Void> {
        private NoteDatabase noteDatabase;
        DeleteAsyncTask(NoteDatabase noteDatabase) {
            this.noteDatabase = noteDatabase;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDatabase.getNoteModelDao().deleteNote(notes[0]);
            return null;
        }
    }
}
